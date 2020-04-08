package com.qiangzeng.learning.cmsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author qiangzeng
 * @date 2020/4/3 下午4:08
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.qiangzeng.learning"})
@MapperScan("com.qiangzeng.learning.cmsservice.mapper")
public class CmsApplication {

    public static void main(String[] args) { SpringApplication.run(CmsApplication.class,args); }
}
