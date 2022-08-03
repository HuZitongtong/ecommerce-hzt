package com.hzt.pojo;

import com.hzt.pojo.BasePojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 图片表
 * </p>
 *
 * @author huzt
 * @since 2022-04-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Img extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 文件md5
     */
    private String md5;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件后缀名
     */
    private String suffix;

    /**
     * 文件路径
     */
    private String url;


}
