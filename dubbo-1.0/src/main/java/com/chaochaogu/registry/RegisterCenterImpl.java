package com.chaochaogu.registry;

import com.chaochaogu.constant.Constants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

/**
 * @author chaochao gu
 * @date 2019/9/15
 */
public class RegisterCenterImpl implements RegisterCenter {

    /**
     * zookeeper客户端对象
     */
    private CuratorFramework curatorFramework = null;

    /**
     * 构造方法初始化与zookeeper的连接
     */
    public RegisterCenterImpl() {

        // 重试策略
        RetryNTimes retryNTimes = new RetryNTimes(3, 2000);

        // 创建zookeeper连接，新版本
        // CuratorFramework client = CuratorFrameworkFactory.newClient(Constants.ZK_ADDRESS, retryNTimes);

        // 老版本创建连接客户端
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(Constants.ZK_ADDRESS).
                sessionTimeoutMs(5000).
                connectionTimeoutMs(10000).
                retryPolicy(retryNTimes).
                build();

        // 启动客户端
        curatorFramework.start();

        System.out.println("连接zookeeper成功...");
    }

    /**
     * 注册中心注册服务接口实现
     *
     * @param serviceName
     * @param serviceAddress
     */
    @Override
    public void registerService(String serviceName, String serviceAddress) {

        // 服务节点：/rpc/com.chaochaogu.service.UserService
        String serviceNode = Constants.NAMESPACE + "/" + serviceName;

        try {
            if (curatorFramework.checkExists().forPath(serviceNode) == null) {
                // 服务节点不存在则新建
                curatorFramework.create().
                        creatingParentsIfNeeded().
                        withMode(CreateMode.PERSISTENT).
                        forPath(serviceNode);
            }

            // 地址节点：/rpc/com.chaochaogu.service.UserService/127.0.0.1:20990
            String addressNode = serviceNode + "/" + serviceAddress;
            if (curatorFramework.checkExists().forPath(addressNode) == null) {
                // 地址节点不存在则新建
                curatorFramework.create().
                        creatingParentsIfNeeded().
                        withMode(CreateMode.EPHEMERAL).
                        forPath(addressNode);
            }
            System.out.println("服务注册完毕 ->" + serviceName + "服务地址" + addressNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
