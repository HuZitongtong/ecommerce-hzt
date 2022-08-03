package com.hzt.service;

import com.hzt.pojo.Img;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 图片表 服务类
 * </p>
 *
 * @author huzt
 * @since 2022-04-28
 */
public interface IImgService extends IService<Img> {
    String upload(MultipartFile file) throws IOException;
}
