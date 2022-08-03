package com.hzt.controller;


import javax.annotation.Resource;

import com.hzt.pojo.PmsSku;
import com.hzt.service.IPmsSkuService;
import com.hzt.util.ResultJson;

import org.duracloud.common.retry.Retriable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品参数列表 前端控制器
 * </p>
 *
 * @author huzt
 * @since 2022-04-26
 */
@RestController
@RequestMapping("/pms-sku")
public class PmsSkuController {
    @Resource
    IPmsSkuService skuService;
    @GetMapping("/list")
    ResultJson list(Long categoryId) {
        return ResultJson.success(skuService.list(categoryId));
    }
    @GetMapping("/getone")
    ResultJson getone(Long id) {
        return ResultJson.success(skuService.getById(id));
    }
    @PostMapping("/add")
    ResultJson add(PmsSku pmsSku) {
        return ResultJson.success(skuService.save(pmsSku),"添加商品参数成功！");
    }
    @PostMapping("/update")
    ResultJson update(PmsSku pmsSku) {
        return ResultJson.success(skuService.updateById(pmsSku),"商品参数修改成功！");
    }
    @PostMapping("/del")
    ResultJson del(Long id) {
        return ResultJson.success(skuService.removeById(id),"商品参数删除成功！");
    }
}
