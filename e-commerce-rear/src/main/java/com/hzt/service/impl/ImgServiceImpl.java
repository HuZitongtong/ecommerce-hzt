package com.hzt.service.impl;

import com.hzt.pojo.Img;
import com.hzt.mapper.ImgMapper;
import com.hzt.service.IImgService;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.omg.CORBA.portable.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 图片表 服务实现类
 * </p>
 *
 * @author huzt
 * @since 2022-04-28
 */
@Service
public class ImgServiceImpl extends ServiceImpl<ImgMapper, Img> implements IImgService {
    @Value("${aliyun-oss.endpoint}")
    String endpoint;
    @Value("${aliyun-oss.accessKeyId}")
    String accessKeyId;
    @Value("${aliyun-oss.accessKeySecret}")
    String accessKeySecret;
    @Value("${aliyun-oss.bucketName}")
    String bucketName;
    @Override
    public String upload(MultipartFile file) throws IOException {       
        //md5校验文件是否上传过
        //通过保存至数据库上传
      
        String md5 = DigestUtils.md5Hex(file.getInputStream());
        long size = file.getSize();
        String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
        
        QueryWrapper<Img> wrapper = new QueryWrapper<>();
        wrapper.eq("md5", md5)
                .eq("size", size)
                .eq("suffix", suffix);
        Img img = this.getOne(wrapper);//查单条
        if (img != null) {
            return img.getUrl();
        } else {
           
            OSS ossClient = null;
            try {
                //1.创建oss实例
                 ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
                 //2.获取文件流
                // InputStream inputStream = file.getInputStream();
                 //3.构建日期目录
                 SimpleDateFormat datefFormat = new SimpleDateFormat("yyyy-MM-dd");
                 
                 String datePath = datefFormat.format(new java.util.Date());
                 //4.获取文件名
                //  String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
                 StringBuilder builder = new StringBuilder();
                 LocalDateTime now = LocalDateTime.now();
                 builder.append(datePath + "/");
                 builder.append(now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
                 builder.append(RandomStringUtils.random(6,false,true));
                 builder.append(".").append(suffix);
                 String fileUrl = builder.toString();
                 //5. 文件上传至云服务器
                ossClient.putObject(bucketName, fileUrl, file.getInputStream());
                //System.out.println("https://" + bucketName + "." + endpoint + "/" + fileUrl);

                //img存入数据库
                img = new Img(md5, size, suffix, fileUrl);
                this.save(img);
                return fileUrl;
            }catch (Exception e) {
                 e.printStackTrace();
            } finally {
                 //关闭ossClient
                 ossClient.shutdown();
            }
            
        }
        return "fail";
    }

}
