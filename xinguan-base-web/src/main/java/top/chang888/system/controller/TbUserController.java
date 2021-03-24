package top.chang888.system.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.chang888.response.Result;
import top.chang888.system.entity.TbUser;
import top.chang888.system.service.TbUserService;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author changyw
 * @since 2021-03-24
 */
@RestController
@RequestMapping("/system/tb-user")
@Api(value = "用户管理")
public class TbUserController {

    @Autowired
    private TbUserService tbUserService;

    @GetMapping
    @ApiOperation(value = "查询所有的用户信息", notes = " ")
    public Result findUsers() {
        List<TbUser> list = tbUserService.list();
        return Result.ok().data("users", list);
    }
}

