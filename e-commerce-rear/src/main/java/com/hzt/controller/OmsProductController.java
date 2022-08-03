package com.hzt.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hzt.pojo.OmsProduct;
import com.hzt.service.IOmsOrderService;
import com.hzt.service.IOmsProductService;
import com.hzt.util.ResultJson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单中的商品表 前端控制器
 * </p>
 *
 * @author huzt
 * @since 2022-05-10
 */
@RestController
@RequestMapping("/oms-product")
public class OmsProductController {
    @Resource
    IOmsProductService omsProductService;
    @Resource
    IOmsOrderService omsOrderService;
    @GetMapping("/getByOrderId")
    ResultJson getByOrderId(Long orderId) {
        Map<String,List> map = new HashMap<>();
        List<OmsProduct> omsProducts = omsProductService.getByOrderId(orderId);
        return ResultJson.success("111111");
    }
}
