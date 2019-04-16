package com.ctl.spring.cloud.springcloudctl.bean;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Person implements Serializable{
    private static final long serialVersionUID = 1256066108192616042L;
    @Value("${person.age}")
    private Integer age;
    @Value("${person.name}")
    private String name;
    @Value("${person.salay}")
    private Long salay;
    @Value("${person.uuid}")
    private String uuid;
    private Integer port;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSalay() {
        return salay;
    }

    public void setSalay(Long salay) {
        this.salay = salay;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Person() {
    }

    public Person(Integer age, String name, Long salay, String uuid) {
        this.age = age;
        this.name = name;
        this.salay = salay;
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(age, person.age) &&
                Objects.equals(name, person.name) &&
                Objects.equals(salay, person.salay) &&
                Objects.equals(uuid, person.uuid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(age, name, salay, uuid);
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", salay=" + salay +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
