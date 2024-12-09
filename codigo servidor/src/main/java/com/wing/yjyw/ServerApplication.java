package com.wing.yjyw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
/*
    启动一个基于 Spring Boot 的 Web 应用程序。
    自动加载所需的配置（如数据库、Web 服务等）。
    支持 @Scheduled 注解的定时任务。
    扫描 @WebServlet、@WebFilter 和 @WebListener 注解，方便管理自定义的 servlet、过滤器和监听器。
    启用依赖注入、控制反转（IoC）等 Spring 框架的核心功能。
*/
@SpringBootApplication
@ServletComponentScan
@EnableScheduling
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
