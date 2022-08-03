package com.hzt.pojo;

import com.hzt.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author huzt
 * @since 2022-04-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UmsResource extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源类别 0->前端 1->后端
     */
    private Integer type;

    /**
     * 前端url
     */
    private String frontUrl;

    /**
     * 后端url
     */
    private String backUrl;

    /**
     * 父资源id 顶级为0
     */
    private Long parentId;

    /**
     * 0->无下级 1->有下级
     */
    private Integer haschildren;


}
