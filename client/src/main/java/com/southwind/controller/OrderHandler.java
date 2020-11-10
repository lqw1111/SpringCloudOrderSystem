package com.southwind.controller;

import com.southwind.entity.Menu;
import com.southwind.entity.Order;
import com.southwind.entity.OrderVO;
import com.southwind.entity.User;
import com.southwind.feign.OrderFeign;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/order")
public class OrderHandler {

    @Autowired
    private OrderFeign orderFeign;

    @GetMapping("/save/{mid}")
    public String save(@PathVariable("mid") int mid, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Order order = new Order();
        order.setUser(user);
        Menu menu = new Menu();
        menu.setId(mid);
        order.setMenu(menu);
        order.setState(0);
        order.setDate(new Date(System.currentTimeMillis()));
        orderFeign.save(order);
        return "order";
    }

    @GetMapping("/findAllByUid")
    @ResponseBody
    public OrderVO findAllByUid(@RequestParam("page") int page, @RequestParam("limit") int limit, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int index = (page - 1) * limit;
        return orderFeign.findAllByUid(index, limit, (int)user.getId());
    }

    @GetMapping("/findAll")
    @ResponseBody
    public OrderVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        int index = (page - 1) * limit;
        return orderFeign.findAll(index, limit);
    }

    @GetMapping("/updateState/{id}")
    public String updateState(@PathVariable("id") int id) {
        orderFeign.updateState(id);
        return "redirect:/menu/redirect/order_handler";
    }
}
