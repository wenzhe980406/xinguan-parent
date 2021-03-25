package top.chang888.system.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.chang888.response.Result;
import top.chang888.system.entity.User;
import top.chang888.system.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author changyw
 * @since 2021-03-25
 */
@Api(value = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 分页查询用户列表
     * @param currentPage 当前页码
     * @param size 每页显示条数大小
     * @return list
     */
    @ApiOperation(value = "分页查询用户列表", notes = "通过传入currentPage和size返回当前页的数据")
    @GetMapping("/")
    public Result findUserList(@RequestParam(defaultValue = "1") Integer currentPage,
                               @RequestParam(defaultValue = "10") Integer size) {
        Page<User> page = new Page<>(currentPage, size);

        Page<User> userPage = userService.page(page);
        long total = userPage.getTotal();
        List<User> userList = userPage.getRecords();
        return Result.ok().data("total", total).data("data", userList);
    }
}

