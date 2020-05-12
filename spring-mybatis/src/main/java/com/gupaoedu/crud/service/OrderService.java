package com.gupaoedu.crud.service;

import com.gupaoedu.crud.bean.Order;
import com.gupaoedu.crud.dao.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public List<Order> queryList(){

        return orderMapper.selectByExample(null);
    }
}
