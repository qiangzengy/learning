package com.qiangzeng.learning.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author qiangzeng
 * @date 2020/4/3 下午4:08
 */

@SpringBootApplication()
@ComponentScan(basePackages = {"com.qiangzeng.learning"})
@MapperScan("com.qiangzeng.learning.order.mapper")
public class OrderApplication {

    public static void main(String[] args) { SpringApplication.run(OrderApplication.class,args); }
}
