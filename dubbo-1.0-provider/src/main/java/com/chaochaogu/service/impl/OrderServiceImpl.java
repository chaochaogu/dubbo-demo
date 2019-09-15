package com.chaochaogu.service.impl;

import com.chaochaogu.server.DubboService;
import com.chaochaogu.service.OrderService;

/**
 * @author chaochao gu
 * @date 2019/9/15
 */
@DubboService(OrderService.class)
public class OrderServiceImpl implements OrderService {

    public String order() {
        return "order";
    }
}
