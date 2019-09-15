package com.chaochaogu.proxy;

import com.chaochaogu.client.DubboRequest;
import com.chaochaogu.client.SocketClient;
import com.chaochaogu.discovery.ServiceDiscovery;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author chaochao gu
 * @date 2019/9/15
 */
public class DubboInvocationHandler implements InvocationHandler {

    private ServiceDiscovery serviceDiscovery;

    public DubboInvocationHandler(ServiceDiscovery serviceDiscovery){
        this.serviceDiscovery = serviceDiscovery;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 得到方法所在的类如果是Object，直接调用，不做拦截处理
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }

        // 发现远程服务
        List<String> serviceAddresses = serviceDiscovery.discoverService(method.getDeclaringClass().getName());

        // 封装请求参数
        DubboRequest dubboRequest = new DubboRequest();
        dubboRequest.setMethodName(method.getName());
        dubboRequest.setParameters(args);
        dubboRequest.setServiceInterfaceName(method.getDeclaringClass().getName());

        //加一个负载均衡，比如 随机、轮询

        // 发起远程调用
        SocketClient socketClient = new SocketClient(serviceAddresses.get(0));
        return socketClient.remoteInvoke(dubboRequest);
    }
}
