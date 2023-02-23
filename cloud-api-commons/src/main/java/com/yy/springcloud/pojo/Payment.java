package com.yy.springcloud.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "Payment")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Payment implements Serializable {
    @ApiModelProperty(value = "用户id", example = "0")
    private long userId;

    @ApiModelProperty(value = "价格", example = "0.0")
    private Double amount;

    @ApiModelProperty(value = "实际价格", example = "0")
    private Double staticAmount;

    @ApiModelProperty(value = "原价", example = "0.0")
    private Double originalPrice;

    @ApiModelProperty(value = "钻石数量", example = "0.0")
    private Double cowry;

    @ApiModelProperty(value = "状态 1成功  0待支付 2失败", example = "0")
    private int status;

    private Date addTime;

    private String orderId;

    private String channelOrderId;

    @ApiModelProperty(value = "渠道", example = "")
    private String channel;

    @ApiModelProperty(value = "渠道", example = "")
    private String channelReturn;

    @ApiModelProperty(value = "支付时间", example = "")
    private Date successTime;

    private Date updated_time;

    @ApiModelProperty(value = "商品id", example = "")
    private Integer productId;

    private Integer orderType; // 1普通订单 2开通VIP订单
}
