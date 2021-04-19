package top.chang888.system.auth;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.chang888.common.dto.UserLoginDTO;
import top.chang888.common.entity.Menu;
import top.chang888.common.entity.Role;
import top.chang888.common.entity.User;
import top.chang888.common.vo.system.UserInfoVo;
import top.chang888.system.service.MenuService;
import top.chang888.system.service.RoleService;
import top.chang888.system.service.UserService;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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
        UserLoginDTO userDetails;

        User user = userService.findUserByUsername(username);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("未找到该用户 - " + username);
        }

        userDetails = new UserLoginDTO(user.getUsername(), user.getPassword(), user.getStatus() == 1);

        return userDetails;
    }

}
