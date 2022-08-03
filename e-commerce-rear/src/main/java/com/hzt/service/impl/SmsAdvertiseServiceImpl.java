package com.hzt.service.impl;

import com.hzt.pojo.SmsAdvertise;
import com.hzt.mapper.SmsAdvertiseMapper;
import com.hzt.service.ISmsAdvertiseService;

import javax.print.attribute.standard.PagesPerMinute;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 轮播广告表 服务实现类
 * </p>
 *
 * @author huzt
 * @since 2022-05-09
 */
@Service
public class SmsAdvertiseServiceImpl extends ServiceImpl<SmsAdvertiseMapper, SmsAdvertise> implements ISmsAdvertiseService {

    @Override
    public IPage<SmsAdvertise> page(Integer pageNo, Integer pageSize, String name) {
        QueryWrapper<SmsAdvertise> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }
        return this.page(new Page<>(pageNo,pageSize),wrapper);
    }

}
