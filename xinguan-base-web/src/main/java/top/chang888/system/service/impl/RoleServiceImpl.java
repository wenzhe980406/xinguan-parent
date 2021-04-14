package top.chang888.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.chang888.common.entity.Role;
import top.chang888.system.mapper.RoleMapper;
import top.chang888.system.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author changyw
 * @since 2021-04-06
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    /**
     * 通过分页查找role表
     * @param page 页码信息
     * @param wrapper 是否需要过滤
     * @return 分页器
     */
    @Override
    public IPage<Role> findRoleByCondition(Page<Role> page, QueryWrapper<Role> wrapper) {
        return this.baseMapper.findRoleByCondition(page, wrapper);
    }

    @Override
    public Role findRoleByUserId(Long id) {
        return this.baseMapper.findRoleByUserId(id);
    }
}
