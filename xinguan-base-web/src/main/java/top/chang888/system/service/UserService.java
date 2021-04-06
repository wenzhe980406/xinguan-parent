package top.chang888.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.chang888.common.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author changyw
 * @since 2021-03-25
 */
public interface UserService extends IService<User> {

    /**
     * 通过查询条件分页查询用户列表
     * @param page 当前页码
     * @param queryWrapper 增加查询条件
     * @return IPage 固定返回参数
     */
    IPage<User> findUserByCondition(Page<User> page, QueryWrapper<User> queryWrapper);

    /**
     * 添加用户
     * @param user 用户
     */
    void addUser(User user);

    /**
     * 编辑用户
     * @param user 用户
     */
    void editUser(User user);

    /**
     * 根据用户id删除用户
     * @param id 用户id
     */
    void deleteUser(Long id);

    /**
     * 修改用户状态
     * @param id 用户id
     * @param status 用户状态
     */
    void editUserStatus(Integer id, Integer status);
}
