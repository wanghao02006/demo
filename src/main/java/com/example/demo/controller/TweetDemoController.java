package com.example.demo.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wh on 2017/6/30.
 */

@Controller
@RequestMapping("/tweet")
public class TweetDemoController {

    @RequestMapping
    public String home(){
        return "searchPage";
    }

    @RequestMapping(value = "/postSearch",method = RequestMethod.POST)
    public String postSearch(HttpServletRequest request, RedirectAttributes redirectAttributes){
        String search = request.getParameter("search");
        if(search.toLowerCase().contains("struts")){
            redirectAttributes.addFlashAttribute("error","Try using spring instead!");
            return "redirect:";
        }
        redirectAttributes.addAttribute("search",search);
        return "redirect:search";
    }

    @RequestMapping("/search")
    public String tweetList(@RequestParam(value = "search",defaultValue = "java") String word, Model model){
        List<String> result = Arrays.asList("Java","C++","C#","scala","python");

        model.addAttribute("result",result);
        model.addAttribute("search",word);
        return "tweetList";
    }
}
