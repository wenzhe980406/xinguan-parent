package top.chang888.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import top.chang888.common.converter.MenuConverter;
import top.chang888.common.entity.Menu;
import top.chang888.common.utils.MenuTreeUtils;
import top.chang888.common.vo.system.MenuNodeVo;
import top.chang888.common.vo.system.MenuVo;
import top.chang888.system.mapper.MenuMapper;
import top.chang888.system.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author changyw
 * @since 2021-04-06
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    /**
     * 获取菜单树
     * @return list
     */
    @Override
    public List<MenuNodeVo> get() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        List<Menu> menuList = this.baseMapper.selectList(queryWrapper);
        List<MenuNodeVo> menuNodeVos = MenuConverter.converterMenu2MenuNodeVo(menuList);
        return MenuTreeUtils.build(menuNodeVos);
    }

    /**
     * 添加菜单节点
     *
     * @param menuVo 菜单节点
     */
    @Override
    public void add(MenuVo menuVo) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVo, menu);
        menu.setAvailable(menuVo.getDisabled() ? 0 : 1);
        this.baseMapper.insert(menu);
    }

    /**
     * 通过菜单节点id 删除菜单节点
     *
     * @param id 菜单节点id
     */
    @Override
    public void delete(Integer id) {
        this.baseMapper.deleteById(id);
    }

    /**
     * 通过菜单节点id 编辑菜单节点
     *
     * @param menuVo 菜单节点id
     */
    @Override
    public void edit(MenuVo menuVo) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVo, menu);
        this.baseMapper.updateById(menu);
    }

    /**
     * 所有展开菜单的ID
     * @return list
     */
    @Override
    public List<Long> findOpenIds() {
        /*QueryWrapper<Long> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open", 1).select("id");
        return this.baseMapper.selectList(queryWrapper);*/

        return this.baseMapper.findAllByOpen();
    }
}
