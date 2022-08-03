package com.hzt.service.impl;

import com.hzt.pojo.UmsRole;
import com.hzt.mapper.UmsRoleMapper;
import com.hzt.service.IUmsRoleService;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author huzt
 * @since 2022-04-21
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements IUmsRoleService {

    @Override
    public List<UmsRole> list(String name) {
        // TODO Auto-generated method stub
        QueryWrapper<UmsRole> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(name)) {
            //如果按照名字查询
            //模糊查询
            wrapper.like("name", name);
        }
        return this.list(wrapper);
    }

}
