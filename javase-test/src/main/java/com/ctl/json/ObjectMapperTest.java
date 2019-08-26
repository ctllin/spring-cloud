package com.ctl.json;

/**
 * <p>Title: ObjectMapperTest</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-08-24 17:20
 */

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.print.attribute.standard.JobOriginatingUserName;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


class Student {

    private Integer uid;
    private String uname;
    private String upwd;
    private Integer number;
    private Boolean isstudent;
    public Integer getUid() {
        return uid;
    }
    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getUpwd() {
        return upwd;
    }
    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public Boolean getIsstudent() {
        return isstudent;
    }
    public void setIsstudent(Boolean isstudent) {
        this.isstudent = isstudent;
    }
    @Override
    public String toString() {
        return "Student [uid=" + uid + ", uname=" + uname + ", upwd=" + upwd
                + ", number=" + number + ", isstudent=" + isstudent + "]";
    }

}
 class Jackson {



}
public class ObjectMapperTest {
    public  static JsonGenerator jsonGenerator = null;
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        Student student = new Student();
        student.setIsstudent(true);
        student.setUid(1000);
        student.setUname("xiao liao");
        student.setUpwd("123");
        student.setNumber(12);

        Map<String, Student> stuMap = new HashMap<String, Student>();
        stuMap.put("1", student);
        stuMap.put("2", student);

        List<Object> stuList = new ArrayList<Object>();
        List<Student> stuList1 = new ArrayList<Student>();
        stuList1.add(student);
        student=  new  Student();
        student.setIsstudent(false);
        student.setUid(200);
        student.setUname("xiao mi");
        stuList1.add(student);

