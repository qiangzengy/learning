package com.qiangzeng.learning.ucservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author qiangzeng
 * @date 2020/4/3 下午4:08
 */

@SpringBootApplication()
@ComponentScan(basePackages = {"com.qiangzeng.learning"})
@MapperScan("com.qiangzeng.learning.ucservice.mapper")
public class UceApplication {

    public static void main(String[] args) { SpringApplication.run(UceApplication.class,args); }
}
