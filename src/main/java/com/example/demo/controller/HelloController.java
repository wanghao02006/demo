package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wh on 2017/6/23.
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String hello(String name, Model model){
        model.addAttribute("message","hello " + name);
        return "resultPage";
    }
}
