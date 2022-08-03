package com.hzt.controller;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.print.attribute.standard.PagesPerMinute;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.JsonObject;
import com.hzt.pojo.PmsProduct;
import com.hzt.pojo.PmsSkuValue;
import com.hzt.pojo.PmsSpuValue;
import com.hzt.pojo.PmsStock;
import com.hzt.service.IImgService;
import com.hzt.service.IPmsProductService;
import com.hzt.service.IPmsSkuValueService;
import com.hzt.service.IPmsSpuValueService;
import com.hzt.service.IPmsStockService;
import com.hzt.util.ResultJson;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author huzt
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/pms-product")
public class PmsProductController {
    @Resource 
    IPmsProductService productService;
    @Resource
    IImgService imgService;
    @Resource 
    IPmsSpuValueService spuValueService;
    @Resource
    IPmsSkuValueService skuValueService;
    @Resource
    IPmsStockService stockService;
    @GetMapping("/list")
    ResultJson list(Integer pageNo, Integer pageSize) {
        return ResultJson.success(productService.page(new Page<>(pageNo,pageSize)));
    }
    @PostMapping("/add") 
    @Transactional
    ResultJson add(PmsProduct pmsProduct, MultipartFile file, MultipartFile[] files, String[] spus, String[] skus, String[] stocks) throws IOException {
        // System.out.println(pmsProduct);
        // System.out.println(file);
        // System.out.println(files.length);
        // System.out.println(Arrays.toString(spus));
        // System.out.println(Arrays.toString(skus));
        // System.out.println(Arrays.toString(stocks));
        // pmsProduct.setImg(imgService.upload(file));
        pmsProduct.setImg(imgService.upload(file));
        List<String> piclist = new ArrayList<>();
        for(MultipartFile f : files) {
            piclist.add(imgService.upload(f));
        }
        //去个中括号存储哈
        String pics =  piclist.toString().replaceAll("\\[","").replaceAll("\\]", "");
        pmsProduct.setPics(pics);
        productService.save(pmsProduct);

        //反序列化
        List<PmsSpuValue> spuValueList = new ArrayList<>();
        for(String spu : spus) {
            //此处spuValue需要productId 所以 需要先保存product 再获取到producid
            PmsSpuValue pmsSpuValue = JSONObject.parseObject(spu, PmsSpuValue.class);
            pmsSpuValue.setProductId(pmsProduct.getId());
            spuValueList.add(pmsSpuValue); 
        }
        spuValueService.saveBatch(spuValueList);
        List<PmsSkuValue> skuValueList = new ArrayList<>();
        for(String sku : skus) {
            PmsSkuValue pmsSkuValue = JSONObject.parseObject(sku, PmsSkuValue.class);
            pmsSkuValue.setProductId(pmsProduct.getId());
            skuValueList.add(pmsSkuValue);
        }
        skuValueService.saveBatch(skuValueList);
        //库存
        List<PmsStock> stockList = new ArrayList<>();
        for(String stock : stocks) {
            PmsStock pmsStock = JSONObject.parseObject(stock, PmsStock.class);
            pmsStock.setProductId(pmsProduct.getId());
            stockList.add(pmsStock);
        }
        stockService.saveBatch(stockList);
        return ResultJson.success("11111111","传到后端成功！");
    }
}
