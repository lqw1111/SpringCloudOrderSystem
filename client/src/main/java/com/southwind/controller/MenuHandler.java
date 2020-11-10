package com.southwind.controller;

import com.southwind.entity.Menu;
import com.southwind.entity.MenuVO;
import com.southwind.feign.MenuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuHandler {

    @Autowired
    private MenuFeign menuFeign;

    @GetMapping("/findAll")
    @ResponseBody
    public MenuVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        int index = (page - 1) * limit;
        return menuFeign.findAll(index, limit);
    }

    @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location") String location) {
        return location;
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") int id) {
        menuFeign.deleteById(id);
        return "redirect:/menu/redirect/menu_manage";
    }

    @GetMapping("/findTypes")
    public ModelAndView findTypes() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("menu_add");
        modelAndView.addObject("list", menuFeign.findTypes());
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(Menu menu) {
        menuFeign.save(menu);
        return "redirect:/menu/redirect/menu_manage";
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("menu_update");
        modelAndView.addObject("menu", menuFeign.findById(id));
        modelAndView.addObject("list", menuFeign.findTypes());
        return modelAndView;
    }

    @PostMapping("/update")
    public String udpate(Menu menu) {
        menuFeign.update(menu);
        return "redirect:/menu/redirect/menu_manage";
    }
}
