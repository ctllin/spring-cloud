package com.ctl.springboot.atomikos.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created with Intellij IDEA.
 * @author ctl
 * @version 2019/03/14
 */
@Data
@TableName(value = "member")//指定表名
public class Member {

    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    //value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
    //@TableId(value = "id",type = IdType.AUTO)//指定自增策略
    //private Integer id;

    //若没有开启驼峰命名，或者表中列名不符合驼峰规则，可通过该注解指定数据库表中的列名，exist标明数据表中有没有对应列
    //@TableField(value = "last_name",exist = true)
    //private String lastName;

    private String name;

    private String mobile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
