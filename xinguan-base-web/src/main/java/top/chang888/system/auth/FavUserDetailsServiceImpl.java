package top.chang888.system.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.chang888.common.dto.UserLoginDTO;
import top.chang888.common.entity.User;
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

        User user = userService.findUserByUsername(username);

//        if (Objects.isNull(user)) {
//            throw new UsernameNotFoundException("未找到该用户 - " + username);
//        }
//
//        userDetails = new UserLoginDTO(user.getUsername(), user.getPassword(), user.getStatus() == 1);
        userDetails = Objects.isNull(user) ? new UserLoginDTO(username, null, false) :
                new UserLoginDTO(user.getUsername(), user.getPassword(), user.getStatus() == 1);

        return userDetails;
    }

}
