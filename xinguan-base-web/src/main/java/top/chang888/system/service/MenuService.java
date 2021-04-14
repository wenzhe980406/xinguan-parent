package top.chang888.system.service;

import top.chang888.common.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import top.chang888.common.vo.system.MenuNodeVo;
import top.chang888.common.vo.system.MenuVo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author changyw
 * @since 2021-04-06
 */
public interface MenuService extends IService<Menu> {

    /**
     * 获取菜单树
     * @return list
     */
    List<MenuNodeVo> get();

    /**
     * 添加菜单节点
     * @param menu 菜单节点
     */
    void add(MenuVo menu);

    /**
     * 通过菜单节点id 删除菜单节点
     * @param id 菜单节点id
     */
    void delete(Integer id);

    /**
     * 通过菜单节点id 编辑菜单节点
     * @param menu 菜单节点id
     */
    void edit(MenuVo menu);

    /**
     * 所有展开菜单的ID
     * @return list
     */
    List<Long> findOpenIds();

    /**
     * 获取所有菜单的权限
     * @param id 角色id
     * @return list
     */
    List<Menu> findMenuPermsByRoleId(Long id);
}
