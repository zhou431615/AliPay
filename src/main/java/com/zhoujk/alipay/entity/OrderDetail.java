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
public class OrderDetail implements Serializable
{
    private static final long serialVersionUID = 594760442926690641L;

    @TableId
    private Long id;

    private String courseid;

    private String coursetitle;

    private String courseimg;

    private String userid;

    private String ordernumber;

    private String tradeno;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String username;

    private String price;

    private String paymethod;

}
