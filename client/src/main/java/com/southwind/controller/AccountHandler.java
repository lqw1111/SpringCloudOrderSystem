package com.southwind.controller;

import com.southwind.entity.Admin;
import com.southwind.entity.User;
import com.southwind.feign.AccountFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("/account")
public class AccountHandler {

    @Autowired
    private AccountFeign accountFeign;

    @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location") String location) {
        return location;
    }

    @PostMapping("/login")
    public String login(@RequestParam("username")String username, @RequestParam("password") String password, @RequestParam("type") String type, HttpSession session) {
        Object object = accountFeign.login(username, password, type);
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) object;
        String result = null;
        if (object == null) {
            result = "login";
        } else {
            switch (type) {
                case "user" :
                    User user = new User(
                            Long.parseLong(map.get("id") + ""),
                            (String)map.get("username"),
                            (String)map.get("password"),
                            (String)map.get("nickname"),
                            (String)map.get("gender"),
                            (String)map.get("telephone"),
                            null,
                            (String)map.get("address"));
                    session.setAttribute("user", user);
                    result = "index";
                    break;
                case "admin" :
                    Admin admin = new Admin(Long.parseLong(map.get("id") + ""),
                            (String)map.get("username"),
                            (String)map.get("password"));
                    session.setAttribute("admin", admin);
                    result = "main";
                    break;
            }
        }
        return result;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.html";
    }
}
