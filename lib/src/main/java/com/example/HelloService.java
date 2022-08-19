package com.example;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String hello = "hello";

    public String getHello() {
        return hello;
    }
}
