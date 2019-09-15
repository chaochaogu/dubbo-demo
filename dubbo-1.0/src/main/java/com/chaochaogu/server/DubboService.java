package com.chaochaogu.server;

import java.lang.annotation.*;

/**
 * @author chaochao gu
 * @date 2019/9/15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface DubboService {

    Class value();
}
