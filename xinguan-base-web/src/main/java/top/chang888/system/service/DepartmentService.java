package top.chang888.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.extension.service.IService;
import top.chang888.common.entity.Department;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author changyw
 * @since 2021-03-25
 */
public interface DepartmentService extends IService<Department> {

    /**
     * 通过查询条件分页查询部门列表
     * @param page 当前页码
     * @param wrapper 增加查询条件
     * @return IPage 固定返回参数
     */
    IPage<Department> findDeptAndCount(Page<Department> page, QueryWrapper<Department> wrapper);

    /**
     * 增加部门
     * @param department 部门
     */
    void add(Department department);

    /**
     * 通过 id 编辑部门
     * @param department 部门
     */
    void edit(Department department);

    /**
     * 通过 id 删除部门
     * @param id 部门id
     */
    void delete(Integer id);
}
