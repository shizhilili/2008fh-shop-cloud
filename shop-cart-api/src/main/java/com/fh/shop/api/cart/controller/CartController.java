package com.fh.shop.api.cart.controller;

import com.fh.shop.api.BaseController;
import com.fh.shop.api.cart.biz.ICartService;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.common.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/carts")
@Api(tags = "购物车接口")
public class CartController extends BaseController {

    @Resource(name = "cartService")
    private ICartService cartService;

    @Autowired
    private HttpServletRequest request;


    @PostMapping("/addCart")
   @ApiImplicitParams({
           @ApiImplicitParam(name = "x-auth",value = "请求头",paramType = "header",required = true,dataType = "java.lang.String"),
            @ApiImplicitParam(name = "skuId",value = "商品id",required = true,dataType = "java.lang.Long"),
           @ApiImplicitParam(name = "count",value = "数量",required = true,dataType = "java.lang.Long")
    })
    @ApiOperation("添加商品到购物车")
    public ServerResponse addCart(Long skuId,Long count){

        MemberVo memberVo = buildMemberVo(request);
        Long id = memberVo.getId();
        return cartService.addCart(id,skuId,count);
    }



    @GetMapping("/findCart")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth",value = "请求头",paramType = "header",required = true,dataType = "java.lang.String"),
    })
    @ApiOperation("查询购物车")
    public ServerResponse findCart(){
        MemberVo memberVo = buildMemberVo(request);
        Long id = memberVo.getId();
        return cartService.findCart(id);
    }



    @PostMapping("/findCartCount")
    @ApiOperation("查看购物车商品数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth",value = "请求头",paramType = "header",required = true,dataType = "java.lang.String")
    })
    public ServerResponse findCartCount(){
        MemberVo memberVo = buildMemberVo(request);
        Long memberId = memberVo.getId();
        return cartService.findCartCount(memberId);
    }


    @PostMapping("/deleteCartSku")
    @ApiImplicitParams({
           @ApiImplicitParam(name = "x-auth",value = "请求头",paramType = "header",required = true,dataType = "java.lang.String"),
          @ApiImplicitParam(name = "skuId",value = "商品id",required = true,dataType = "java.lang.Long")
    })
    @ApiOperation("删除购物车商品")
    public ServerResponse deleteCartSku(Long skuId){
        MemberVo memberVo = buildMemberVo(request);
        Long id = memberVo.getId();
        return cartService.deleteCartSku(id,skuId);
    }


    @PostMapping("/deleteBatch")
    @ApiOperation("批量删除购物车商品")
   @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth",value = "请求头",paramType = "header",required = true,dataType = "java.lang.String"),
            @ApiImplicitParam(name = "ids",value = "批删",required = true,dataType = "java.lang.String")
    })
    public ServerResponse deleteBatch(String ids){
        MemberVo memberVo = buildMemberVo(request);
        Long memberId = memberVo.getId();
        return cartService.deleteBatch(memberId,ids);
    }






}
