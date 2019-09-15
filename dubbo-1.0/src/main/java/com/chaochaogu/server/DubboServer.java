package com.chaochaogu.server;

import com.chaochaogu.registry.RegisterCenter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chaochao gu
 * @date 2019/9/15
 */
public class DubboServer {

    /**
     * 注册中心接口的引用
     */
    private RegisterCenter registerCenter;

    /**
     * 服务发布的地址
     */
    private String serviceAddress;

    private static final Map<String, Object> SERVICE_MAPPING = new ConcurrentHashMap<>();

    /**
     * 构造方法初始化两个成员变量
     *
     * @param registerCenter
     * @param serviceAddress
     */
    public DubboServer(RegisterCenter registerCenter, String serviceAddress) {
        this.registerCenter = registerCenter;
        this.serviceAddress = serviceAddress;
    }

    /**
     * 绑定服务名称和服务对象
     *
     * @param services
     */
    public void bindService(Object... services) {
        for (Object service : services) {
            DubboService dubboService = service.getClass().getAnnotation(DubboService.class);
            if (dubboService != null) {
                String serviceName = dubboService.value().getName();
                // com.chaochaogu.service.Uservice -> UserServiceImpl实例对象
                SERVICE_MAPPING.put(serviceName, service);
            }
        }
    }

    /**
     * 发布服务到注册中心，并且等待客户端调用
     */
    public void publish() {

        int port = Integer.parseInt(serviceAddress.split(":")[1]);

        // 把所有服务注册到注册中心上
        SERVICE_MAPPING.keySet().forEach(serviceName ->{
            registerCenter.registerService(serviceName, serviceAddress);
            System.out.println("服务注册成功 ->" + serviceName + serviceAddress);
        });

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            // 准备接收远程调用
            while (true){
                Socket socket = serverSocket.accept();
                // 开始执行
                new Thread(new ServerHandler(socket, SERVICE_MAPPING)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
