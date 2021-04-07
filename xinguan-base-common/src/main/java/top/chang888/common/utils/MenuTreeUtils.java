package top.chang888.common.utils;

import top.chang888.common.vo.system.MenuNodeVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author changyw
 * @date 2021/4/7
 */
public class MenuTreeUtils {

    public static List<MenuNodeVo> build(List<MenuNodeVo> nodes) {
        // 根节点
        List<MenuNodeVo> root = new ArrayList<>();

        nodes.forEach(node -> {
            if (node.getParentId() == 0) {
                root.add(node);
            }
        });

        root.sort(MenuNodeVo.comparator());

        root.forEach(node -> node.setChildren(getChild(node.getId(), nodes)));

        return root;
    }

    public static List<MenuNodeVo> getChild(Long id, List<MenuNodeVo> nodes) {
        List<MenuNodeVo> children = new ArrayList<>();

        nodes.forEach(node -> {
            if (id.equals(node.getParentId())) {
                children.add(node);
            }
        });
        children.sort(MenuNodeVo.comparator());

        // 递归设置菜单
        children.forEach(child -> child.setChildren(getChild(child.getId(), children)));
        // 如果子节点列表为空，代表没有再向下的节点了 截止递归
        if (children.size() == 0) {
            return new ArrayList<>();
        }
        return children;
    }

}
