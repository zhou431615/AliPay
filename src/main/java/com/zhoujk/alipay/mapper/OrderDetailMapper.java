package com.zhoujk.alipay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhoujk.alipay.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : zhoujiankang
 * @Desc:
 * @since : 2022/5/4 9:43
 */

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail>
{

}
