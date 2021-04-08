package top.chang888.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.chang888.common.entity.Role;
import top.chang888.common.response.Result;
import top.chang888.system.service.RoleService;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author changyw
 * @since 2021-04-06
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    public Result get(@RequestParam(defaultValue = "1") Integer currentPage,
                      @RequestParam(defaultValue = "10") Integer size,
                      @Nullable @RequestParam(defaultValue = "") String name) {
        try {
            QueryWrapper<Role> wrapper = new QueryWrapper<>();
            if (!ObjectUtils.isEmpty(name)){
                wrapper.eq("role_name", name);
            }

            List<Role> roles = roleService.get(currentPage, size, wrapper);
        }
    }
}

