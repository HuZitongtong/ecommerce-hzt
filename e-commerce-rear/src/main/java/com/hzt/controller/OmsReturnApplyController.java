package com.hzt.controller;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzt.pojo.OmsAddress;
import com.hzt.pojo.OmsProduct;
import com.hzt.pojo.OmsReturnApply;
import com.hzt.service.IOmsAddressService;
import com.hzt.service.IOmsProductService;
import com.hzt.service.IOmsReturnApplyService;
import com.hzt.util.ResultJson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 退货申请表 前端控制器
 * </p>
 *
 * @author huzt
 * @since 2022-05-11
 */
@RestController
@RequestMapping("/oms-return-apply")
public class OmsReturnApplyController {
    @Resource
    IOmsReturnApplyService returnApplyService;
    @Resource
    IOmsProductService productService;
    @Resource
    IOmsAddressService addressService;
    @GetMapping("/list")
    ResultJson list(Integer pageNo,Integer pageSize) {
        return ResultJson.success(returnApplyService.page(new Page<>(pageNo,pageSize)));
    }
    @GetMapping("/getDetail")
    ResultJson getDetail(Long id) {
        //商品信息 List
        OmsReturnApply omsReturnApply = returnApplyService.getById(id);
        Long orderId = omsReturnApply.getOrderId();
        List<OmsProduct> productList = productService.getByOrderId(orderId);
        //服务单号信息
        List<OmsReturnApply> applyList = new ArrayList<>();
        applyList.add(omsReturnApply);
        //地址信息
        Long addressId = omsReturnApply.getAddressId();
        OmsAddress omsAddress = addressService.getById(addressId);
        List<OmsAddress> addressList = new ArrayList<>();
        addressList.add(omsAddress);
        Map<String,List> map = new HashMap<>();
        map.put("productList", productList);
        map.put("applyList", applyList);
        map.put("addressList", addressList);
        return ResultJson.success(map);
    }
    @PostMapping("/updateStatus")
    ResultJson updateStatus(OmsReturnApply omsReturnApply) {
        return ResultJson.success(returnApplyService.updateById(omsReturnApply));
    }
}
