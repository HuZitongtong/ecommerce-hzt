package com.hzt.service.impl;

import com.hzt.pojo.PmsSpu;
import com.hzt.mapper.PmsSpuMapper;
import com.hzt.service.IPmsSpuService;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.assertj.core.util.Arrays;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品规格列表 服务实现类
 * </p>
 *
 * @author huzt
 * @since 2022-04-26
 */
@Service
public class PmsSpuServiceImpl extends ServiceImpl<PmsSpuMapper, PmsSpu> implements IPmsSpuService {

    @Override
    public List<PmsSpu> list(Long categoryId) {
        QueryWrapper<PmsSpu> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id", categoryId);
        return this.list(wrapper);
    }

    @Override
    public List<PmsSpu> getByCategory(Long[] categoryIds) {
        List<Object> ids = Arrays.asList(categoryIds);
        QueryWrapper<PmsSpu> wrapper = new QueryWrapper<>();
        wrapper.in("category_id", ids);
        return this.list(wrapper );
    }

}
