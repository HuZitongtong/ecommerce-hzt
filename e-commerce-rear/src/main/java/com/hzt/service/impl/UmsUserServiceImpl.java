package com.hzt.service.impl;

import com.hzt.pojo.UmsUser;
import com.hzt.mapper.UmsUserMapper;
import com.hzt.service.IUmsUserService;

import static org.mockito.Mockito.never;

import java.util.List;

import javax.annotation.Resource;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huzt
 * @since 2022-04-18
 */
@Service
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements IUmsUserService {

    @Resource
    BCryptPasswordEncoder passwordEncoder;
    @Override
    public List<UmsUser> getAll() {
        // 查所有有效的用户
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("active", 1);
        return this.list(wrapper);
    }

    @Override
    public IPage<UmsUser> page(Integer pageNo, Integer pageSize, String name) {
        // TODO Auto-generated method stub
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        //对于name为 null或者“”或者“  ” 查找全部
        if(StringUtils.isNotBlank(name)) {
            wrapper.like("nicky_name", name);
        }
        return this.page(new Page<>(pageNo,pageSize),wrapper);
    }

    @Override
    public String login(String username, String password) throws Exception {
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", username);
        UmsUser user = this.getOne(wrapper);
        if(user == null) {
            throw new Exception("用户名或密码错！");
        }
        if(user.getActive() == 0) {
            throw new Exception("该用户已经失效，无法登陆！");
        }
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("用户名或密码错！");
        }
        String token = JWT.create().withClaim("username", username)
                    .sign(Algorithm.HMAC256("hzt"));//使用jwt工具包生成token
        return token;
    }

}
