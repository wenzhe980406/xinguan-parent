package top.chang888.system.auth;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.chang888.common.dto.UserLoginDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author changyw
 * @date 2021/4/15
 */
@Slf4j
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        /*if (!request.getContentType().startsWith(MediaType.APPLICATION_JSON_VALUE) &&
                !request.getContentType().startsWith(MediaType.MULTIPART_FORM_DATA_VALUE) &&
                    !request.getContentType().equals(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {
            throw new AuthenticationServiceException("请求头类型错误 " + request.getContentType());
        }*/
        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("请求方法错误 " + request.getMethod());
        }

        UserLoginDTO user;
        try(InputStream inputStream = request.getInputStream()) {
            user = objectMapper.readValue(inputStream, UserLoginDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("解析输入流失败.");
        }

        if (Objects.nonNull(user)) {
            String username = user.getUsername();
            username = StrUtil.isEmpty(username) ? "" : username;
            username = username.trim();

            String password = user.getPassword();
            password = StrUtil.isEmpty(password) ? "" : password;
            password = password.trim();

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request, authRequest);
            return super.getAuthenticationManager().authenticate(authRequest);
        }

        throw new AuthenticationServiceException("输入用户信息错误！");
    }

}
