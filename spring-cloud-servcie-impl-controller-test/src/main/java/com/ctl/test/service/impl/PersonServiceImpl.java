package com.ctl.test.service.impl;

import com.ctl.test.mapper.PersonMapper;
import com.ctl.test.model.Person;
import com.ctl.test.model.Student;
import com.ctl.test.service.PersonService;
import com.ctl.test.service.StudentService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import java.util.Date;
import java.util.Random;

/**
 * <p>Title: PersonServiceImpl</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-03-09 15:15
 */
@Service
public class PersonServiceImpl implements PersonService {

    static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
    @Autowired(required = false)
    private StudentService studentService;

    @Autowired(required = false)
    private PersonMapper personMapper;

    //编程式事务相对于方法上的事务可以更快的释放数据库连接(尤其当方法某些代码执行的比较慢时)

    //编程式事务 方式1
    @Autowired
    private TransactionTemplate transactionTemplate;
    //编程式事务 方式2
    @Autowired
    private PlatformTransactionManager transactionManager;
    /**
     * 根据pid获取person
     *
     * @param pid
     * @return
     */
    @Override
    public Person getPersonByPid(int pid) {
        return personMapper.selectByPrimaryKey(pid);
    }

    private void addPersonStudent(){
        Student student = new Student();
        student.setSname("嵌套方法入库" + System.currentTimeMillis());
        studentService.addStudent(student);
        Person person = new Person();
        person.setName("嵌套方法入库-" + System.currentTimeMillis() + "-transactionTemplate");
        person.setUpdatetime(new Date());
        person.setStatus((byte) 1);
        personMapper.insertSelective(person);
    }

    @Override
    public boolean addPerson(Person person) {
        Person person1 = null;
        try {
            person1 = (Person) transactionTemplate.execute(new TransactionCallback() {
                //编程式事务 doInTransaction 执行结束提交事务,中途出错事务回滚
                //多个transactionTemplate.execute执行的结果互不影响 person1和person2两个事务互不影响
                //person2没有try-catch执行失败后person1的数据已经入库不会回滚
                //这样的写法有点在于如果事务加在方法上,而该方法又调用别的接口或者处理很慢,会造成数据库连接长期占用得不到有效释放
                @Override
                public Object doInTransaction(TransactionStatus transactionStatus) {
                    Student student = new Student();
                    student.setSname("" + System.currentTimeMillis());
                    studentService.addStudent(student);
                    //此时student没有入库,虽然调用的是另一个接口但是在此doInTransaction方法中保持了事务的一致性

                    addPersonStudent();
                    //addPersonStudent执行后没有入库,doInTransaction方法中保持了事务的一致性


                    Person person = new Person();
                    person.setName("ctl-" + System.currentTimeMillis() + "-transactionTemplate");
                    person.setUpdatetime(new Date());
                    person.setStatus((byte) 1);
                    int result = personMapper.insertSelective(person);
                    //此时虽然没有入库但是已经可以拿到自增主键
                    logger.info("person1-result={},pid{}", result, person.getId());
                    logger.info("" + 2 / new Random().nextInt(2)); //此句子执行后失败，事务回滚
                    return person;
                }
            });
        } catch (Exception e) {
            logger.error("保存person1失败", e.getMessage());
        }
        Person person2 = null;
        try {
            person2 = (Person) transactionTemplate.execute(new TransactionCallback() {
                @Override
                public Object doInTransaction(TransactionStatus transactionStatus) {
                    Person person = new Person();
                    person.setName("ctl-" + System.currentTimeMillis() + "-transactionTemplate");
                    person.setUpdatetime(new Date());
                    person.setStatus((byte) 1);
                    int result = personMapper.insertSelective(person);
                    //此时虽然没有入库但是已经可以拿到自增主键
                    logger.info("person2-result={},pid{}", result, person.getId());
                    logger.info("" + 2 / new Random().nextInt(2)); //此句子执行后失败，事务回滚
                    return person;
                }
            });
        } catch (Exception e) {
            logger.error("保存person2失败", e.getMessage());
        }


        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            Student student = new Student();
            student.setSname("transactionManager" + System.currentTimeMillis());
            studentService.addStudent(student);
            //此时student没有入库,虽然调用的是另一个接口但是在此doInTransaction方法中保持了事务的一致性

            addPersonStudent();
            //addPersonStudent执行后没有入库,doInTransaction方法中保持了事务的一致性

            Person person3 = new Person();
            person3.setName("ctl-" + System.currentTimeMillis() + "-transactionTemplate");
            person3.setUpdatetime(new Date());
            person3.setStatus((byte) 1);
            int result = personMapper.insertSelective(person3);
            //此时虽然没有入库但是已经可以拿到自增主键
            logger.info("person3-result={},pid{}", result, person.getId());
            logger.info("" + 2 / new Random().nextInt(2)); //此句子执行后失败，事务回滚
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            logger.error("保存person3失败", e);
        }


        person = (person1 == null ? person2 : person1);
        logger.info(JSONObject.fromObject(person).toString());
        if (person1 != null && person2 != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean modifyPerson(Person person) {
        if (person == null || person.getId() == null){
            return false;
        } else {
            person.setUpdatetime(new Date());
            person.setStatus((byte)1);
            return personMapper.updateByPrimaryKeySelective(person)==1?true:false;
        }
    }

    @Override
    public Person delPerson(Integer pid) {
        Person person = personMapper.selectByPrimaryKey(pid);
        personMapper.deleteByPrimaryKey(pid);
        return person;
    }
}
