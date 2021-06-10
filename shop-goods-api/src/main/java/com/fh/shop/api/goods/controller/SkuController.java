package com.fh.shop.api.goods.controller;

import com.fh.shop.api.goods.biz.ISkuService;
import com.fh.shop.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")

public class SkuController {

    @Resource(name = "skuService")
    private ISkuService skuService;
    @Value("${server.port}")
    private String port;

    @GetMapping("/skus/status")
    public ServerResponse findStatusList(){
        return skuService.findStatusList();
    }

    @GetMapping("/skus/findSku")
    public ServerResponse findSku(@RequestParam("id") Long id){

        return skuService.findSku(id);
    }



}
