package com.ls;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.ls.mapper")
@EnableDubbo(scanBasePackages = "com.ls.service")
public class InventoryServiceApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(InventoryServiceApp.class,args);
    }
}
