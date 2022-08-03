package com.hzt.service.impl;

import com.hzt.pojo.PmsProduct;
import com.hzt.mapper.PmsProductMapper;
import com.hzt.service.IPmsProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author huzt
 * @since 2022-04-27
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements IPmsProductService {

}
