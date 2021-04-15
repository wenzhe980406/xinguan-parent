package top.chang888.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.chang888.App;
import top.chang888.common.utils.JwtUtils;

/**
 * @author changyw
 * @date 2021/4/15
 */
@SpringBootTest(classes = App.class)
public class JwtTest {

    @Test
    public void test1() {
        String token = JwtUtils.sign("changyw");
        System.out.println(token);

        System.out.println(JwtUtils.getUsername(token));
    }
}
