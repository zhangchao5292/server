package com.yy.springcloud.exception;

import com.yy.springcloud.model.LumiApiResEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 *
 * @author tianyi.wei
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 5811443430547292818L;
    /**
     * 异常对应的返回码
     */
    public LumiApiResEnum code;

    /**
     * 异常对应的描述信息
     */
    public String msgDes;

    public CustomException() {
        super();
    }

    public CustomException(LumiApiResEnum code, String msgDes) {
        super(msgDes);
        this.code = code;
        this.msgDes = String.format(code.getErrMessage(), msgDes);
    }

    public CustomException(LumiApiResEnum code) {
        super(code.getErrMessage());
        this.code = code;
        this.msgDes = code.getErrMessage();
    }

}
