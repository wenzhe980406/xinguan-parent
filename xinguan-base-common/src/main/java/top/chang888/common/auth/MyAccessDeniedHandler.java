package top.chang888.common.auth;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import top.chang888.common.response.JsonAuthentication;
import top.chang888.common.response.Result;
import top.chang888.common.response.ResultCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author changyw
 * @date 2021/4/13
 */

@Component("deniedHandler")
public class MyAccessDeniedHandler extends JsonAuthentication implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result result = Result.error(ResultCode.NO_PERMISSION);
        this.write(request, response, result);
    }
}
