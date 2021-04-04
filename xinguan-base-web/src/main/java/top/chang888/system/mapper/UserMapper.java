package top.chang888.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.chang888.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author changyw
 * @since 2021-03-25
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过查询条件分页查询用户列表
     * @param page 当前页码
     * @param queryWrapper 增加查询条件 这里的queryWrapper前必须添加@param注解 不然xml中${ew.customSqlSegment}无法注入
     * @return IPage 固定返回参数
     */
    IPage<User> findUserByCondition(Page<User> page, @Param(Constants.WRAPPER) QueryWrapper<User> queryWrapper);

    /**
     * 通过id修改用户状态
     * @param id 用户id
     * @param status 当前状态
     */
    void updateStatusById(@Param("id") Integer id, @Param("status") Integer status);

}
