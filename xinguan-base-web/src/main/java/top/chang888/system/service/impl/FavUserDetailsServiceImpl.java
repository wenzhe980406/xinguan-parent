package top.chang888.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
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
@Service("userDetailsService")
public class FavUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername -> " + username);
        UserLoginDTO userDetails;

        User user = userService.findUserByUsername(username);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("未找到该用户 - " + username);
        }

        userDetails = new UserLoginDTO(user.getUsername(), user.getPassword(), user.getStatus() == 1);

        List<Role> roleList = roleService.findRoleByUserId(user.getId());
        List<Menu> menuList = menuService.findMenuByRoles(roleList);
        Set<String> urls = new HashSet<>();
        Set<String> perms = new HashSet<>();
        if (!CollectionUtil.isEmpty(menuList)){
            for (Menu menu : menuList) {
                String url = menu.getUrl();
                String per = menu.getPerms();
                // 如果是菜单类型 则加url 否则加权限
                if (menu.getType() == 0 && !StrUtil.isEmpty(url)) {
                    urls.add(url);
                }

                if (menu.getType() == 1 && !StrUtil.isEmpty(per)) {
                    urls.add(per);
                }
            }
        }

        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(user, userInfoVo);
        List<String> roles = new ArrayList<>();
        Set<GrantedAuthority> authorities = new HashSet<>();
        roleList.forEach(role -> {
            String roleName = role.getRoleName();
            roles.add(roleName);
            authorities.add(new SimpleGrantedAuthority(roleName));
        });
        userDetails.setAuthorities(authorities);

        userInfoVo.setUrl(urls);
        userInfoVo.setPerms(perms);
        userInfoVo.setRoles(roles);
        userInfoVo.setIsAdmin(user.getType() == 0);

        return userDetails;
    }

}
