package com.leaf.application.controller;

import com.leaf.mvc.annotations.Controller;
import com.leaf.mvc.annotations.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/user")
    public String getUser() {
        return "hello";
    }
}
