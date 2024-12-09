package com.wing.yjyw.entity.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName one_zero_three
 */
@Data
public class OneZeroThreeBo implements Serializable {
    private Integer pageNum;
    private Integer pageSize;
    /**
     * 温度
     */
    private String temp;

    /**
     * 湿度
     */
    private String humi;

    /**
     * 光照强度
     */
    private String light;

    /**
     * 土壤湿度
     */
    private String soil;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}