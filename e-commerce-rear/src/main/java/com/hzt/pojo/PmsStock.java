package com.hzt.pojo;

import java.math.BigDecimal;
import com.hzt.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * sku库存表
 * </p>
 *
 * @author huzt
 * @since 2022-05-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PmsStock extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 关联的商品id
     */
    private Long productId;

    /**
     * 库存数
     */
    private Integer stock;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * sku列表
     */
    @TableField("skuList")
    private String skuList;


}
