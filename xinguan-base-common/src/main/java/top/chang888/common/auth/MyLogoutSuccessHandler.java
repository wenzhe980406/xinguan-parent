package top.chang888.common.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import top.chang888.common.response.JsonAuthentication;
import top.chang888.common.response.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author changyw
 * @date 2021/4/13
 */
@Component("logoutSuccessHandler")
public class MyLogoutSuccessHandler extends JsonAuthentication implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // 清除token
        Result result = Result.ok().message("注销成功");
        this.write(request, response, result);

    }
}
