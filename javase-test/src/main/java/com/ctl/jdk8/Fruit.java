package com.ctl.jdk8;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>Title: Fruit</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-08-26 11:13
 */
@Getter
@Setter
@ToString
public class Fruit {
    private String name;
    private Double price;

    public Fruit(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fruit fruit = (Fruit) o;
        return java.util.Objects.equals(name, fruit.name) &&
                java.util.Objects.equals(price, fruit.price);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, price);
    }
    // 注意equals和hashCode必须成对出现
}