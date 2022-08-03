package com.hzt.pojo;

import com.hzt.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author huzt
 * @since 2022-04-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SmsHomeRecommendSubject extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 专题名称
     */
    private String subjectName;

    /**
     * 推荐状态：0->不推荐;1->推荐
     */
    private Integer recomendStatus;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否有效
     */
    private Integer active;

}
