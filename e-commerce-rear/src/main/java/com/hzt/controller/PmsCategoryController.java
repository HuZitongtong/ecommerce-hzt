package com.hzt.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hzt.pojo.PmsCategory;
import com.hzt.service.IPmsCategoryService;
import com.hzt.util.ResultJson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 服装分类表 前端控制器
 * </p>
 *
 * @author huzt
 * @since 2022-04-25
 */
@RestController
@RequestMapping("/pms-category")
public class PmsCategoryController {
    @Resource
    IPmsCategoryService categoryService;
    @GetMapping("/list")
    ResultJson list() {
        return ResultJson.success(categoryService.getByParentId(0L));
    }
    @PostMapping("/add") 
    ResultJson add(PmsCategory pmsCategory) {
        return ResultJson.success(categoryService.save(pmsCategory),"添加服装分类成功！");
    }
    @GetMapping("/getone")
    ResultJson getone(Long id) {
        return ResultJson.success(categoryService.getById(id));
    }  
    @PostMapping("/update") 
    ResultJson update(PmsCategory pmsCategory) {
        return ResultJson.success(categoryService.updateById(pmsCategory),"修改分类成功！");
    }
    @PostMapping("/del")
    ResultJson del(PmsCategory pmsCategory) {
        String message = pmsCategory.getActive() == 0 ? "删除分类成功！" : "恢复分类成功！";
        return ResultJson.success(categoryService.updateById(pmsCategory),message);
    }
    //获取所有有效的分类
    @GetMapping("/getAll")
    ResultJson getAll() {
        Map<String,List> map = new HashMap<>();
        map.put("categorys", categoryService.getAll(0L));
        return ResultJson.success(map);
    }
    

}
