package com.hzt.pojo;

import com.hzt.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 地址表
 * </p>
 *
 * @author huzt
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OmsAddress extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 地址名称
     */
    private String addressName;

    /**
     * 是否默认发货地址
     */
    private Integer sendStatus;

    /**
     * 是否默认收货地址
     */
    private Integer receiveStatus;

    /**
     * 收货人姓名
     */
    private String name;

    /**
     * 收货人电话
     */
    private String phone;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String region;

    /**
     * 详细地址
     */
    private String detailAddress;


}
