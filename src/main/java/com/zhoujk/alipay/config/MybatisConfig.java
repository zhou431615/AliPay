package com.zhoujk.alipay.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * @author : zhoujiankang
 * @Desc: Mybatis-plus配置
 * @since : 2022/5/4 15:48
 */
@Slf4j
@Configuration
@MapperScan(basePackages = "com.zhoujk.alipay.mapper")
public class MybatisConfig
{
    /**
     * 乐观锁插件
     * 注意在Mybatis-plus3.4.0版本后，放弃此方法乐观锁处理器
     *
     * @Deprecated
     * @Bean public OptimisticLockerInterceptor optimisticLockerInterceptor()
     * {
     * return new OptimisticLockerInterceptor();
     * }
     */


    @Bean

    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor()
    {
        return new OptimisticLockerInnerInterceptor();
    }


    /**
     * 新版分页插件，注意选择数据库
     *
     * @return MybatisPlusInterceptor
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor()
    {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        log.info("Mybatis Plus 分页插件配置完成...");
        return interceptor;
    }

    /**
     * 自动填充配置
     *
     * @return MetaObjectHandler
     */
    @Bean
    public MetaObjectHandler metaObjectHandler()
    {
        return new MetaObjectHandler()
        {
            @Override
            public void insertFill(MetaObject metaObject)
            {
                log.info("start insert fill ....");
                this.fillStrategy(metaObject, "createTime", LocalDateTime.now());
                this.fillStrategy(metaObject, "updateTime", LocalDateTime.now());
            }

            @Override
            public void updateFill(MetaObject metaObject)
            {
                log.info("start update fill ....");
                this.fillStrategy(metaObject, "updateTime", LocalDateTime.now());
            }
        };
    }

}

