package com.chaochaogu.client;

import java.io.Serializable;

/**
 * @author chaochao gu
 * @date 2019/9/15
 */
public class DubboRequest implements Serializable {

    private Object[] parameters;

    private String methodName;

    private String serviceInterfaceName;

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getServiceInterfaceName() {
        return serviceInterfaceName;
    }

    public void setServiceInterfaceName(String serviceInterfaceName) {
        this.serviceInterfaceName = serviceInterfaceName;
    }
}
