package com.hzt.service;

import com.hzt.pojo.PmsCategory;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服装分类表 服务类
 * </p>
 *
 * @author huzt
 * @since 2022-04-25
 */
public interface IPmsCategoryService extends IService<PmsCategory> {
    List<PmsCategory> getByParentId(Long parentId);//通过数据库的数据构建树
    List<PmsCategory> getAll(Long parentId);
}
