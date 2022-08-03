package com.hzt.controller;


import javax.annotation.Resource;

import com.aliyuncs.ecs.model.v20140526.AttachKeyPairResponse.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzt.pojo.OmsOrder;
import com.hzt.service.IOmsOrderService;
import com.hzt.service.IOmsProductService;
import com.hzt.util.ResultJson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author huzt
 * @since 2022-05-01
 */
@RestController
@RequestMapping("/oms-order")
public class OmsOrderController {
    @Resource
    IOmsOrderService orderService;
    @Resource
    IOmsProductService omsProductService;
    @GetMapping("/list")
    ResultJson list(Integer pageNo, Integer pageSize) {
        return ResultJson.success(orderService.page(new Page<>(pageNo,pageSize)));
    }
    @GetMapping("/getone")
    ResultJson getone(Long id) {
        return ResultJson.success(orderService.getById(id));
    }
    @PostMapping("/update") 
    ResultJson update(OmsOrder omsOrder) {
        return ResultJson.success(orderService.updateById(omsOrder));
    }
    @GetMapping("/getProduct")
    ResultJson getProduct(Long id) {
        return ResultJson.success(omsProductService.getByOrderId(id));
    }
    

}
