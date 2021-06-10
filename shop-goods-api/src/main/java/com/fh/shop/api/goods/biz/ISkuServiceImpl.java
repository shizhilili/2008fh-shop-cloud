package com.fh.shop.api.goods.biz;

import com.alibaba.fastjson.JSON;
import com.fh.shop.api.goods.mapper.ISkuMapper;
import com.fh.shop.api.goods.po.Sku;
import com.fh.shop.api.goods.vo.SkuVo;
import com.fh.shop.common.ServerResponse;
import com.fh.shop.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service("skuService")
public class ISkuServiceImpl implements ISkuService {

    @Autowired
    private ISkuMapper skuMapper;

   public ServerResponse findStatusList(){
       //先看redis缓存有没有数据
       String skuVoLis = RedisUtil.get("skuList");
       if(StringUtils.isNotEmpty(skuVoLis)){
           List<SkuVo> skuList = JSON.parseArray(skuVoLis, SkuVo.class);
           return ServerResponse.success(skuList);
       }
        //redis没有值
       List<Sku> statusList = skuMapper.findStatusList();
       List<SkuVo> skuVoList = statusList.stream().map(a -> {
           SkuVo skuVo = new SkuVo();
           skuVo.setId(a.getId());
           skuVo.setImage(a.getImage());
           skuVo.setPrice(a.getPrice());
           skuVo.setSkuName(a.getSkuName());
           return skuVo;
       }).collect(Collectors.toList());
       String skuList = JSON.toJSONString(skuVoList);
       RedisUtil.setEx("skuList",skuList,30);
       return ServerResponse.success(skuVoList);
   }

    @Override
    public ServerResponse findSku(Long id) {
        Sku sku = skuMapper.selectById(id);
        return ServerResponse.success(sku);
    }
}
