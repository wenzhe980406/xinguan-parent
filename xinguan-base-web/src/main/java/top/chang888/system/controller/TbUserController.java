package top.chang888.system.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.chang888.handler.BusinessException;
import top.chang888.response.Result;
import top.chang888.response.ResultCode;
import top.chang888.system.entity.TbUser;
import top.chang888.system.service.TbUserService;

import java.util.List;
import java.util.Objects;

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
@Api(value = "系统用户模块", tags = "系统用户接口")
public class TbUserController {

    @Autowired
    private TbUserService tbUserService;

    @GetMapping
    @ApiOperation(value = "用户列表", notes = "查询所有用户信息")
    public Result findUsers() {
        List<TbUser> list = tbUserService.list();
        return Result.ok().data("users", list);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询单个用户", notes = "通过id查询对应的用户信息")
    public Result getUserById(@PathVariable("id") Long id) {
        TbUser user = tbUserService.getById(id);
        if (Objects.nonNull(user)){
            return Result.ok().data("user", user);
        } else {
            throw new BusinessException(ResultCode.USER_NOT_FOUND.getCode(), ResultCode.USER_NOT_FOUND.getMessage());
//            return Result.error()
//                    .code(ResultCode.USER_NOT_FOUND.getCode())
//                    .message(ResultCode.USER_NOT_FOUND.getMessage());
        }
    }
}

