package com.sy.autochess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description TODO
 * @Author sy
 * @Version 1.0
 * @Time 2019/5/6 10:32
 **/
@SpringBootApplication
public class AppMain {
    public static void main(String[] args) {
        // 内嵌 tomcat 端口 请查看 application.yml
        SpringApplication.run(AppMain.class, args);
    }
}
