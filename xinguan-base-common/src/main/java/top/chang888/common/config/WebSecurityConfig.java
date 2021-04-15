package top.chang888.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import top.chang888.common.auth.*;

import javax.annotation.Resource;

/**
 * web权限控制配置类
 * @author changyw
 * @date 2021/3/27
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MyUsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
        MyUsernamePasswordAuthenticationFilter filter = new MyUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setAuthenticationFailureHandler(failureHandler);
        return filter;
    }

//    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    public MyAuthenticationSuccessHandler successHandler;

    @Resource
    public MyAuthenticationFailureHandler failureHandler;

    @Resource
    public MyAuthenticationEntryPoint entryPoint;

    @Resource
    public MyAccessDeniedHandler deniedHandler;

    @Resource
    public MyLogoutHandler logoutHandler;

    @Resource
    public MyLogoutSuccessHandler logoutSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                // 允许注销操作
                .logout().permitAll()
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(logoutSuccessHandler)
                // 删除cookie
                .deleteCookies("JESSIONID")
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and()
                .exceptionHandling()
                // 未登录访问资源的时候提示
                .authenticationEntryPoint(entryPoint)
                .accessDeniedHandler(deniedHandler);

        http.addFilterAt(usernamePasswordAuthenticationFilter(), MyUsernamePasswordAuthenticationFilter.class);

        http.sessionManagement()
                // STATELESS  从不创建或使用任何session
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // IF_REQUIRED 只在需要的时候创建session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        // 关闭csrf跨域
        http.csrf().disable();
//        http.cors().disable();  // 不可以加这个！不然会报错
    }
}
