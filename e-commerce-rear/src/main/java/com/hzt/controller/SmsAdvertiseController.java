package com.hzt.controller;


import java.io.IOException;

import javax.annotation.Resource;

import com.aliyuncs.ecs.model.v20140526.AttachKeyPairResponse.Result;
import com.hzt.pojo.SmsAdvertise;
import com.hzt.service.IImgService;
import com.hzt.service.ISmsAdvertiseService;
import com.hzt.util.ResultJson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 轮播广告表 前端控制器
 * </p>
 *
 * @author huzt
 * @since 2022-05-09
 */
@RestController
@RequestMapping("/sms-advertise")
public class SmsAdvertiseController {
    @Resource
    ISmsAdvertiseService advertiseService;
    @Resource
    IImgService imgService;
    @GetMapping("/list")
    ResultJson list(Integer pageNo,Integer pageSize,String name) {
        return ResultJson.success(advertiseService.page(pageNo,pageSize,name));
    }
    @PostMapping("/add")
    ResultJson add(SmsAdvertise smsAdvertise, MultipartFile file) throws IOException {
        smsAdvertise.setImg(imgService.upload(file));
        return ResultJson.success(advertiseService.save(smsAdvertise), "添加广告轮播成功！");
    }
    @GetMapping("/getone")
    ResultJson getone(Long id) {
        return ResultJson.success(advertiseService.getById(id));
    }
    @PostMapping("/update")
    ResultJson update(SmsAdvertise smsAdvertise,MultipartFile file) throws IOException {
        if (file != null && file.getSize()>0) {
            smsAdvertise.setImg(imgService.upload(file));
        }
        return ResultJson.success(advertiseService.updateById(smsAdvertise), "修改广告轮播成功！");
    }
    @PostMapping("/del")
    ResultJson del(Long id) {
        return ResultJson.success(advertiseService.removeById(id),"删除广告信息成功！");
    }
}
