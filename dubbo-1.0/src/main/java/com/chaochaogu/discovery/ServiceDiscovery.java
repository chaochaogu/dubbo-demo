package com.chaochaogu.discovery;

import java.util.List;

/**
 * @author chaochao gu
 * @date 2019/9/15
 */
public interface ServiceDiscovery {

    List<String> discoverService(String serviceName);
}
