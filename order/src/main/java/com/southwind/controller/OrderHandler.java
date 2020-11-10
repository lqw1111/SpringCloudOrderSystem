package com.southwind.controller;

import com.netflix.discovery.converters.Auto;
import com.southwind.entity.Order;
import com.southwind.entity.OrderVO;
import com.southwind.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderHandler {

    @Value("${server.port}")
    private String port;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/index")
    public String index() {
        return "order port: " + this.port;
    }

    @PostMapping("/save")
    public void save(@RequestBody Order order) {
        order.setDate(new Date(System.currentTimeMillis()));
        orderRepository.save(order);
    }

    @GetMapping("/findAllByUid/{index}/{limit}/{uid}")
    public OrderVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit, @PathVariable("uid") int uid){
        OrderVO orderVO = new OrderVO();
        orderVO.setMsg("");
        orderVO.setCode(0);
        orderVO.setCount(orderRepository.countByUid(uid));
        orderVO.setData(orderRepository.findAllByUid(index, limit, uid));
        return orderVO;
    }

    @GetMapping("/countByUid/{uid}")
    public int countByUid(@PathVariable("uid") int uid) {
        return orderRepository.countByUid(uid);
    }

    @GetMapping("/findAll/{index}/{limit}")
    public OrderVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit) {
        OrderVO orderVO = new OrderVO();
        orderVO.setCount(0);
        orderVO.setCount(orderRepository.countByState());
        orderVO.setData(orderRepository.findAllByState(index, limit, 0));
        return orderVO;
    }

    @GetMapping("/updateState/{id}")
    public void updateState(@PathVariable("id") int id) {
        orderRepository.updateState(id);
    }
}
