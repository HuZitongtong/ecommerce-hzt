package com.hzt.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.hzt.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author huzt
 * @since 2022-05-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OmsOrder extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 订单提交时间
     */
    private LocalDateTime createTime;

    /**
     * 用户名
     */
    private String loginName;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 应付金额/实际付款金额
     */
    private BigDecimal payAmount;

    /**
     * 运费
     */
    private BigDecimal freightAmount;

    /**
     * 优惠卷抵扣金额
     */
    private BigDecimal couponAmount;

    /**
     * 管理员后台调整订单 使用的折扣金额
     */
    private BigDecimal discountAmount;

    /**
     * 支付方式 0->未支付 1->支付宝 2->微信
     */
    private Integer payType;

    /**
     * 订单来源 0->pc订单 1->app订单
     */
    private Integer sourceType;

    /**
     * 订单状态 0->待付款 1->待发货 2->已发货 3->已完成 4->已关闭 5->无效订单
     */
    private Integer status;

    /**
     * 物流公司
     */
    private String deliveryCompany;

    /**
     * 物流单号
     */
    private String deliverySn;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 收货人邮编
     */
    private String receiverPostCode;

    /**
     * 收货人省
     */
    private String receiverProvince;

    /**
     * 市
     */
    private String receiverCity;

    /**
     * 区
     */
    private String receiverRegion;

    /**
     * 详细地址
     */
    private String receiverDetailAddress;

    /**
     * 订单备注
     */
    private String note;

    /**
     * 确认收货状态 0->未确认 1->已确认
     */
    private Integer confirmStatus;

    /**
     * 是否删除订单 0->删除订单 1->未删除订单
     */
    private Integer active;


}
