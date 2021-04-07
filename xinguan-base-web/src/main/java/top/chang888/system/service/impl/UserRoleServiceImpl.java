package top.chang888.system.service.impl;

import top.chang888.common.entity.UserRole;
import top.chang888.system.mapper.UserRoleMapper;
import top.chang888.system.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author changyw
 * @since 2021-04-06
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
