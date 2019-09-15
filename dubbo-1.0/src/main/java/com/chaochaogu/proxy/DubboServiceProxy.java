package com.chaochaogu.proxy;

import com.chaochaogu.discovery.ServiceDiscovery;
import com.chaochaogu.service.UserService;

import java.lang.reflect.Proxy;

/**
 * @author chaochao gu
 * @date 2019/9/15
 */
public class DubboServiceProxy {

    private ServiceDiscovery serviceDiscovery;

    public DubboServiceProxy(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    @SuppressWarnings("unchecked")
    public <T> T getServiceProxy(Class<T> interfaces) {
        return (T) Proxy.newProxyInstance(interfaces.getClassLoader(),
                new Class<?>[]{interfaces},
                new DubboInvocationHandler(serviceDiscovery));
    }
}
