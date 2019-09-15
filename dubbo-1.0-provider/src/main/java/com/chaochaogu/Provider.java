package com.chaochaogu;

import com.chaochaogu.registry.RegisterCenter;
import com.chaochaogu.registry.RegisterCenterImpl;
import com.chaochaogu.server.DubboServer;
import com.chaochaogu.service.impl.OrderServiceImpl;
import com.chaochaogu.service.impl.UserServiceImpl;

/**
 * @author chaochao gu
 * @date 2019/9/15
 */
public class Provider {

    public static void main(String[] args) {

        // 初始化与zookeeper连接
        RegisterCenter registerCenter = new RegisterCenterImpl();

        // 创建dubbo服务对象 serviceAddress= 127.0.0.1:20990 ,IP需要获取一下
        DubboServer dubboServer = new DubboServer(registerCenter, "127.0.0.1:20990");

        // 绑定服务
        dubboServer.bindService(new UserServiceImpl());

        dubboServer.bindService(new OrderServiceImpl());

        // 服务发布（把服务注册到注册中心）
        dubboServer.publish();
    }
}
