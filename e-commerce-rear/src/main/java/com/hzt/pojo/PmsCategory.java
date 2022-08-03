package com.hzt.pojo;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hzt.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 服装分类表
 * </p>
 *
 * @author huzt
 * @since 2022-04-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PmsCategory extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 上级id；顶级id为0
     */
    private Long parentId;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 是否有效
     */
    private Integer active;
    /**
     * 子分类
     */
    @TableField(exist = false)
    private List<PmsCategory> children;

}
