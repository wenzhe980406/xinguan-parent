package top.chang888.system.controller;


import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("system/dept")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "查询部门及人数", notes = "从部门表中分组查询用户人数")
    @GetMapping("/findDeptAndCount")
    public Result findDeptAndCount(@RequestParam(defaultValue = "1") Integer currentPage,
                                   @RequestParam(defaultValue = "10") Integer size,
                                   @Nullable @RequestParam(defaultValue = "") String name) {
        Page<Department> page = new Page<>(currentPage, size);
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(name)) {
            wrapper.eq("name", name);
        }
        IPage<Department> deptByCondition = departmentService.findDeptAndCount(page, wrapper);
        long total = deptByCondition.getTotal();
        List<Department> userList = deptByCondition.getRecords();
        return Result.ok().data("total", total).data("depts", userList);
    }

    @ApiOperation(value = "添加部门", notes = "添加部门")
    @PostMapping("/add")
    public Result add(@RequestBody Department department) {
        try {
            departmentService.add(department);
            return Result.ok().message("添加部门成功!");
        } catch (Exception e) {
            return Result.error().message("添加部门失败!");
        }
    }

    @ApiOperation(value = "编辑部门", notes = "编辑部门")
    @PutMapping("/edit")
    public Result edit(@RequestBody Department department) {
        try {
            departmentService.edit(department);
            return Result.ok().message("编辑部门成功!");
        } catch (Exception e) {
            return Result.error().message("编辑部门失败!");
        }
    }

    @ApiOperation(value = "删除部门", notes = "删除部门")
    @PutMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        try {
            departmentService.delete(id);
            return Result.ok().message("删除部门成功!");
        } catch (Exception e) {
            return Result.error().message("删除部门失败!");
        }
    }
}
