import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import top.chang888.App;
import top.chang888.system.entity.OssEntity;

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
    private OssEntity ossEntity;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test1() {
        System.out.println(jdbcTemplate);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from tb_user");
        maps.forEach(System.out::println);
    }

    @Test
    public void test2() {
        System.out.println(ossEntity.toString());
    }
}
