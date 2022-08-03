package com.hzt.service;

import com.hzt.pojo.OmsProduct;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单中的商品表 服务类
 * </p>
 *
 * @author huzt
 * @since 2022-05-10
 */
public interface IOmsProductService extends IService<OmsProduct> {
    List<OmsProduct> getByOrderId(Long orderId);
}
