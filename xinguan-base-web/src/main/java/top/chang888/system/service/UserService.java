package top.chang888.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.chang888.common.entity.User;
import top.chang888.common.vo.system.MenuNodeVo;
import top.chang888.common.vo.system.UserInfoVo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
     * 获取spring security user认真用户
     * @param username 用户名
     * @return <code>org.springframework.security.core.userdetails.User</code>
     */
    User findUserByUsername(String username);

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

    /**
     * 导出Excel 表格
     * @param response res 相应
     * @param wrapper 请求参数
     * @throws IOException io流异常
     */
    void exportExcel(HttpServletResponse response, QueryWrapper<User> wrapper) throws IOException;

    /**
     * 获取用户前端信息
     * @return 用户前端信息
     */
    UserInfoVo getUserInfo();

    /**
     * 获取用户菜单树
     * @return 'list<MenuNodeVo>'
     */
    List<MenuNodeVo> getUserMenus();
}
