package top.chang888.common.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import top.chang888.common.dto.UserLoginDTO;
import top.chang888.common.response.JsonAuthentication;
import top.chang888.common.response.Result;
import top.chang888.common.utils.JwtsUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功 操作
 * @author changyw
 * @date 2021/4/13
 */
@Component("successHandler")
public class MyAuthenticationSuccessHandler extends JsonAuthentication implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        UserLoginDTO userLoginDTO = (UserLoginDTO) authentication.getPrincipal();
        String token = JwtsUtils.sign(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        // 返回json
        Result result = Result.ok().message("登录成功").data("token", token);

        this.write(request, response, result);
    }
}
