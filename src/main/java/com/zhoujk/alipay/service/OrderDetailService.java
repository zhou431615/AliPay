package com.zhoujk.alipay.service;

import com.zhoujk.alipay.entity.OrderDetail;

import java.util.List;

/**
 * @author : zhoujiankang
 * @Desc:
 * @since : 2022/5/4 9:49
 */

public interface OrderDetailService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderDetail queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<OrderDetail> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param kssOrderDetail 实例对象
     * @return 实例对象
     */
    int insert(OrderDetail kssOrderDetail);

    /**
     * 修改数据
     *
     * @param kssOrderDetail 实例对象
     * @return 实例对象
     */
    int update(OrderDetail kssOrderDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);


}
