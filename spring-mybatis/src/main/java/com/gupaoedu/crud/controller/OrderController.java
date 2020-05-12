package com.gupaoedu.crud.controller;

import com.gupaoedu.crud.bean.Order;
import com.gupaoedu.crud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;


    @RequestMapping("query/orders")
    @ResponseBody
    public List<Order> queryList(){
        return  orderService.queryList();
    }
}
