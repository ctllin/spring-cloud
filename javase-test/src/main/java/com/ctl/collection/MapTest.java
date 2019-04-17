package com.ctl.collection;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    static class School{
        private String address;

        public School(String address) {
            this.address = address;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "School{" +
                    "address='" + address + '\'' +
                    '}';
        }
    }
    static class  Person{
        private String name;
        private School school;

        public School getSchool() {
            return school;
        }

        public void setSchool(School school) {
            this.school = school;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Person(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", school=" + school +
                    '}';
        }
    }
    public static void main(String[] args) {
        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("key1","value1");
        map1.put("key2","value2");
        Person person = new Person("per1");
        School school = new School("sch1");
        person.setSchool(school);
        map1.put("person",person);
        Map<String,Object> map2 = (Map<String, Object>) map1.clone();
        System.out.println(map1);
        System.out.println(map2);
        map2.put("key2","vvvvv2");
       // map2.put("key3","v3");
        Person personMapGet = (Person) map2.get("person");
        personMapGet.setName("person2");
        personMapGet.getSchool().setAddress("sch2");
        System.out.println(map1);
        System.out.println(map2);
    }
}