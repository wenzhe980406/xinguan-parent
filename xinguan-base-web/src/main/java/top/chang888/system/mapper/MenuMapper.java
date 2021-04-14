package top.chang888.system.mapper;

import top.chang888.common.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author changyw
 * @since 2021-04-06
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 获取所有open为1的id列表
     * @return list
     */
    List<Long> findAllByOpen();

    /**
     * 通过角色id获取该角色拥有的所有菜单权限
     * @param id 角色id
     * @return list
     */
    List<Menu> findMenuPermsByRoleId(Long id);
}
