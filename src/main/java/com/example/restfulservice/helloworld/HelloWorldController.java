package com.example.restfulservice.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    
    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello-world")
    public String helloworld(){
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloworldbean(){
        return new HelloWorldBean ("Hello World");
    }

    @GetMapping("/hello-name-bean/path-variable/{name}")
    public HelloWorldBean hellonamebean(@PathVariable String name){
        return new HelloWorldBean (String.format("Hello, %s", name));
    }

    @GetMapping("/hello-world-internationalized")
    public String helloworldinternationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale){
        return messageSource.getMessage("good.morning.message", null, locale);
    }

}
