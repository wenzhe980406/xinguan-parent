package top.chang888.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import top.chang888.common.entity.User;
import top.chang888.common.handler.BusinessException;
import top.chang888.common.response.Result;
import top.chang888.system.service.UserService;
import top.chang888.common.vo.UserVo;

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
@RequestMapping("/system/user")
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
    @PostMapping("/findUserByCondition")
    public Result findUserByCondition(@RequestParam(defaultValue = "1") Integer currentPage,
                                      @RequestParam(defaultValue = "10") Integer size,
                                      @RequestBody UserVo userVo) {
        Page<User> page = new Page<>(currentPage, size);

        QueryWrapper<User> wrapper = getWrapper(userVo);
        wrapper.eq("deleted", false);
        IPage<User> userByCondition = userService.findUserByCondition(page, wrapper);
        long total = userByCondition.getTotal();
        List<User> userList = userByCondition.getRecords();
        return Result.ok().data("total", total).data("data", userList);
    }

    @ApiOperation(value = "添加用户", notes = "添加用户信息")
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        try{
            userService.addUser(user);
            return Result.ok();
        } catch (BusinessException e) {
            log.info(e.getErrMsg());
            return Result.error().code(e.getCode()).message(e.getErrMsg());
        } catch (Exception e) {
            return Result.error();
        }
    }

    @ApiOperation(value = "编辑用户", notes = "编辑用户信息")
    @PutMapping("/edit")
    public Result edit(@RequestBody User user) {
        try{
            userService.editUser(user);
            return Result.ok();
        } catch (BusinessException e) {
            log.info(e.getErrMsg());
            return Result.error().code(e.getCode()).message(e.getErrMsg());
        } catch (Exception e) {
            return Result.error();
        }
    }

    @ApiOperation(value = "修改用户状态", notes = "修改用户状态")
    @PutMapping("/edit/status/{id}/{status}")
    public Result editStatus(@PathVariable("id") Integer id,
                             @PathVariable("status") Integer status) {
        try{
            userService.editUserStatus(id, status);
            return Result.ok();
        } catch (BusinessException e) {
            log.info(e.getErrMsg());
            return Result.error().code(e.getCode()).message(e.getErrMsg());
        } catch (Exception e) {
            return Result.error();
        }
    }

    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        try{
            userService.deleteUser(id);
            return Result.ok();
        } catch (BusinessException e) {
            log.info(e.getErrMsg());
            return Result.error().code(e.getCode()).message(e.getErrMsg());
        } catch (Exception e) {
            return Result.error();
        }
    }

    private QueryWrapper<User> getWrapper(UserVo userVo) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (Objects.nonNull(userVo)) {
            if (!ObjectUtils.isEmpty(userVo.getDepartmentId())){
                wrapper.eq("department_id", userVo.getDepartmentId());
            }
            if (!ObjectUtils.isEmpty(userVo.getUsername())){
                wrapper.eq("username", userVo.getUsername());
            }
            if (!ObjectUtils.isEmpty(userVo.getNickname())){
                wrapper.eq("nickname", userVo.getNickname());
            }
            if (!ObjectUtils.isEmpty(userVo.getEmail())){
                wrapper.eq("email", userVo.getEmail());
            }
            if (!ObjectUtils.isEmpty(userVo.getSex())){
                wrapper.eq("sex", userVo.getSex());
            }
        }
        return wrapper;
    }
}

