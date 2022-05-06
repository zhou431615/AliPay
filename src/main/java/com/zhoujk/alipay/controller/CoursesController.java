package com.zhoujk.alipay.controller;

import com.zhoujk.alipay.entity.Course;
import com.zhoujk.alipay.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhoujiankang
 * @Desc:
 * @since : 2022/5/4 9:40
 */

@RestController
@RequestMapping("Courses")
public class CoursesController
{
    /**
     * 服务对象
     */
    @Autowired private CoursesService coursesService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Course selectOne(String id)
    {
        return this.coursesService.queryById(id);
    }

}
