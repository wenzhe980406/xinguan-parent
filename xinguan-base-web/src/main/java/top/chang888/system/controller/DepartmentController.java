package top.chang888.system.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.chang888.handler.BusinessException;
import top.chang888.response.Result;
import top.chang888.response.ResultCode;
import top.chang888.system.entity.Department;
import top.chang888.system.service.DepartmentService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author changyw
 * @since 2021-03-25
 */
@Api(value = "部门管理")
@RestController
@RequestMapping("/dept")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "查询部门及人数", notes = "从部门表中分组查询用户人数")
    @GetMapping("/findDeptAndCount")
    public Result findDeptAndCount() {
        List<Department> departments = departmentService.findDeptAndCount();
        if (departments.size() == 0) {
            throw new BusinessException(ResultCode.DEPARTMENT_NOT_EXIST.getCode(),
                    ResultCode.DEPARTMENT_NOT_EXIST.getMessage());
        } else {
            return Result.ok().data("depts", departments);
        }
    }

}

