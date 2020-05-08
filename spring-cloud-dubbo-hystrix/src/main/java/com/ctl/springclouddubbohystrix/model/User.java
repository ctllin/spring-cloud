package com.ctl.springclouddubbohystrix.model;

import lombok.Data;

/**
 * <p>Title: User</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.1
 * @date 2020-05-08 09:34
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}