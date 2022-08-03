package com.hzt.service;

import com.hzt.pojo.PmsSpu;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品规格列表 服务类
 * </p>
 *
 * @author huzt
 * @since 2022-04-26
 */
public interface IPmsSpuService extends IService<PmsSpu> {
    List<PmsSpu> list(Long categoryId);
    List<PmsSpu> getByCategory(Long[] categoryIds);
}
