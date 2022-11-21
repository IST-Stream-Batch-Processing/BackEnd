package cn.ist.lowcoding.userservice.controller;

import cn.ist.lowcoding.common.model.QueryCondition;
import cn.ist.lowcoding.common.model.Role;
import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import cn.ist.lowcoding.userservice.model.User;
import cn.ist.lowcoding.userservice.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(tags = "用户管理")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "获取所有用户信息")
    @GetMapping("/user")
    public Result<List<User>> findUsers() {
        return ResultUtil.success(userService.findAll());
    }

    @ApiOperation(value = "用户条件查询", notes = "用户作为数据服务的内置数据，可进行条件查询")
    @PostMapping("/user/query")
    public Result<List<User>> queryUsers(@RequestBody List<QueryCondition> queryConditions) {
        return ResultUtil.success(userService.query(queryConditions));
    }

    @ApiOperation(value = "根据多个id获取多个用户信息")
    @PostMapping("/user/ids")
    public Result<List<User>> findUsersByIds(@RequestBody List<String> ids) {
        return ResultUtil.success(userService.findByIds(ids));
    }

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public Result<String> register(String username, String password) {
        userService.register(username, password);
        return ResultUtil.success();
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result<String> login(String username, String password) {
        String token = userService.login(username, password);
        return ResultUtil.success(token);
    }

    @ApiOperation(value = "配置用户权限", notes = "为用户分配研发和管理权限，仅管理人员可分配")
    @PutMapping("/user/{id}/role")
    public Result<String> authorize(@PathVariable String id, @RequestBody List<Role> roles) {
        userService.authorize(id, roles);
        return ResultUtil.success();
    }
}
