package com.hzt.pojo;

import com.hzt.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品规格列表
 * </p>
 *
 * @author huzt
 * @since 2022-04-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PmsSpu extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 关联的商品分类
     */
    private Long categoryId;

    /**
     * 规格名
     */
    private String name;


}
