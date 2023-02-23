package com.yy.springcloud.model;


import com.yy.springcloud.exception.CustomException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * LumiApiResponse
 *
 * @author tianyi.wei
 */
@Data
@ApiModel(value = "LumiApiResponse")
public class LumiApiResponse<T> implements Serializable {

    private static final long serialVersionUID = -3284622969525206252L;

    @ApiModelProperty(value = " 业务返回码(随每个接口给出)")
    int ret;

    @ApiModelProperty(value = " 业务返回消息(随每个接口给出)")
    String msg;

    @ApiModelProperty(value = " 业务返回值")
    T result;

    @ApiModelProperty(value = "服务器响应时间")
    String serverTime = String.valueOf(System.currentTimeMillis());

    public LumiApiResponse() {
       this.ret=LumiApiResEnum.SUCCESS.getCode();
        this.msg = LumiApiResEnum.SUCCESS.getErrMessage();
    }

    public LumiApiResponse(LumiApiResEnum ret) {
        this.ret = ret.getCode();
        this.msg = ret.getErrMessage();
    }

    public LumiApiResponse(LumiApiResEnum ret, T result) {
        this.ret = ret.getCode();
        this.msg = ret.getErrMessage();
        this.result = result;
    }

    private LumiApiResponse(Integer errCode, String errDesc) {
        this.ret = errCode;
        this.msg = errDesc;
    }

    public LumiApiResponse(LumiApiResEnum ret, String customMessage, T result) {
        this.ret = ret.getCode();
        this.msg = customMessage;
        this.result = result;
    }

    public static LumiApiResponse error(String errorMessage, LumiApiResEnum lumiApiResEnum) {
        return new LumiApiResponse(lumiApiResEnum.getCode(), errorMessage);
    }

    public static LumiApiResponse error(LumiApiResEnum lumiApiResEnum) {
        return new LumiApiResponse(lumiApiResEnum);
    }


    public T getResult() {
        return result;
    }


    public LumiApiResponse(T result) {
        this.ret = LumiApiResEnum.SUCCESS.getCode();
        this.result = result;
    }

    public static LumiApiResponse<String> error(CustomException customException) {
        return new LumiApiResponse<>(customException.getCode(), customException.getMsgDes(), null);
    }
}
