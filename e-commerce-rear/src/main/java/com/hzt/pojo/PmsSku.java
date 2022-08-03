package com.hzt.pojo;

import com.hzt.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品参数列表
 * </p>
 *
 * @author huzt
 * @since 2022-04-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PmsSku extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 关联的商品分类
     */
    private Long categoryId;

    /**
     * 商品参数名称
     */
    private String name;

    /**
     * 参数列表
     */
    private String inputList;

    /**
     * 是否允许动态手动添加参数 1->允许 0->不允许
     */
    private Integer addType;


}
