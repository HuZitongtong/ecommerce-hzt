package com.hzt.controller;


import javax.annotation.Resource;

import com.hzt.pojo.OmsReturnReason;
import com.hzt.service.IOmsReturnReasonService;
import com.hzt.util.ResultJson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 退货原因表 前端控制器
 * </p>
 *
 * @author huzt
 * @since 2022-05-10
 */
@RestController
@RequestMapping("/oms-return-reason")
public class OmsReturnReasonController {
    @Resource
    IOmsReturnReasonService reasonService;
    @GetMapping("/list")
    ResultJson list() {
        return ResultJson.success(reasonService.list());
    }
    @GetMapping("/getone")
    ResultJson getone(Long id) {
        return ResultJson.success(reasonService.getById(id));
    }
    @PostMapping("/add")
    ResultJson add(OmsReturnReason omsReturnReason) {
        return ResultJson.success(reasonService.save(omsReturnReason),"添加退货原因成功！");
    }
    @PostMapping("/update")
    ResultJson update(OmsReturnReason omsReturnReason) {
        
        return ResultJson.success(reasonService.updateById(omsReturnReason),"修改成功");
    }
    @PostMapping("/del")
    ResultJson del(Long id) {
        return ResultJson.success(reasonService.removeById(id),"删除退货原因成功！");
    }
}
