package top.chang888.common.response;

/**
 * rules:
 *  # 200 表示成功
 *  # 1001-1999 区间表示参数错误
 *  # 2001-2999 区间表示用户错误
 *  # 3001-3999 区间表示接口错误
 * @author changyw
 * @date 2021/3/24
 */
public enum ResultCode implements CustomizeResultCode {

    /* 200: 成功 */
    SUCCESS(200, "成功"),

    /* 默认失败 */
    COMMON_FAIL(999, "失败"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),
    USER_SESSION_INVALID(2010, "登录超时"),
    USER_TOKEN_NOT_FUND(2011, "用户token信息不存在"),
    USER_TOKEN_NOT_UNUSUAL(2012, "用户token信息异常"),

    /*部门错误*/
    DEPARTMENT_NOT_EXIST(3007, "部门不存在"),
    DEPARTMENT_ALREADY_EXIST(3008, "部门已存在"),

    /* 业务错误 */
    NO_PERMISSION(4001, "没有权限"),

    /*运行时异常*/
    ARITHMETIC_EXCEPTION(9001,"算数异常"),
    DESERIALIZE_EXCEPTION(9002, "数据序列化异常"),
    CONVERSION_EXCEPTION(9003, "数据转换异常"),
    DATABASE_USER_USERNAME_REPET(9004, "数据库用户表用户名重复异常")
    ;

    private Integer code;

    private String message;

    ResultCode() {

    }

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
