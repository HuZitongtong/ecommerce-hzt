package com.hzt.controller;


import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzt.pojo.UmsUser;
import com.hzt.service.IImgService;
import com.hzt.service.IUmsUserService;
import com.hzt.util.ResultJson;
import com.mysql.cj.xdevapi.Result;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import afu.org.checkerframework.checker.units.qual.A;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huzt
 * @since 2022-04-18
 */
@RestController
@RequestMapping("/ums_user")
public class UmsUserController {
    @Resource
    //密码加密工具类
    BCryptPasswordEncoder passwordEncoder;
    @Resource
    IUmsUserService userService;
    @Resource
    IImgService imgService;
    @GetMapping("/list")
    ResultJson list(Integer pageNo,Integer pageSize,String name) throws InterruptedException {
       // System.out.println("pageNo:"+pageNo+" pageSize:"+pageSize);
       //int i = 100/0;//模拟后端错误信息
       //TimeUnit.SECONDS.sleep(3);//模拟加载速度慢的情况
       IPage userIPage = new Page<>(pageNo,pageSize);
    //    System.out.println("总条数"+userIPage.getTotal());
    //     System.out.println("总页数"+userIPage.getPages());
        //return userService.page(new Page<>(pageNo,pageSize));
        return ResultJson.success(userService.page(pageNo, pageSize, name));
    }
    @PostMapping("/add")
   ResultJson add(UmsUser umsUser, MultipartFile file) throws IOException{
    // 构建 MinioClient对象
       umsUser.setPassword(passwordEncoder.encode(umsUser.getRawPassword()));

    //    String endpoint = "oss-cn-beijing.aliyuncs.com";
    //    String accessKeyId = "LTAI5t7Ntgkcv47td5WQgtEM";
    //    String accessKeySecret = "tgsW8n0ZUQeAOraSgFOdCH6MKQFYl8";
    //    String bucketName = "hzt-images";
    //    OSS ossClient = null;
    //    try {
    //        //1.创建oss实例
    //         ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    //         //2.获取文件流
    //         InputStream inputStream = file.getInputStream();
    //         //3.构建日期目录
    //         SimpleDateFormat datefFormat = new SimpleDateFormat("yyyy-MM-dd");
    //         String datePath = datefFormat.format(new Date());
    //         //4.获取文件名
    //         String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
    //         StringBuilder builder = new StringBuilder();
    //         LocalDateTime now = LocalDateTime.now();
    //         builder.append(datePath + "/");
    //         builder.append(now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
    //         builder.append(RandomStringUtils.random(6,false,true));
    //         builder.append(".").append(suffix);
    //         String fileUrl = builder.toString();
    //         //5. 文件上传至云服务器
    //        ossClient.putObject(bucketName, fileUrl, inputStream);
    //        System.out.println("https://" + bucketName + "." + endpoint + "/" + fileUrl);
    //    }catch (Exception e) {
    //         e.printStackTrace();
    //    } finally {
    //         //关闭ossClient
    //         ossClient.shutdown();
    //    }
    //    //图片上传至图片服务器
       //1.构建minioclient对象
       
    //    MinioClient minioClient = MinioClient.builder()
    //                                         .endpoint("http://172.22.44.39:9000/")
    //                                         .credentials("admin", "123456789").build();
    //     PutObjectArgs args = PutObjectArgs.builder()
    //                                     .object(file.getOriginalFilename())
    //                                     .bucket("images")
    //                                     .contentType(file.getContentType())
    //                                     .stream(file.getInputStream(), file.getSize(), -1)
    //                                     .build();
        //minioClient.putObject(args);
        umsUser.setIcon(imgService.upload(file));
      return ResultJson.success(userService.save(umsUser),"添加用户成功！");
    //    return ResultJson.success("11111111","添加用户成功！");
   
   }
   @GetMapping("/getone") 
   ResultJson getone(Long id) {
        return ResultJson.success(userService.getById(id));
   }
   @PostMapping("/update")
   ResultJson update(UmsUser umsUser, MultipartFile file) throws IOException {
       if (file != null && file.getSize()>0) {
           umsUser.setIcon(imgService.upload(file));
       }
       //通过id去修改用户信息
       return ResultJson.success(userService.updateById(umsUser), "修改用户成功！");
   }
   @PostMapping("/delete")
   ResultJson delete(UmsUser umsUser) {
    String message = umsUser.getActive() == 0 ? "删除用户成功" : "恢复用户成功";
    return ResultJson.success(userService.updateById(umsUser),message);
   }

   @PostMapping("/login")
   ResultJson login(String username,String password) throws Exception {
        String message = "用户" + username + "登陆成功";
        return ResultJson.success(userService.login(username, password),message);
   }
    
    
}
