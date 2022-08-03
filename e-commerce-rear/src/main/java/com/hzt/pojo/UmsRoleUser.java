package com.hzt.pojo;

import com.hzt.pojo.BasePojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 角色与用户关联表
 * </p>
 *
 * @author huzt
 * @since 2022-04-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UmsRoleUser extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 用户id
     */
    private Long userId;


}
