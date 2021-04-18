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

}

