package top.chang888.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.chang888.common.response.Result;
import top.chang888.common.response.ResultCode;

/**
 * 全局Controller异常处理器
 * @author changyw
 * @date 2021/3/24
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 算数异常捕捉
     * @param e 算数异常
     * @return Result
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e) {
        log.error(e.getMessage());
        return Result.error()
                .code(ResultCode.ARITHMETIC_EXCEPTION.getCode())
                .message(ResultCode.ARITHMETIC_EXCEPTION.getMessage());
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseBody
    public Result error(HttpMessageConversionException e) {
        log.error(e.getMessage() + " 字段转换异常");
        return Result.error()
                .code(ResultCode.CONVERSION_EXCEPTION.getCode())
                .message(ResultCode.CONVERSION_EXCEPTION.getMessage());
    }

    /**
     * 全局异常处理 没有指定类型
     * @param e 异常
     * @return Result
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        log.error(e.getMessage());
        return Result.error();
    }

    /**
     * 自定义异常处理
     * @param e 自定义异常
     * @return Result
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result error(BusinessException e) {
        log.error(e.getMessage());
        return Result.error()
                .code(e.getCode())
                .message(e.getErrMsg());
    }

}
