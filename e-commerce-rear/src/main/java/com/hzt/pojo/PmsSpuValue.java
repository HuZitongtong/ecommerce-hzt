package com.hzt.pojo;

import com.hzt.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品的spu值表
 * </p>
 *
 * @author huzt
 * @since 2022-05-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PmsSpuValue extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 关联的商品id
     */
    private Long productId;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 属性值
     */
    private String value;


}
