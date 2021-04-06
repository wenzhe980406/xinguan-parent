package top.chang888.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域解决方案
 * @author changyw
 * @date 2021/3/25
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * springboot 2.4版本解决方案
     * @param registry registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // springboot 2.3版本解决方案 -> .allowedOrigins("*")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(3600)
                .allowCredentials(true)
                ;
    }
}
