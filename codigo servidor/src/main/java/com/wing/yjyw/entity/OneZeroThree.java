package com.wing.yjyw.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName one_zero_three
 */
@TableName(value ="one_zero_three")
@Data
public class OneZeroThree implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 温度
     */
    @TableField(value = "temp")
    private String temp;

    /**
     * 湿度
     */
    @TableField(value = "humi")
    private String humi;

    /**
     * 光照强度
     */
    @TableField(value = "light")
    private String light;

    /**
     * 土壤湿度
     */
    @TableField(value = "soil")
    private String soil;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}