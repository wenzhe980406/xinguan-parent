import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import top.chang888.App;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author changyw
 * @date 2021/3/23
 */

@SpringBootTest(classes = App.class)
public class MyTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test1() {
        System.out.println(jdbcTemplate);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from tb_user");
        maps.forEach(System.out::println);
    }
}
