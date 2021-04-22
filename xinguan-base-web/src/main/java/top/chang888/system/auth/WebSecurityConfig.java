package top.chang888.system.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.chang888.system.auth.handler.*;

import javax.annotation.Resource;

/**
 * web权限控制配置类
 * @author changyw
 * @date 2021/3/27
 */
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

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    @Bean
    public MyBasicAuthenticationFilter basicAuthenticationFilter() throws Exception {
        return new MyBasicAuthenticationFilter(authenticationManager());
    }

//    @Bean
    /*public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }*/

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

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(authenticationProvider())
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/login", "/logout")
                    .permitAll()
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
//                        .usernameParameter("username")
//                        .passwordParameter("password")
                        .successHandler(successHandler)
                        .failureHandler(failureHandler)
                .and()
                    .exceptionHandling()
                    // 未登录访问资源的时候提示
                    .authenticationEntryPoint(entryPoint)
                    .accessDeniedHandler(deniedHandler)
                .and()
                    .sessionManagement()
                        // IF_REQUIRED 只在需要的时候创建session
                        // STATELESS  从不创建或使用任何session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 关闭csrf跨域
                    .csrf()
                    .disable();

        http.addFilterAt(usernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilter(basicAuthenticationFilter())
        ;

//        http.csrf().disable();
//        http.cors().disable();  // 不可以加这个！不然会报错
    }
}
