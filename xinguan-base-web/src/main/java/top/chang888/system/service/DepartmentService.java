package top.chang888.system.service;

import top.chang888.system.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

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

    List<Department> findDeptAndCount();

}
