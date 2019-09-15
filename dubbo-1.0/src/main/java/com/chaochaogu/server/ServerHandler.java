package com.chaochaogu.server;

import com.chaochaogu.client.DubboRequest;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chaochao gu
 * @date 2019/9/15
 */
public class ServerHandler implements Runnable {

    private Socket socket;

    private Map<String, Object> serviceMapping;

    public ServerHandler(Socket socket, Map<String, Object> serviceMapping) {
        this.socket = socket;
        this.serviceMapping = serviceMapping;
    }

    @Override
    public void run() {

        try {
            // 读取客户端发送过来的数据
            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            try {
                DubboRequest dubboRequest = (DubboRequest) ois.readObject();
                // 调用真正的实现方法
                Object result = invokeTarget(dubboRequest);
                // 向客户端返回结果
                OutputStream os = socket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(result);
                oos.flush();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // TODO 关闭流资源
        }
    }

    /**
     * 真正调用目标实现类的方法
     *
     * @param dubboRequest
     * @return
     */
    private Object invokeTarget(DubboRequest dubboRequest) {

        // 调用方法的参数
        Object[] parameters = dubboRequest.getParameters();

        // 调用方法的参数类型
        Class[] parameterTypes = null;
        if (parameters != null) {
            parameterTypes = new Class[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                parameterTypes[i] = parameters[i].getClass();
            }
        }
        // UserService
        Object serviceImpl = serviceMapping.get(dubboRequest.getServiceInterfaceName());
        try {
            Method method = null;
            try {
                method = serviceImpl.getClass().getMethod(dubboRequest.getMethodName(), parameterTypes);
                // 反射调用真正的目标方法
                Object result = method.invoke(serviceImpl, parameters);
                // 返回结果
                return result;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
