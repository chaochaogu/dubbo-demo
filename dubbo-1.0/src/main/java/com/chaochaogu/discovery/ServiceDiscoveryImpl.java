package com.chaochaogu.discovery;

import com.chaochaogu.constant.Constants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

import java.util.List;

/**
 * @author chaochao gu
 * @date 2019/9/15
 */
public class ServiceDiscoveryImpl implements ServiceDiscovery {

    /**
     * 连接zookeeper客户端对象
     */
    private CuratorFramework curatorFramework = null;

    /**
     * 构造方法连接zookeeper
     */
    public ServiceDiscoveryImpl(){

        // 重试策略
        RetryNTimes retryNTimes = new RetryNTimes(3, 2000);

        // 创建zookeeper连接，新版本
        // CuratorFrameworkFactory.newClient(Constants.ZK_ADDRESS, retryNTimes);

        // 老版本创建连接客户端
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(Constants.ZK_ADDRESS).
                connectionTimeoutMs(10000).
                sessionTimeoutMs(5000).
                retryPolicy(retryNTimes).
                build();

        // 启动客户端
        curatorFramework.start();
        System.out.println("连接zookeeper成功...");
    }

    @Override
    public List<String> discoverService(String serviceName) {

        // Zk节点：/rpc/com.chaochaogu.service.UserServcie
        String serviceNode = Constants.NAMESPACE + "/" + serviceName;
        try {
            List<String> childNodes = curatorFramework.getChildren().forPath(serviceNode);
            // 暂时取第一个，简化一下
            return childNodes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
