package top.chang888.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.chang888.common.entity.Department;
import top.chang888.common.handler.BusinessException;
import top.chang888.common.response.ResultCode;
import top.chang888.system.mapper.DepartmentMapper;
import top.chang888.system.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author changyw
 * @since 2021-03-25
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Override
    public IPage<Department> findDeptAndCount(Page<Department> page, QueryWrapper<Department> wrapper) {
        return this.baseMapper.findDeptAndCount(page, wrapper);
    }

    @Override
    public void add(Department department) {
        // 判断是否添加了同一个部门
        findDeptExist("name", department.getName(), ResultCode.DEPARTMENT_ALREADY_EXIST);
        this.baseMapper.insert(department);
    }

    @Override
    public void edit(Department department) {
        findDeptExist("id", department.getId().toString(), ResultCode.DEPARTMENT_ALREADY_EXIST);
        findDeptExist("name", department.getName(), ResultCode.DEPARTMENT_ALREADY_EXIST);
        this.baseMapper.updateById(department);
    }

    @Override
    public void delete(Integer id) {
        this.baseMapper.deleteById(id);
    }

    public void findDeptExist(String field, String qw, ResultCode code) {
        // 判断是否添加了同一个用户 (username 相同)
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper.eq(field, qw);
        Integer count = this.baseMapper.selectCount(wrapper);
        if (count != 0) {
            throw new BusinessException(code.getCode(), code.getMessage());
        }
    }
}
