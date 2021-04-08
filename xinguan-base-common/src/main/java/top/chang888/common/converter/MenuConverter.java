package top.chang888.common.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import top.chang888.common.entity.Menu;
import top.chang888.common.vo.system.MenuNodeVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author changyw
 * @date 2021/4/7
 */
public class MenuConverter {

    public static List<MenuNodeVo> converterMenu2MenuNodeVo(List<Menu> menuList) {
        List<MenuNodeVo> menuNodeVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(menuList)) {
            for (Menu menu : menuList) {
                MenuNodeVo menuNodeVo = new MenuNodeVo();
                BeanUtils.copyProperties(menu, menuNodeVo);
                menuNodeVo.setDisabled(menu.getAvailable() == 0);
                menuNodeVos.add(menuNodeVo);
            }
        }
        return menuNodeVos;
    }

}
