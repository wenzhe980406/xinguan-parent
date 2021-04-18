package top.chang888.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.chang888.App;
import top.chang888.common.entity.Menu;
import top.chang888.common.entity.Role;
import top.chang888.system.service.MenuService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author changyw
 * @date 2021/4/17
 */
@SpringBootTest(classes = App.class)

public class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void test() {

        List<Role> roleList = new ArrayList<>();
        Role role1 = new Role();
        Role role2 = new Role();
        role1.setId(125L);
        role2.setId(126L);
        roleList.add(role1);
        roleList.add(role2);
        List<Menu> menuList = menuService.findMenuByRoles(roleList);

        System.out.println(menuList);
        System.out.println(menuList.size());

    }
}
