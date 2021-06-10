package com.fh.shop.api.goods.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Sku implements Serializable {

  private Long id;

  private String skuName;

  private  Long spuId;

  private BigDecimal price;

  private  int stock;

  private  String specInfo;

  private String image;

  private  Long colorId;

  private String status;

  private String  newOld;

  private  String  hot;

}
