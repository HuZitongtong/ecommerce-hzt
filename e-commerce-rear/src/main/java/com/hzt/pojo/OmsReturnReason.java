package com.hzt.pojo;

import com.hzt.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 退货原因表
 * </p>
 *
 * @author huzt
 * @since 2022-05-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OmsReturnReason extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 原因类型
     */
    private String type;

    /**
     * 是否可用 0->不可 1->可
     */
    private Integer active;


}
