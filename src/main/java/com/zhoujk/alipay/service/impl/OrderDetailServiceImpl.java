package com.zhoujk.alipay.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhoujk.alipay.entity.OrderDetail;
import com.zhoujk.alipay.mapper.OrderDetailMapper;
import com.zhoujk.alipay.service.OrderDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : zhoujiankang
 * @Desc:
 * @since : 2022/5/4 9:51
 */

@Service("OrderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService
{
    @Resource private OrderDetailMapper orderDetailMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OrderDetail queryById(Long id)
    {
        return this.orderDetailMapper.selectById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<OrderDetail> queryAllByLimit(int offset, int limit)
    {

        Page<OrderDetail> page = new Page<>(offset, limit);

        Page<OrderDetail> selectPage = orderDetailMapper.selectPage(page, null);

        return selectPage.getRecords();
    }


    /**
     * 新增数据
     *
     * @param orderDetail 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(OrderDetail orderDetail)
    {
        return orderDetailMapper.insert(orderDetail);
    }

    /**
     * 修改数据
     *
     * @param orderDetail 实例对象
     * @return 实例对象
     */
    @Override
    public int update(OrderDetail orderDetail)
    {
        return orderDetailMapper.updateById(orderDetail);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id)
    {
        return this.orderDetailMapper.deleteById(id) > 0;
    }
}
