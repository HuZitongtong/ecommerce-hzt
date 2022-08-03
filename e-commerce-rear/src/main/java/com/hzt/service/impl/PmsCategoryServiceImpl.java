package com.hzt.service.impl;

import com.hzt.pojo.PmsCategory;
import com.hzt.mapper.PmsCategoryMapper;
import com.hzt.service.IPmsCategoryService;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服装分类表 服务实现类
 * </p>
 *
 * @author huzt
 * @since 2022-04-25
 */
@Service
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, PmsCategory> implements IPmsCategoryService {

    private List<PmsCategory> getByParentId(Long parentId, Integer active) {
        QueryWrapper<PmsCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        if(active != null) {
            wrapper.eq("active", active);
        }
        List<PmsCategory> list = this.list(wrapper);
        for (PmsCategory pmsCategory : list) {
            //如果该父类没有被删除 再查子类
            if(pmsCategory.getActive() == 1) {
                pmsCategory.setChildren(getByParentId(pmsCategory.getId()));
            }         
        }
        return list;
    }
    @Override
    public List<PmsCategory> getByParentId(Long parentId) {
        return this.getByParentId(parentId, null);
    }
    @Override
    public List<PmsCategory> getAll(Long parentId) {
        return this.getByParentId(parentId, 1);
    }

    // @Override
    // public List<PmsCategory> getAll() {
    //     QueryWrapper<PmsCategory> wrapper = new QueryWrapper<>();
    //     wrapper.eq("active", 1);
    //     return this.list(wrapper);
    // }

}
