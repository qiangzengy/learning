package com.qiangzeng.learning.servicevod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author qiangzeng
 * @date 2020/4/3 下午4:08
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.qiangzeng.learning"})
public class VodApplication {

    public static void main(String[] args) { SpringApplication.run(VodApplication.class,args); }
}
