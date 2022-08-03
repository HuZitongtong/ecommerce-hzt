package com.hzt.service;

import com.hzt.pojo.UmsRole;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author huzt
 * @since 2022-04-21
 */
public interface IUmsRoleService extends IService<UmsRole> {
    List<UmsRole> list(String name);
}
