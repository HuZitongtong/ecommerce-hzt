package com.hzt.controller;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.hzt.service.IUmsRoleUserService;
import com.hzt.service.IUmsUserService;
import com.hzt.util.ResultJson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色与用户关联表 前端控制器
 * </p>
 *
 * @author huzt
 * @since 2022-04-22
 */
@RestController
@RequestMapping("/ums-role-user")
public class UmsRoleUserController {
    @Resource
    IUmsUserService userService;
    @Resource
    IUmsRoleUserService roleUserService;
    //获取所有用户
    @GetMapping("/getData")
    ResultJson getData(Long roleId) {
        Map<String, Object> map = new HashMap<>();
        //放入所有有效用户为users
        map.put("users", userService.getAll());
        //放入该roleid对应用户为values
        map.put("values", roleUserService.getUsersByRoleId(roleId));
        return ResultJson.success(map);
    }
    @PostMapping("/save")
    ResultJson save(Long roleId ,Long[] userIds) {
        return ResultJson.success(roleUserService.save(roleId, userIds),"分配角色成功！");
    }

}
