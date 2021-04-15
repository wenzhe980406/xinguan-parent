package top.chang888.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.chang888.common.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author changyw
 * @since 2021-04-06
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 分页查询Role表
     * @param page 分页查询配置表
     * @param wrapper 查询条件
     * @return IPage信息表
     */
    IPage<Role> findRoleByCondition(Page<Role> page, @Param(Constants.WRAPPER) QueryWrapper<Role> wrapper);

    /**
     * 通过用户id获取用户所拥有的角色
     * @param id 用户id
     * @return role
     */
    List<Role> findRoleByUserId(Long id);
}
