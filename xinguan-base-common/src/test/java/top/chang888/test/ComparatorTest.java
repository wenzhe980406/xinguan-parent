package top.chang888.test;

import top.chang888.common.vo.system.MenuNodeVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author changyw
 * @date 2021/4/7
 */

public class ComparatorTest {
    public static void main(String[] args) {

        MenuNodeVo menuNodeVo = new MenuNodeVo(1L, "系统管理", 1L, Boolean.TRUE, 0);
        MenuNodeVo menuNodeVo1 = new MenuNodeVo(1L, "系统管理", 2L, Boolean.TRUE, 0);
        MenuNodeVo menuNodeVo2 = new MenuNodeVo(1L, "系统管理", 3L, Boolean.TRUE, 0);
        MenuNodeVo menuNodeVo3 = new MenuNodeVo(1L, "系统管理", 4L, Boolean.TRUE, 0);
        MenuNodeVo menuNodeVo4 = new MenuNodeVo(1L, "系统管理", 5L, Boolean.TRUE, 0);
        MenuNodeVo menuNodeVo5 = new MenuNodeVo(1L, "系统管理", 6L, Boolean.TRUE, 0);
        MenuNodeVo menuNodeVo6 = new MenuNodeVo(1L, "系统管理", 7L, Boolean.TRUE, 0);
        MenuNodeVo menuNodeVo7 = new MenuNodeVo(1L, "系统管理", 8L, Boolean.TRUE, 0);
        MenuNodeVo menuNodeVo8 = new MenuNodeVo(1L, "系统管理", 9L, Boolean.TRUE, 0);
        MenuNodeVo menuNodeVo9 = new MenuNodeVo(1L, "系统管理", 10L, Boolean.TRUE, 0);


        List<MenuNodeVo> list = new ArrayList<>();
        list.add(menuNodeVo);
        list.add(menuNodeVo9);
        list.add(menuNodeVo4);
        list.add(menuNodeVo3);
        list.add(menuNodeVo6);
        list.add(menuNodeVo8);
        list.add(menuNodeVo1);
        list.add(menuNodeVo2);
        list.add(menuNodeVo5);
        list.add(menuNodeVo7);
        list.add(menuNodeVo2);
        list.stream().sorted(MenuNodeVo.comparator()).forEach(System.out::println);

    }
}
