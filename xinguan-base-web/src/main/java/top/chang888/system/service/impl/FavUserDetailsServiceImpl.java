package top.chang888.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.chang888.common.entity.Role;
import top.chang888.common.entity.User;
import top.chang888.system.service.RoleService;
import top.chang888.system.service.UserService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author changyw
 * @date 2021/4/14
 */
@Service("userDetailService")
public class FavUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername -> " + username);
        UserDetails userDetails = null;
        try {
            User user = userService.findUserByUsername(username);

//            if (Objects.isNull(user)) {
//                throw new UsernameNotFoundException("用户未找到");
//            }

            userDetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(),
                    getAuthorities(user.getId()));

            return userDetails;

        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("用户未找到");
        }


    }

    private Collection<? extends GrantedAuthority> getAuthorities(Long userId) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        Role roleByUserId = roleService.findRoleByUserId(userId);
        authorities.add(new SimpleGrantedAuthority(roleByUserId.getRoleName()));
        return authorities;
    }
}
