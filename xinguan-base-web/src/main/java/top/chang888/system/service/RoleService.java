package top.chang888.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.chang888.common.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author changyw
 * @since 2021-04-06
 */
public interface RoleService extends IService<Role> {

    /**
     * 通过分页查找role表
     * @param page 页码信息
     * @param wrapper 是否需要过滤
     * @return 分页器
     */
    IPage<Role> findRoleByCondition(Page<Role> page, QueryWrapper<Role> wrapper);
}
