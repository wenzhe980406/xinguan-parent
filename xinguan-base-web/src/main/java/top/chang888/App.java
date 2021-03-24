package top.chang888;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author changyw
 * @date 2021/3/23
 */
@SpringBootApplication
@MapperScan("top.chang888.system.mapper")
@EnableSwagger2
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
