package com.example.demo.controller;

import com.example.demo.dto.ProfileForm;
import com.example.demo.formatter.CNLocalDateFormatter;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by wh on 2017/7/3.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @ModelAttribute("dateFormat")
    public String localeFormat(Locale locale){
        return CNLocalDateFormatter.getCnPattern(locale);
    }

    @RequestMapping("")
    public String displayProfile(ProfileForm profileForm){
        return "profile/profilePage";
    }

    @RequestMapping(value = "",params = {"save"},method = RequestMethod.POST)
    public String saveProfile(@Valid ProfileForm profileForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "profile/profilePage";
        }
        System.out.println("save ok" + profileForm);
        return "redirect:";
    }

    @RequestMapping(value = "",params = {"addTaste"})
    public String addRow(ProfileForm profileForm){
        profileForm.getTastes().add(null);
        return "profile/profilePage";
    }

    @RequestMapping(value = "",params = {"removeTaste"})
    public String removeRow(ProfileForm profileForm, HttpServletRequest request){
        Integer rowId = Integer.valueOf(request.getParameter("removeTaste"));
        profileForm.getTastes().remove(rowId.intValue());
        return "profile/profilePage";
    }

}
