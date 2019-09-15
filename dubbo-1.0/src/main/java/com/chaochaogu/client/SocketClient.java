package com.chaochaogu.client;

import java.io.*;
import java.net.Socket;

/**
 * @author chaochao gu
 * @date 2019/9/15
 */
public class SocketClient {

    private String serviceAddresses;

    public SocketClient(String serviceAddresses) {
        this.serviceAddresses = serviceAddresses;
    }

    /**
     * 创建Socket
     *
     * @return
     */
    public Socket createSocket() {

        String ip = serviceAddresses.split(":")[0];

        int port = Integer.parseInt(serviceAddresses.split(":")[1]);

        Socket socket = null;
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }

    /**
     * 调用服务提供者（远程调用）
     *
     * @param dubboRequest
     * @return
     */
    public Object remoteInvoke(DubboRequest dubboRequest) {

        Socket socket = createSocket();

        OutputStream os = null;
        try {
            // 从socket获取一个输出流，用于向远程写数据
            os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(dubboRequest);
            oos.flush();

            // 从远程服务端读取返回的数据
            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            Object result = ois.readObject();
            return result;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // TODO 关闭流
        }
        return null;
    }
}
