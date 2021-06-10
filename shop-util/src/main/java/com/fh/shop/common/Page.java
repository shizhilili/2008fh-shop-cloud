package com.fh.shop.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class Page implements Serializable {

    @ApiModelProperty(value = "请求参数",example = "1",required = true)
    private Long draw;

    @ApiModelProperty(value = "开始位置",example = "1",required = true)
    private Long start;

    @ApiModelProperty(value = "数据长度",example = "10",required = true)
    private Long length;

    public Long getDraw() {
        return draw;
    }

    public void setDraw(Long draw) {
        this.draw = draw;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }
}
