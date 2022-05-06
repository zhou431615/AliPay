package com.zhoujk.alipay.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : zhoujiankang
 * @Desc:
 * @since : 2022/5/4 9:43
 */

@Data
public class Course implements Serializable
{
    private static final long serialVersionUID = 261036342156151394L;
    /**
     * 课程唯一id
     */
    @TableId
    private String courseid;
    /**
     * 课程标题
     */
    private String title;
    /**
     * 课程简短介绍
     */
    private String intro;
    /**
     * 课程封面地址
     */
    private String img;
    /**
     * 课程的活动价
     */
    private Double price;
    /**
     * 状态:已发布/未发布
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
