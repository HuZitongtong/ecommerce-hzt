package com.hzt.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.hzt.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 退货申请表
 * </p>
 *
 * @author huzt
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OmsReturnApply extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 地址表id
     */
    private Long addressId;

    /**
     * 退货商品id
     */
    private Long productId;

    /**
     * 订单编号
     */
    private Long orderSn;

    /**
     * 申请时间
     */
    private LocalDateTime submitTime;

    /**
     * 用户账号
     */
    @TableField("loginName")
    private String loginName;

    /**
     * 退款金额
     */
    private BigDecimal returnAmount;

    /**
     * 退款人姓名
     */
    private String returnName;

    /**
     * 退款人电话
     */
    private String returnPhone;

    /**
     * 申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝
     */
    private Integer status;

    /**
     * 退款原因类型
     */
    private String returnReason;

     /**
      * 退款原因描述
      */
    private String reasonDetail;

}
