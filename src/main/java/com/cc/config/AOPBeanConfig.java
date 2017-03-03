package com.cc.config;

import com.cc.base.MethodTimeCount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by xn032607 on 2017/1/10.
 */
@Configuration
@EnableAspectJAutoProxy
public class AOPBeanConfig {

    @Bean
    public MethodTimeCount methodTimeCount() {
        return new MethodTimeCount();
    }
}
