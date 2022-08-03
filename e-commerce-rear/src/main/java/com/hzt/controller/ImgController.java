package com.hzt.controller;


import java.io.IOException;

import javax.annotation.Resource;

import com.hzt.service.IImgService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 图片表 前端控制器
 * </p>
 *
 * @author huzt
 * @since 2022-04-28
 */
@RestController
@RequestMapping("/img")
public class ImgController {
    @Resource
    IImgService imgService;
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    String upload(MultipartFile file) throws IOException {
        return imgService.upload(file);
    }

}
