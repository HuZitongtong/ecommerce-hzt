package com.hzt.service.impl;

import com.hzt.pojo.UmsRoleUser;
import com.hzt.mapper.UmsRoleUserMapper;
import com.hzt.service.IUmsRoleUserService;

import static org.mockito.Mockito.never;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色与用户关联表 服务实现类
 * </p>
 *
 * @author huzt
 * @since 2022-04-22
 */
@Service
public class UmsRoleUserServiceImpl extends ServiceImpl<UmsRoleUserMapper, UmsRoleUser> implements IUmsRoleUserService {

    @Override
    @Transactional
    public boolean save(Long roleId, Long[] userIds) {
        //为了做到数据更新，每次删除roleId对应的全部数据，再重新存入
        QueryWrapper<UmsRoleUser> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        this.remove(wrapper);
        //有多少个用户id就要保存多少条
        //给umsroleuser类加一个无参构造和全参构造方法
        List<UmsRoleUser> list = new ArrayList<>();
        for(Long userId : userIds) {
            list.add(new UmsRoleUser(roleId, userId));
        }

        return this.saveBatch(list);
    }

    @Override
    public List<UmsRoleUser> getUsersByRoleId(Long roleId) {
        // TODO Auto-generated method stub
        QueryWrapper<UmsRoleUser> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        return this.list(wrapper);
    }

}
