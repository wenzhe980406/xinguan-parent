package top.chang888.system.mapper;

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
     * 查找部门和数量
     * @return list
     */
    List<Department> findDeptAndCount();
}
