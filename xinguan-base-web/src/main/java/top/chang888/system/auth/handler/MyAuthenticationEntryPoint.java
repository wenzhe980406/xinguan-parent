package top.chang888.system.auth.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.chang888.common.response.JsonAuthentication;
import top.chang888.common.response.Result;
import top.chang888.common.response.ResultCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败 异常操作
 * @author changyw
 * @date 2021/4/13
 */
@Component("entryPoint")
public class MyAuthenticationEntryPoint extends JsonAuthentication implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result result = Result.error(ResultCode.USER_NOT_LOGIN);
        this.write(request, response, result);
    }
}
