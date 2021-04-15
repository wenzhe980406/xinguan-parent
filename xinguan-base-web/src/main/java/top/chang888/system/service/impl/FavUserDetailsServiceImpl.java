package top.chang888.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.chang888.common.dto.UserLoginDTO;
import top.chang888.common.entity.Role;
import top.chang888.common.entity.User;
import top.chang888.system.service.RoleService;
import top.chang888.system.service.UserService;

import java.util.*;

/**
 * @author changyw
 * @date 2021/4/14
 */
@Service("userDetailsService")
public class FavUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername -> " + username);
        UserLoginDTO userDetails;

        User user = userService.findUserByUsername(username);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("未找到该用户 - " + username);
        }

        userDetails = new UserLoginDTO(user.getUsername(), user.getPassword(), user.getStatus() == 1,
                getAuthorities(user.getId()));

        return userDetails;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Long userId) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        List<Role> rolesByUserId = roleService.findRoleByUserId(userId);
        rolesByUserId.forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return authorities;
    }
}
