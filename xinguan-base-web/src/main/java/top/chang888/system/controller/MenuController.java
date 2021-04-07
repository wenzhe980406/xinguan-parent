package top.chang888.system.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.chang888.common.response.Result;
import top.chang888.common.entity.Menu;
import top.chang888.common.vo.system.MenuNodeVo;
import top.chang888.common.vo.system.MenuVo;
import top.chang888.system.service.MenuService;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author changyw
 * @since 2021-04-06
 */
@Api(value = "菜单管理")
@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "获取所有菜单", notes = "获取所有菜单")
    @GetMapping("/")
    public Result get() {
        try {
            List<MenuNodeVo> menuList = menuService.get();
            return Result.ok().message("获取所有菜单成功！").data("tree", menuList);
        } catch (Exception e) {
            return Result.error().message("获取所有菜单失败！");
        }
    }

    @ApiOperation(value = "增加菜单节点", notes = "增加菜单节点")
    @PostMapping("/add")
    public Result add(@RequestBody MenuVo menuVo) {
        try {
            menuService.add(menuVo);
            return Result.ok().message("增加菜单节点成功！");
        } catch (Exception e) {
            return Result.error().message("增加菜单节点失败！");
        }
    }

    @ApiOperation(value = "删除菜单节点", notes = "删除菜单节点")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        try {
            menuService.delete(id);
            return Result.ok().message("删除菜单节点成功！");
        } catch (Exception e) {
            return Result.error().message("删除菜单节点失败！");
        }
    }

    @ApiOperation(value = "更新菜单节点", notes = "更新菜单节点")
    @PutMapping("/edit")
    public Result edit(@RequestBody MenuVo menuVo) {
        try {
            menuService.edit(menuVo);
            return Result.ok().message("更新菜单节点成功！");
        } catch (Exception e) {
            return Result.error().message("更新菜单节点失败！");
        }
    }

}

