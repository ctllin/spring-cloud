package com.neo.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController {

    @Value("${person.name}")
    private String name;

    @RequestMapping("/name")
    public String from() {
        return this.name;
    }
}