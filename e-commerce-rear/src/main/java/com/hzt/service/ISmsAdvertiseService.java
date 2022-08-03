package com.hzt.service;

import com.hzt.pojo.SmsAdvertise;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 轮播广告表 服务类
 * </p>
 *
 * @author huzt
 * @since 2022-05-09
 */
public interface ISmsAdvertiseService extends IService<SmsAdvertise> {
    IPage<SmsAdvertise> page(Integer pageNo,Integer pageSize, String name);
}
