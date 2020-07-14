package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author : yuzebo <511729587@qq.com>
 * @Date : 2020/7/11 - 10:22 下午 - 星期六
 * @Package : com.changgou
 * @ProjectName : changgou
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
    /*
    加载启动类为springboot配置标准
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);
    }
}
