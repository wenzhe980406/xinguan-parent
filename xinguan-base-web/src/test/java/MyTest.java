import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import top.chang888.App;
import top.chang888.common.utils.AliOssUtils;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author changyw
 * @date 2021/3/23
 */

@SpringBootTest(classes = App.class)
public class MyTest {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    /*@Test
    public void test1() {
        System.out.println(jdbcTemplate);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from tb_user");
        maps.forEach(System.out::println);
    }*/

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test1() {
        AliOssUtils.listFile().forEach(System.out::println);
    }

    @Test
    public void test2() {
        AliOssUtils.deleteFile("avatar/2021/03/27/d2ebd0a5bb7a4b999e5aa8b7c2ce054c.jpg");
    }

    @Test
    public void test3() {
        String filename = "mmm.jpg";
        String fileType = filename.substring(filename.lastIndexOf("."));
        System.out.println(fileType);
    }

    @Test
    public void test4() {
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }

    @Test
    public void test5() throws SQLException {
        System.out.println(dataSource);
        System.out.println(dataSource.getConnection());
    }
}
