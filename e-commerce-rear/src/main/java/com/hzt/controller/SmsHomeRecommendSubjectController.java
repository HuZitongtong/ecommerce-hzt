package com.hzt.controller;


import javax.annotation.Resource;

import com.hzt.pojo.SmsHomeRecommendSubject;
import com.hzt.service.ISmsHomeRecommendSubjectService;
import com.hzt.util.ResultJson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huzt
 * @since 2022-04-25
 */
@RestController
@RequestMapping("/sms-home-recommend-subject")
public class SmsHomeRecommendSubjectController {
    @Resource 
    ISmsHomeRecommendSubjectService homeRecommendSubjectService;
    @GetMapping("/list")
    ResultJson list() {
        return ResultJson.success(homeRecommendSubjectService.list());
    }
    @PostMapping("/del")
    ResultJson del(SmsHomeRecommendSubject smsHomeRecommendSubject) {
        String message = smsHomeRecommendSubject.getActive() == 0 ? "删除专题成功" : "恢复专题成功";
        return ResultJson.success(homeRecommendSubjectService.updateById(smsHomeRecommendSubject),message);
    }
    @PostMapping("/update")
    ResultJson update(SmsHomeRecommendSubject smsHomeRecommendSubject) {
        String message = smsHomeRecommendSubject.getRecomendStatus() == 0 ? "取消推荐成功" :"推荐成功";
        return ResultJson.success(homeRecommendSubjectService.updateById(smsHomeRecommendSubject),message);
    }
}
