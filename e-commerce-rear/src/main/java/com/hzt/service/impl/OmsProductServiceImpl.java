package com.hzt.service.impl;

import com.hzt.pojo.OmsProduct;
import com.hzt.mapper.OmsProductMapper;
import com.hzt.service.IOmsProductService;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单中的商品表 服务实现类
 * </p>
 *
 * @author huzt
 * @since 2022-05-10
 */
@Service
public class OmsProductServiceImpl extends ServiceImpl<OmsProductMapper, OmsProduct> implements IOmsProductService {

    @Override
    public List<OmsProduct> getByOrderId(Long orderId) {
        QueryWrapper<OmsProduct> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", orderId);
        return this.list(wrapper);
    }

}
