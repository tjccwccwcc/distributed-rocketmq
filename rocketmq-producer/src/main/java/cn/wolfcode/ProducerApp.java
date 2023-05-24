package cn.wolfcode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by lanxw
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.wolfcode.mapper")
public class ProducerApp {
    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class,args);
    }
}
