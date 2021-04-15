package top.chang888.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import top.chang888.common.response.Result;
import top.chang888.system.service.UserService;

import javax.annotation.Resource;

/**
 * @author changyw
 * @date 2021/4/15
 */

@Api(value = "登录模块")
@Slf4j
@RestController
public class LoginController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    public Result info() {

        return Result.ok();
    }

}
