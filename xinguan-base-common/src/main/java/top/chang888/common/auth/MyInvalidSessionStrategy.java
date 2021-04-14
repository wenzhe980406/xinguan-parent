package top.chang888.common.auth;

import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;
import top.chang888.common.response.JsonAuthentication;
import top.chang888.common.response.Result;
import top.chang888.common.response.ResultCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 账户超时处理器
 * @author changyw
 * @date 2021/4/14
 */
@Component("myInvalidSessionStrategy")
public class MyInvalidSessionStrategy extends JsonAuthentication implements InvalidSessionStrategy {

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Result result = Result.error(ResultCode.USER_SESSION_INVALID);
        this.write(request, response, result);
    }
}



