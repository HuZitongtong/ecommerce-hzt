package com.hzt.config;

import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.hzt.pojo.BasePojo;

public class Generator {
    public static void main(String[] args) {
        //构建自动生成对象
        AutoGenerator mpg = new AutoGenerator();
        //构建配置器
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor("huzt");
        //定义项目路径
        String path = System.getProperty("user.dir");
        gc.setOutputDir(path + "/src/main/java");
        mpg.setGlobalConfig(gc);
        //根据数据库表生成代码
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/shop?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);
        //包配置（什么东西生成到哪）
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.hzt");
        pc.setEntity("pojo");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");

        Map<String,String> pathInfo = new HashMap<String,String>();
        pathInfo.put("xml_path", path + "/src/main/resources/com/hzt/mapper");
        pathInfo.put("entity_path",path + "/src/main/java/com/hzt/pojo");
        pathInfo.put("mapper_path",path + "/src/main/java/com/hzt/mapper");
        pathInfo.put("service_path",path + "/src/main/java/com/hzt/service");
        pathInfo.put("service_impl_path",path + "/src/main/java/com/hzt/service/impl");
        pathInfo.put("controller_path",path + "/src/main/java/com/hzt/controller");
        pc.setPathInfo(pathInfo);
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(BasePojo.class);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类

        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        strategy.setInclude("oms_address");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        //生成代码
        mpg.execute();
    }
   
}
