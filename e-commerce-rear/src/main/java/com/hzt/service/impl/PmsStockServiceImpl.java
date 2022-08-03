package com.hzt.service.impl;

import com.hzt.pojo.PmsStock;
import com.hzt.mapper.PmsStockMapper;
import com.hzt.service.IPmsStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * sku库存表 服务实现类
 * </p>
 *
 * @author huzt
 * @since 2022-05-09
 */
@Service
public class PmsStockServiceImpl extends ServiceImpl<PmsStockMapper, PmsStock> implements IPmsStockService {

}
