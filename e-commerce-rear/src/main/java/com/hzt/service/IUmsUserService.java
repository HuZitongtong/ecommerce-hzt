package com.hzt.service;

import com.hzt.pojo.UmsUser;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huzt
 * @since 2022-04-18
 */
public interface IUmsUserService extends IService<UmsUser> {
    
    List<UmsUser> getAll();//获取所有有效对象
    IPage<UmsUser> page(Integer pageNo,Integer pageSize, String name);//根据昵称模糊查询用户
    String login(String username,String password) throws Exception;
}
