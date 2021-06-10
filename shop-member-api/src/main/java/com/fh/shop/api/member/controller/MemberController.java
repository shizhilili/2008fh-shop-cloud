package com.fh.shop.api.member.controller;


import com.alibaba.fastjson.JSON;
import com.fh.shop.api.BaseController;
import com.fh.shop.api.member.biz.IMemberService;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.common.SecretLogin;
import com.fh.shop.common.ServerResponse;
import com.fh.shop.common.SystemConstant;
import com.fh.shop.util.KeyUtil;
import com.fh.shop.util.RedisUtil;
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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(tags= "会员信息")
public class MemberController extends BaseController {


    @Resource(name = "memberService")
    private IMemberService service;

    @Autowired
    private HttpServletRequest request;


    @GetMapping("/member/getMemVo")
    @ApiOperation("查询用户信息")
    @ApiImplicitParam(name = "x-auth",value = "请求头",paramType = "header",required = true,dataType = "java.lang.String")
    public ServerResponse getMemVo()  {
        MemberVo memberVo = buildMemberVo(request);
        Long id = memberVo.getId();
        String count = RedisUtil.hget(KeyUtil.buildCartKey(id), SystemConstant.CART_COUNT_FIELD);
        Map map=  new HashMap();
        map.put("memberVo",memberVo);
        map.put("count",count);
        return ServerResponse.success(map);
    }

    @ApiOperation("会员登录")
    @PostMapping("/member/login")
    @ApiImplicitParams({
            @ApiImplicitParam(value ="会员名",name = "memberName",dataType = "java.lang.String",required = true),
            @ApiImplicitParam(value ="密码",name = "password",dataType = "java.lang.String",required = true)
    })
    public ServerResponse login(String memberName, String password){
        return service.login(memberName,password);
    }

    @ApiOperation("注销")
    @GetMapping("/member/loginOut")
    public ServerResponse loginOut() {
        MemberVo memberVo = buildMemberVo(request);
        RedisUtil.del(KeyUtil.getksy(memberVo.getId()));
        return ServerResponse.success();

    }




}
