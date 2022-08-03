package com.hzt.pojo;

import com.hzt.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author huzt
 * @since 2022-04-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UmsRole extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名
     */
    private String name;

    /**
     * 是否有效
     */
    private Integer active;


}
