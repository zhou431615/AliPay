package com.zhoujk.alipay.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author : zhoujiankang
 * @Desc:
 * @since : 2022/5/5 13:32
 */

@Data
public class OrderTrade
{
    private static final long serialVersionUID = 594760442926690641L;

    @TableId(type = IdType.ASSIGN_ID) private Long id;

    private String outTradeNO;

    private String tradeNo;

    private Long totalAmount;
    @TableField(fill = FieldFill.INSERT) private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE) private Date updateTime;


}
