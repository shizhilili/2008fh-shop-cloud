package com.fh.shop.api.member.biz;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.member.mapper.IMemberMapper;
import com.fh.shop.api.member.po.Member;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.common.ResponseEnum;
import com.fh.shop.common.SecretLogin;
import com.fh.shop.common.ServerResponse;
import com.fh.shop.util.KeyUtil;
import com.fh.shop.util.Md5Util;
import com.fh.shop.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service("memberService")
public class IMemberServiceImpl implements IMemberService {
    @Autowired
    private IMemberMapper memberMapper;


    @Override
    public ServerResponse login(String memberName, String password) {
//        在登陆的时候首先要判断用户，密码不为空
            if(StringUtils.isEmpty(memberName) || StringUtils.isEmpty(password)){
                return ServerResponse.error(ResponseEnum.MEMBER_LOJIN_IS_NOLL);
            }

//         判断用户是否正确
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("memberName",memberName);
        Member member = memberMapper.selectOne(memberQueryWrapper);
        //判断账号是否激活

        if(member==null){
            return ServerResponse.error(ResponseEnum.MEMBER_LOJIN_IS_EX);
        }
//        判断密码是否正确
        if(!Md5Util.md5(password).equals(member.getPassword())){
            return ServerResponse.error(ResponseEnum.MEMBER_LOJIN_PASSWORD_ERROR);
        }
        //判断用户状态是否进行激活
        int status = member.getStatus();
        if(status==0){
            String email = member.getEmail();
            Long id = member.getId();
            Map map=new HashMap();
            map.put("email",email);
            map.put("id",id);
            return ServerResponse.error(ResponseEnum.MEMBER_LOJIN_STATUS_ERROR,map);
        }

        // ----------------------------------------获取签名
        //  签名=MD5(用户信息+secret密钥）
        MemberVo memberVo = new MemberVo();
        memberVo.setId(member.getId());
        memberVo.setMemberName(member.getMemberName());
        memberVo.setNickName(member.getNickName());
        String memberVoJsonStr = JSON.toJSONString(memberVo);
        String secre = SecretLogin.SECRE;
        String sign = Md5Util.md5(memberVoJsonStr + secre);
        // ----------------------------------------获取签名

        // 返回  用户信息+签名通过basc64(encode是加 decode是解)
        String memberInfo = Base64.getEncoder().encodeToString(memberVoJsonStr.getBytes());
        String signBase64 = Base64.getEncoder().encodeToString(sign.getBytes());

        RedisUtil.setEx(KeyUtil.getksy(memberVo.getId()),"",SecretLogin.SECODE);

        return ServerResponse.success(memberInfo+"."+signBase64);
    }




}
