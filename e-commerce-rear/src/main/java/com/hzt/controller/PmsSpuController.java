package com.hzt.controller;


import static org.mockito.Mockito.spy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hzt.pojo.PmsSpu;
import com.hzt.service.IPmsSkuService;
import com.hzt.service.IPmsSpuService;
import com.hzt.util.ResultJson;
import com.mysql.cj.xdevapi.Result;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品规格列表 前端控制器
 * </p>
 *
 * @author huzt
 * @since 2022-04-26
 */
@RestController
@RequestMapping("/pms-spu")
public class PmsSpuController {
    @Resource
    IPmsSpuService spuService;
    @Resource 
    IPmsSkuService skuService;
    @GetMapping("/list")
    ResultJson list(Long categoryId) {
        return ResultJson.success(spuService.list(categoryId));
    }
    @GetMapping("/getone")
    ResultJson getone(Long id) {
        return ResultJson.success(spuService.getById(id));
    }
    @PostMapping("/add")
    ResultJson add(PmsSpu pmsSpu) {
        return ResultJson.success(spuService.save(pmsSpu),"添加服装规格成功！");
    }
    @PostMapping("/update")
    ResultJson update(PmsSpu pmsSpu) {
        return ResultJson.success(spuService.updateById(pmsSpu),"修改服装规格成功！");
    }
    @PostMapping("/del")
    ResultJson del(Long id) {
        return ResultJson.success(spuService.removeById(id),"删除服装规格成功！");
    }
    @GetMapping("/getAttrs")
    ResultJson getAttrs(Long[] categoryIds){
        Map<String, List> map = new HashMap<>();
        map.put("spus", spuService.getByCategory(categoryIds));
        map.put("skus", skuService.getByCategory(categoryIds));
        return ResultJson.success(map);
    }
}
