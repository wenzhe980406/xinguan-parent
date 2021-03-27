import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.chang888.App;
import top.chang888.utils.AliOssUtils;

/**
 * @author changyw
 * @date 2021/3/23
 */

@SpringBootTest(classes = App.class)
public class MyTest {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    /*@Test
    public void test1() {
        System.out.println(jdbcTemplate);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from tb_user");
        maps.forEach(System.out::println);
    }*/

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
}
