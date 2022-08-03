package com.hzt.pojo;

import java.math.BigDecimal;
import com.hzt.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订单中的商品表
 * </p>
 *
 * @author huzt
 * @since 2022-05-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OmsProduct extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品价格
     */
    private BigDecimal productPrice;

    /**
     * 购买数量
     */
    private Integer productNum;

    /**
     * 商品的sku编号
     */
    private Long productSkuId;

    /**
     * 商品的销售属性 [{key : "颜色",value : "红色"},{}]
     */
    private String productAttr;

    /**
     * 商品图片
     */
    private String img;


}
