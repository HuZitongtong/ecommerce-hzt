package com.hzt.pojo;

import com.hzt.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 轮播广告表
 * </p>
 *
 * @author huzt
 * @since 2022-05-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SmsAdvertise extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 轮播位置 0->pc首页轮播 1->app首页轮播
     */
    private Integer type;

    /**
     * 图片
     */
    private String img;

    /**
     * 上下线状态 0->下线 1->上线
     */
    private Integer status;

    /**
     * 点击数
     */
    private Integer clickCount;

    /**
     * 下单数
     */
    private Integer orderCount;

    /**
     * 关联链接地址
     */
    private String url;

    /**
     * 备注
     */
    private String note;

    /**
     * 排序
     */
    private Integer sort;


}
