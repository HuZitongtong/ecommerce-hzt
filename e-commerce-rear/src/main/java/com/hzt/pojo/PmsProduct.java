package com.hzt.pojo;

import java.math.BigDecimal;
import com.hzt.pojo.BasePojo;

import org.springframework.security.access.method.P;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author huzt
 * @since 2022-04-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PmsProduct extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品副标题
     */
    private String subTitle;

    /**
     * 标准价格
     */
    private BigDecimal price;

    /**
     * 类别id
     */
    private String categoryId;

    /**
     * 关键词
     */
    private String keywords;

    /**
     * 上架状态 默认没上架
     */
    private Integer publicStatus;

    /**
     * 热卖状态
     */
    private Integer hotStatus;

    /**
     * 新品状态
     */
    private Integer newStatus;

    /**
     * 商品图片
     */
    private String img;

    /**
     * 商品相册
     */
    private String pics;

    /**
     * 商品详情
     */
    private String detailHtml;

    /**
     * 是否有效
     */
    private Integer active;


}
