package com.chaochaogu;

import com.chaochaogu.discovery.ServiceDiscovery;
import com.chaochaogu.discovery.ServiceDiscoveryImpl;
import com.chaochaogu.model.User;
import com.chaochaogu.proxy.DubboServiceProxy;
import com.chaochaogu.service.OrderService;
import com.chaochaogu.service.UserService;

/**
 * 基于TCP方式 -> java.net.socket
 * 基于HTTP方式调用 -> java.net.URL
 *
 * @author chaochao gu
 * @date 2019/9/15
 */
public class Consumer {

    public static void main(String[] args) {

        // 初始化与zookeeper连接，从注册中心找服务
        ServiceDiscovery serviceDiscovery = new ServiceDiscoveryImpl();

        // 创建一个UserService接口的代理类对象，用这个代理类对象去调用远程服务
        DubboServiceProxy dubboServiceProxy = new DubboServiceProxy(serviceDiscovery);

        //拿到一个service代理对象
        UserService userService = dubboServiceProxy.getServiceProxy(UserService.class);

        // 调用方法（远程调用）
        User user = userService.getUserById(1);

        System.out.println(user);

        OrderService orderService = dubboServiceProxy.getServiceProxy(OrderService.class);

        System.out.println(orderService.order());
    }
}
