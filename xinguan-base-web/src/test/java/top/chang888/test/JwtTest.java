package top.chang888.test;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.chang888.App;
import top.chang888.common.utils.JwtUtils;
import top.chang888.common.utils.JwtsUtils;

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

    @Test
    public void test2() throws JSONException {
        String token = JwtsUtils.sign("changyw", "123456");
        System.out.println(token);

//        System.out.println(JwtsUtils.getUsername(token));
//        System.out.println(JwtsUtils.parseToken(token));
    }

    @Test
    public void test3() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJjaGFuZ3l3IiwiaWF0IjoxNjE4NTU1ODc4LCJleHAiOjE2MTg1NTYxNzh9.h9Q2qdeikIrCowoyMY6RsjspjDfPz5i8qEFRYGJ2F2Y";

        System.out.println(JwtsUtils.parseToken(token, "123456"));
    }

    @Test
    public void test5() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJjaGFuZ3l3IiwiaWF0IjoxNjE4NTU1ODc4LCJleHAiOjE2MTg1NTYxNzh9.h9Q2qdeikIrCowoyMY6RsjspjDfPz5i8qEFRYGJ2F2Y";

        System.out.println(JwtsUtils.surExpires(token, "123456"));
    }

    @Test
    public void test6() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhbmdlIiwiaWF0IjoxNjE4ODg0NDgzLCJleHAiOjE2MTg4ODQ3ODN9.azV03QGBl4JoLyJi97VAyxeO5nP1WRoy7UqxaCrlB0s";

        System.out.println(JwtsUtils.verifyToken(token, "$2a$10$X7RC7S75aI6/BAT/BoJjy.Fvtv.KOXxH5bw/ElR/8s564qrsl9fnS"));
    }
}
