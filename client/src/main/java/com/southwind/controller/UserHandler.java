package com.southwind.controller;

import com.southwind.entity.User;
import com.southwind.entity.UserVO;
import com.southwind.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserHandler {
    @Autowired
    private UserFeign userFeign;

    @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location") String location) {
        return location;
    }

    @GetMapping("/findAll")
    @ResponseBody
    public UserVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        int index = (page - 1) * limit;
        return new UserVO(0, "", userFeign.count(), userFeign.findAll(index, limit));
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") int id) {
        return userFeign.findById(id);
    }

    @GetMapping("/count")
    public int count() {
        return userFeign.count();
    }

    @PostMapping("/save")
    public String save(User user) {
        userFeign.save(user);
        return "redirect:/user/redirect/user_manage";
    }

    @PutMapping("/update")
    public String update(@RequestBody User user) {
        userFeign.update(user);
        return "redirect:/user/redirect/user_manage";
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") int id) {
        userFeign.deleteById(id);
        return "redirect:/user/redirect/user_manage";
    }
}
