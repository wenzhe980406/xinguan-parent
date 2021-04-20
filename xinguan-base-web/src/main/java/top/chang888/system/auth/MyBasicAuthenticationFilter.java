package top.chang888.system.auth;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import top.chang888.common.dto.UserLoginDTO;
import top.chang888.common.entity.Menu;
import top.chang888.common.entity.Role;
import top.chang888.common.entity.User;
import top.chang888.common.utils.JwtsUtils;
import top.chang888.common.vo.system.UserInfoVo;
import top.chang888.system.service.MenuService;
import top.chang888.system.service.RoleService;
import top.chang888.system.service.UserService;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author changyw
 * @date 2021/4/19
 */
@Slf4j
public class MyBasicAuthenticationFilter extends BasicAuthenticationFilter {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private MenuService menuService;

    public MyBasicAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(JwtsUtils.TOKEN_HEADER);
        log.info("已进入MyBasicAuthenticationFilter - 获取token - {}", token);
        if (!JwtsUtils.verifyTokenFormat(token) || !token.startsWith(JwtsUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            log.warn("用户未登录或者token错误, 认证失败!");
            return;
        }

        token = token.replace(JwtsUtils.TOKEN_PREFIX, "");

        try {
            String username = JwtsUtils.getUsername(token);

            User user = userService.findUserByUsername(username);

            if (Objects.isNull(user)) {
                chain.doFilter(request, response);
                log.warn("查询用户失败, 请重新登录!");
                return;
            }

            if (!JwtsUtils.verifyToken(token, user.getPassword())) {
                chain.doFilter(request, response);
                log.warn("证书过期, 请重新登录!");
                return;
            }

            Authentication authentication = StrUtil.isEmpty(username) ? new UsernamePasswordAuthenticationToken(username,
            null, new ArrayList<>()) : getAuthentication(user);

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JSONException e) {
            chain.doFilter(request, response);
            log.warn("证书异常, 认证失败!");
            return;
        }

        super.doFilterInternal(request, response, chain);
    }

    private Authentication getAuthentication(User user) {
        UserLoginDTO userDetails = new UserLoginDTO();

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
                    perms.add(per);
                }
            }
        }

        if (user.getType() == 0) {
            perms.clear();
            perms.add("*:*");
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
        userInfoVo.setDepartment(user.getName());
        userInfoVo.setIsAdmin(user.getType() == 0);
        userDetails.setUserInfoVo(userInfoVo);
        log.info("MyBasicAuthenticationFilter - [getAuthentication] userDetails: {} ", userDetails.toString());
        return new PreAuthenticatedAuthenticationToken(userDetails, user.getPassword(), authorities);
    }

}
