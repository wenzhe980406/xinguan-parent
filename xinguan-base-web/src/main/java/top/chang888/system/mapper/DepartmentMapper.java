package top.chang888.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.chang888.system.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author changyw
 * @since 2021-03-25
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 通过查询条件分页查询部门列表 和 数量
     * @param page 当前页码
     * @param wrapper 增加查询条件
     * @return IPage 固定返回参数
     */
    IPage<Department> findDeptAndCount(Page<Department> page, @Param(Constants.WRAPPER) QueryWrapper<Department> wrapper);

}
