package com.mohammadali.springboot.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping({"/"})
public class HomeController {


    @RequestMapping({"/"})
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout" , required = false) String logout , Model model){

        if (error!=null){
            model.addAttribute("error","username and password is invalid");
        }

        if (logout!=null){
            model.addAttribute("msg","You hava logout successfully");
        }

        return "login";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request , HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth !=null)
            new SecurityContextLogoutHandler().logout(request , response , auth);

        cancelCookie(request , response);

        return "redirect:/login?logout";
    }

    void cancelCookie(HttpServletRequest request, HttpServletResponse response){
        String cookieName = "remember-me";
        Cookie cookie = new Cookie(cookieName,null);
        cookie.setPath(StringUtils.hasLength(request.getContextPath()) ? request.getContextPath():"/");
        response.addCookie(cookie);
    }

    @GetMapping("/about")
    public String aboutPage(){
        return "about";
    }

    @GetMapping("/contact")
    public String contactPage(){
        return "contact";
    }
}
