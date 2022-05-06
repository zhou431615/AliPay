package com.zhoujk.alipay.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhoujk.alipay.entity.Course;
import com.zhoujk.alipay.mapper.CoursesMapper;
import com.zhoujk.alipay.service.CoursesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : zhoujiankang
 * @Desc:
 * @since : 2022/5/4 9:51
 */

@Service("CoursesService")
public class CoursesServiceImpl implements CoursesService
{
    @Resource private CoursesMapper coursesMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param courseid 主键
     * @return 实例对象
     */
    @Override
    public Course queryById(String courseid)
    {
        return this.coursesMapper.selectById(courseid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 当前页数
     * @param limit  每页记录数
     * @return 对象列表
     */
    @Override
    public List<Course> queryAllByLimit(int offset, int limit)
    {

        Page<Course> page = new Page<>(offset, limit);

        Page<Course> coursePage = coursesMapper.selectPage(page, null);

        return coursePage.getRecords();
    }

    /**
     * 新增数据
     *
     * @param course 实例对象
     * @return 影响行数
     */
    @Override
    public int insert(Course course)
    {
        return coursesMapper.insert(course);
    }

    /**
     * 修改数据
     *
     * @param course 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Course course)
    {
        return coursesMapper.updateById(course);

    }

    /**
     * 通过主键删除数据
     *
     * @param courseid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String courseid)
    {
        return this.coursesMapper.deleteById(courseid) > 0;
    }
}
