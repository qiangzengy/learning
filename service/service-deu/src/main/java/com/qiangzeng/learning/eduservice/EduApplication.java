package com.qiangzeng.learning.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author qiangzeng
 * @date 2020/4/3 下午4:08
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.qiangzeng.learning"})
public class EduApplication {

    public static void main(String[] args) { SpringApplication.run(EduApplication.class,args); }
}
