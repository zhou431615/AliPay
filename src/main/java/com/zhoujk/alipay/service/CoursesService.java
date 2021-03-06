package com.zhoujk.alipay.service;

import com.zhoujk.alipay.entity.Course;

import java.util.List;

/**
 * @author Zhoujiankang
 */
public interface CoursesService
{

    /**
     * 通过ID查询单条数据
     *
     * @param courseid 主键
     * @return 实例对象
     */
    Course queryById(String courseid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Course> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param course 实例对象
     * @return 实例对象
     */
    int insert(Course course);

    /**
     * 修改数据
     *
     * @param course 实例对象
     * @return 实例对象
     */
    int update(Course course);

    /**
     * 通过主键删除数据
     *
     * @param courseid 主键
     * @return 是否成功
     */
    boolean deleteById(String courseid);

}
