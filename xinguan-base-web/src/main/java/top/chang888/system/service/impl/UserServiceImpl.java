package top.chang888.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.chang888.enums.UserStatusEnum;
import top.chang888.enums.UserTypeEnum;
import top.chang888.handler.BusinessException;
import top.chang888.response.ResultCode;
import top.chang888.system.entity.Department;
import top.chang888.system.entity.User;
import top.chang888.system.mapper.DepartmentMapper;
import top.chang888.system.mapper.UserMapper;
import top.chang888.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author changyw
 * @since 2021-03-25
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.NESTED, isolation= Isolation.DEFAULT, rollbackFor=Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public IPage<User> findUserByCondition(Page<User> page, QueryWrapper<User> queryWrapper) {
        return this.baseMapper.findUserByCondition(page, queryWrapper);
    }

    @Override
    public void addUser(User user) {
        findUserExist(user.getUsername(), ResultCode.USER_ACCOUNT_ALREADY_EXIST);

        // 判断所添加的部门是否为空
        Department department = departmentMapper.selectById(user.getDepartmentId());
        if (Objects.isNull(department)) {
            throw new BusinessException(ResultCode.DEPARTMENT_NOT_EXIST.getCode(),
                    ResultCode.DEPARTMENT_NOT_EXIST.getMessage());
        }

        user.setSalt(UUID.randomUUID().toString().substring(0, 32));

        // 自动设置时间 取消手动设置

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setType(UserTypeEnum.SYSTEM_USER.getTypeCode());
        user.setStatus(UserStatusEnum.DISABLE.getTypeCode());

        user.setDeleted(false);
        this.baseMapper.insert(user);
    }

    /**
     * 编辑用户，需要在url中传递用户ID
     * @param user 用户
     */
    @Override
    public void editUser(User user) {
        findUserExist(user.getUsername(), ResultCode.USER_ACCOUNT_ALREADY_EXIST);
        this.baseMapper.updateById(user);
    }

    /**
     * 根据用户id删除用户
     *
     * @param id 用户id
     */
    @Override
    public void deleteUser(Long id) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("deleted", false);
        this.baseMapper.deleteById(id);
    }

    /**
     * 编辑用户状态
     * @param id 用户id
     * @param status 用户状态
     */
    @Override
    public void editUserStatus(Integer id, Integer status) {
        this.baseMapper.updateStatusById(id, status);
    }

    public void findUserExist(String qw, ResultCode code) {
        // 判断是否添加了同一个用户 (username 相同)
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", qw);
        Integer count = this.baseMapper.selectCount(wrapper);
        if (count != 0) {
            throw new BusinessException(code.getCode(), code.getMessage());
        }
    }
}
