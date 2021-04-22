package top.chang888.system.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.chang888.common.dto.UserLoginDTO;
import top.chang888.common.entity.User;
import top.chang888.common.handler.BusinessException;
import top.chang888.system.service.UserService;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author changyw
 * @date 2021/4/14
 */
@Slf4j
@Service("userDetailsService")
public class FavUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername -> {}", username);
        UserLoginDTO userDetails;

        try {
            User user = userService.findUserByUsername(username);
            userDetails = new UserLoginDTO(user.getUsername(), user.getPassword(), user.getStatus() == 1);
        } catch (BusinessException e){
            throw new InternalAuthenticationServiceException("未找到该用户 - " + username);
        }

//        userDetails = Objects.isNull(user) ? new UserLoginDTO(username, "", false) :
//                new UserLoginDTO(user.getUsername(), user.getPassword(), user.getStatus() == 1);

        return userDetails;
    }

}
