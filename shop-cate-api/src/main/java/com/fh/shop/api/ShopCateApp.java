package com.fh.shop.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.fh.shop.api.cate.mapper"})
public class ShopCateApp {

    public static void main(String[] args) {
        //System.out.println("阳光彩虹小白马");
        SpringApplication.run(ShopCateApp.class,args);
    }
}
