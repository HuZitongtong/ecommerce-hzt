package com.hzt.service.impl;

import com.hzt.pojo.PmsSku;
import com.hzt.mapper.PmsSkuMapper;
import com.hzt.service.IPmsSkuService;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.assertj.core.util.Arrays;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品参数列表 服务实现类
 * </p>
 *
 * @author huzt
 * @since 2022-04-26
 */
@Service
public class PmsSkuServiceImpl extends ServiceImpl<PmsSkuMapper, PmsSku> implements IPmsSkuService {

    @Override
    public List<PmsSku> list(Long categoryId) {
        QueryWrapper<PmsSku> wrapper = new QueryWrapper<>();
         wrapper.eq("category_id", categoryId);
        return this.list(wrapper);
    }

    @Override
    public List<PmsSku> getByCategory(Long[] categoryIds) {
        QueryWrapper<PmsSku> wrapper = new QueryWrapper<>();
        wrapper.in("category_id", Arrays.asList(categoryIds));
        return this.list(wrapper);
    }

}
