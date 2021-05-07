package top.chang888.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import top.chang888.common.converter.MenuConverter;
import top.chang888.common.dto.UserLoginDTO;
import top.chang888.common.entity.Department;
import top.chang888.common.entity.Menu;
import top.chang888.common.entity.User;
import top.chang888.common.enums.UserStatusEnum;
import top.chang888.common.enums.UserTypeEnum;
import top.chang888.common.handler.BusinessException;
import top.chang888.common.response.ResultCode;
import top.chang888.common.utils.MenuTreeUtils;
import top.chang888.common.vo.system.MenuNodeVo;
import top.chang888.common.vo.system.UserInfoVo;
import top.chang888.system.mapper.DepartmentMapper;
import top.chang888.system.mapper.MenuMapper;
import top.chang888.system.mapper.UserMapper;
import top.chang888.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

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
@Transactional(propagation = Propagation.NESTED, isolation = Isolation.DEFAULT, rollbackFor=Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findUserByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        List<User> userList = this.baseMapper.findUserByCondition(wrapper);

        if (userList.size() != 1) {
            throw new BusinessException(ResultCode.DATABASE_USER_USERNAME_REPET);
        }

        return userList.get(0);
    }

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
            throw new BusinessException(ResultCode.DEPARTMENT_NOT_EXIST);
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

    @Override
    public void exportExcel(HttpServletResponse response, QueryWrapper<User> wrapper) throws IOException {
        List<User> users = this.baseMapper.findUserByCondition(wrapper);

        String filename = URLEncoder.encode("用户信息表.xlsx", "UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        EasyExcel.write(response.getOutputStream(), User.class)
                .sheet("用户表")
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .doWrite(users);
    }

    @Override
    public UserInfoVo getUserInfo() {
        UserLoginDTO userLoginDTO = getSecurityContextUser();
        log.info("UserServiceImpl - [getUserInfo ] principal -> {{}} ", userLoginDTO);
        return userLoginDTO.getUserInfoVo();
    }

    @Override
    public List<MenuNodeVo> getUserMenus() {
        List<Menu> menus;
        UserLoginDTO userLoginDTO = getSecurityContextUser();

        if (userLoginDTO.getUserInfoVo().getIsAdmin()) {
            menus = menuMapper.selectList(new QueryWrapper<>());
        } else {
            menus = userLoginDTO.getMenus();
        }

        List<MenuNodeVo> menuNodeVos = new ArrayList<>();
        if (!CollectionUtil.isEmpty(menus)) {
            menuNodeVos = MenuConverter.converterMenu2MenuNodeVo(menus);
        }

        return MenuTreeUtils.build(menuNodeVos);
    }

    /**
     * 获取spring security 上下文用户详细信息
     * @return UserLoginDTO
     */
    public UserLoginDTO getSecurityContextUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserLoginDTO) authentication.getPrincipal();
    }

    public void findUserExist(String qw, ResultCode code) {
        // 判断是否添加了同一个用户 (username 相同)
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", qw);
        Integer count = this.baseMapper.selectCount(wrapper);
        if (count != 0) {
            throw new BusinessException(code);
        }
    }
}
