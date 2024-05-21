package com.api.restaurant59;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String root() {
        return "Welcome to my Spring Boot";
    }

}
