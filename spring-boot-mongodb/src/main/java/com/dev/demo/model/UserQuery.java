package com.dev.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * <p>Title: User</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-06-05 14:06
 */
@Data
@AllArgsConstructor
@ToString
public class UserQuery {
    @Id
    private Long id;
    private String name;
    private Integer age;
    private Integer currentPage;
    private Integer pageSize;
    public UserQuery() {
    }

  /*  public User(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCurrentPage() {
        if(currentPage==null){
            currentPage=1;

        }else if(currentPage<=0){
            currentPage=1;
        }
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        if(pageSize==null){
            pageSize=10;

        }else if(pageSize<=0){
            pageSize=10;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}