        stuList.add(student);
        stuList.add("xiao xin");
        stuList.add("xiao er");
        stuList.add(stuMap);
        try {
            String message = mapper.writeValueAsString(student);

            System.out.println(message);
            System.out.println(JSON.toJSONString(student));
            Set<String> setAll = new HashSet<>();
            for (int i = 0; i <10000 ; i++) {
                Student finalStudent = new Student();
                finalStudent.setNumber(i);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String message = null;
                        try {
                            message = mapper.writeValueAsString(finalStudent);
                            System.out.println(finalStudent.getNumber()+"\t"+message);
                        } catch (JsonProcessingException e) {
                            System.out.println("errrrr-----------------------------");
                        }
                        setAll.add(message);
                    }
                }).start();
            }
            System.out.println(setAll.size());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //  readJson2List();
        //  readJson2Array();
        try {

            //writeArray2Json(array);
            writeJson2List();
            //writeEntity2Json(student);
            //writeJson2Entity();
            //writeMap2Json(stuMap);
            //writeList2Json(stuList1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * <code>writeEntity2Json</code>
     * @description: TODO(实体类转换成json)
     * @param object
     * @throws IOException
     * @since   2011-11-8     廖益平
     */
    public static void writeEntity2Json(Object object) throws IOException {
        mapper.writeValue( new File("D:\\developSoft\\aaadowload\\testjson1\\lib\\aa.txt"),object );
        mapper.writeValue( System.out,object );

    }
    /**
     *
     * <code>writeArray2Json</code>
     * @description: TODO(数组转换成json数组)
     * @param object
     * @throws IOException
     * @since   2011-11-8     廖益平
     */
    public static void writeArray2Json(Object object) throws IOException {

        // writeValue具有和writeObject相同的功能
        mapper.writeValue( new File("D:\\developSoft\\aaadowload\\testjson1\\lib\\aa.txt"),object );
        mapper.writeValue(System.out,object );

    }
    /**
     *
     * <code>writeMap2Json</code>
     * @description: TODO(map对象转换成json对象)
     * @param object
     * @throws IOException
     * @since   2011-11-8     廖益平
     */
    public static void writeMap2Json(Object object) throws IOException {

        System.out.println("使用ObjectMapper-----------");
        // writeValue具有和writeObject相同的功能
        System.out.println("==>"+mapper.writeValueAsString(object));
        mapper.writeValue( new File("D:\\developSoft\\aaadowload\\testjson1\\lib\\aamap.txt"),object );
        mapper.writeValue( System.out , object );
    }
    /**
     *
     * <code>writeList2Json</code>
     * @description: TODO(list转换成json)
     * @param object
     * @throws IOException
     * @since   2011-11-8     廖益平
     */
    public static void writeList2Json(Object object) throws IOException {
        System.out.println("==>"+mapper.writeValueAsString(object));
        mapper.writeValue( new File("D:\\developSoft\\aaadowload\\testjson1\\lib\\aamap.txt"),object );
        mapper.writeValue( System.out , object );
    }
    /**
     *
     * <code>writeJson2Entity</code>
     * @description: TODO(json转换成实体)
     * @throws IOException
     * @since   2011-11-8     廖益平
     */
    public static void writeJson2Entity() throws IOException {
        System.out.println("json串转换成entity-------------");
//       File file = new File("D:\\developSoft\\aaadowload\\testjson1\\lib\\aa.txt");
//       FileInputStream inputStream = new FileInputStream(file);
//       Student student = mapper.readValue(inputStream,Student.class);
//       System.out.println(student.toString());
        //漂亮输出
        //mapper.defaultPrettyPrintingWriter().writeValueAsString(value);

        String json = "{\"uid\":1000,\"uname\":\"xiao liao\",\"upwd\":\"123\",\"number\":12.0,\"isstudent\":true}";
        Student student1 = mapper.readValue(json,Student.class);
        System.out.println("json2:"+student1.toString());
    }
    /**
     *
     * <code>writeJson2List</code>
     * @description: TODO(json专程list对象)
     * @throws IOException
     * @since   2011-11-8     廖益平
     */
    public static void writeJson2List() throws IOException {
        System.out.println("json串转换成entity-------------");
        File file = new File("D:\\aa.txt");
        FileInputStream inputStream = new FileInputStream(file);
        Student student = mapper.readValue(inputStream,Student.class);
        System.out.println(student.toString());

        String json = "[{\"uid\":1000,\"uname\":\"xiao liao\",\"upwd\":\"123\",\"number\":12.0,\"isstudent\":true},{\"uid\":200,\"uname\":\"xiao mi\",\"upwd\":null,\"number\":0.0,\"isstudent\":false}]";
        List<LinkedHashMap<String, Object>> s= mapper.readValue(json,List.class);
        for (int i = 0; i < s.size(); i++) {
            LinkedHashMap<String, Object> link = s.get(i);
            Set<String>  key = link.keySet();
            for (Iterator iterator = key.iterator(); iterator.hasNext();) {
                String string = (String) iterator.next();
                System.out.println(string+"==>"+link.get(string));

            }
            System.out.println("json:"+i+""+s.get(i).toString());

        }
    }
    /**
     * JSON转换为List对象
     */
    public static void readJson2List() {
        String json = "[{\"uid\":1,\"uname\":\"www\",\"number\":234,\"upwd\":\"456\"},"
                + "{\"uid\":5,\"uname\":\"tom\",\"number\":3.44,\"upwd\":\"123\"}]";
        try {
            List<LinkedHashMap<String, Object>> list = mapper.readValue(json, List.class);
            System.out.println(list.size());
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                Set<String> set = map.keySet();
                for (Iterator<String> it = set.iterator(); it.hasNext();) {
                    String key = it.next();
                    System.out.println(key + ":" + map.get(key));
                }
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * JSON转换为List对象
     */
    public static void readJson2Array() {
        String json = "[{\"uid\":1,\"uname\":\"www\",\"number\":234,\"upwd\":\"456\"},"
                + "{\"uid\":5,\"uname\":\"tom\",\"number\":3.44,\"upwd\":\"123\"}]";
        try {
            Student[] students = mapper.readValue(json, Student[].class);
            for (Student student : students) {
                System.out.println(">"+student.toString());
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
