package top.chang888.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.chang888.common.entity.Role;
import top.chang888.common.handler.BusinessException;
import top.chang888.common.response.Result;
import top.chang888.system.service.RoleService;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author changyw
 * @since 2021-04-06
 */
@Api("角色管理")
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取角色权限信息", notes = "分页获取角色权限信息")
    @GetMapping("/get")
    public Result get(@RequestParam(defaultValue = "1") Integer currentPage,
                      @RequestParam(defaultValue = "10") Integer size,
                      @Nullable @RequestParam(defaultValue = "") String name) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(name)){
            wrapper.eq("role_name", name);
        }
        Page<Role> rolePage = new Page<>(currentPage, size);
        try {
            IPage<Role> roleByCondition = roleService.findRoleByCondition(rolePage, wrapper);
            long total = roleByCondition.getTotal();
            List<Role> roleList = roleByCondition.getRecords();
            return Result.ok().message("获取role关系表成功!").data("total", total).data("data", roleList);
        } catch (BusinessException e) {
            return Result.error().message(e.getErrMsg());
        } catch (Exception e){
            return Result.error().message("获取role关系表失败!");
        }
    }
}

