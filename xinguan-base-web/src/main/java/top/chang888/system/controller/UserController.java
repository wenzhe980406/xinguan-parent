package top.chang888.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import top.chang888.response.Result;
import top.chang888.system.entity.User;
import top.chang888.system.service.UserService;
import top.chang888.system.vo.UserVo;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author changyw
 * @since 2021-03-25
 */
@Api(value = "用户管理")
@Slf4j
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

    /**
     * 分页查询用户列表
     * @param currentPage 当前页码
     * @param size 每页显示条数大小
     * @return list
     */
    @ApiOperation(value = "分页查询用户列表", notes = "通过传入currentPage和size返回当前页的数据")
    @PostMapping("/findUserByCondition")
    public Result findUserByCondition(@RequestParam(defaultValue = "1") Integer currentPage,
                                      @RequestParam(defaultValue = "10") Integer size,
                                      @RequestBody UserVo userVo) {
        log.info("findUserByCondition => UserVo" + Objects.isNull(userVo));
        Page<User> page = new Page<>(currentPage, size);

        QueryWrapper<User> wrapper = getWrapper(userVo);
        IPage<User> userByCondition = userService.findUserByCondition(page, wrapper);
        long total = userByCondition.getTotal();
        List<User> userList = userByCondition.getRecords();
        return Result.ok().data("total", total).data("data", userList);
    }

    private QueryWrapper<User> getWrapper(UserVo userVo) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (Objects.nonNull(userVo)) {
            if (StringUtils.isEmpty(userVo.getDepartmentId())){
                wrapper.eq("department_id", userVo.getDepartmentId());
            }
            if (StringUtils.isEmpty(userVo.getDepartmentId())){
                wrapper.eq("username", userVo.getUsername());
            }
            if (StringUtils.isEmpty(userVo.getDepartmentId())){
                wrapper.eq("nickname", userVo.getNickname());
            }
            if (StringUtils.isEmpty(userVo.getDepartmentId())){
                wrapper.eq("email", userVo.getEmail());
            }
            if (StringUtils.isEmpty(userVo.getDepartmentId())){
                wrapper.eq("sex", userVo.getSex());
            }
        }
        return wrapper;
    }
}

