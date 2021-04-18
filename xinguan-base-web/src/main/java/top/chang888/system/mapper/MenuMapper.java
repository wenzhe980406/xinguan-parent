package top.chang888.system.mapper;

import org.apache.ibatis.annotations.Select;
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
    @Select("select id from tb_menu where open = 1")
    List<Long> findAllByOpen();

    /**
     * 通过角色id获取该角色拥有的所有菜单权限
     * @param id 角色id
     * @return list
     */
    @Select("SELECT m.* from tb_menu m, tb_role r, tb_role_menu rm \n" +
            "where m.id = rm.menu_id and rm.role_id = r.id and r.id = #{id};")
    List<Menu> findMenuPermsByRoleId(Long id);
}
