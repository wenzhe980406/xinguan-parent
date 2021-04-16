package top.chang888.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chang888.common.response.Result;
import top.chang888.system.service.UserService;

import javax.annotation.Resource;

/**
 * @author changyw
 * @date 2021/4/16
 */

@Api(value = "登录模块")
@Slf4j
@RestController
public class LoginController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户登录", notes = "用户登录并返回用户token")
    @GetMapping("/login")
    public Result login() {
        log.info(" LoginController - [login]  用户点击了登录按钮 ");
        return Result.ok();
    }

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @GetMapping("/system/user/info")
    public Result info() {

        return Result.ok();
    }

}

