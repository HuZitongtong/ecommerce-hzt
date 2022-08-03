package com.hzt.service;

import com.hzt.pojo.PmsSku;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品参数列表 服务类
 * </p>
 *
 * @author huzt
 * @since 2022-04-26
 */
public interface IPmsSkuService extends IService<PmsSku> {
    List<PmsSku> list(Long categoryId);
    List<PmsSku> getByCategory(Long[] categoryIds);
}
