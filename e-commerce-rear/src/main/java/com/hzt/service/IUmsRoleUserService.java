package com.hzt.service;

import com.hzt.pojo.UmsRoleUser;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色与用户关联表 服务类
 * </p>
 *
 * @author huzt
 * @since 2022-04-22
 */
public interface IUmsRoleUserService extends IService<UmsRoleUser> {
    //存储所有角色关联的用户到RoleUser表
    boolean save(Long roleId ,Long[] userIds);
    //在roleuser数据表中获取该roleid对应的数据
    List<UmsRoleUser> getUsersByRoleId(Long roleId);
}
