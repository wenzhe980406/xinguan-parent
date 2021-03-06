package top.chang888.common.handler;

import io.swagger.annotations.ApiModelProperty;
import top.chang888.common.response.ResultCode;

/**
 * 自定义异常类
 * @author changyw
 * @date 2021/3/24
 */
public class BusinessException extends RuntimeException {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "错误信息")
    private String errMsg;

    public BusinessException() {

    }

    public BusinessException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.errMsg = resultCode.getMessage();
    }

    public BusinessException(Integer code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
