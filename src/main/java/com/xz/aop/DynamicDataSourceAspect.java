package com.xz.aop;

import com.xz.anno.DS;
import com.xz.config.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author xz
 * @date 2020/4/24 15:58
 **/
@Slf4j
@Aspect
@Order(-10)//保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {

    @Before("@annotation(targetDataSource))")
    public void changeDataSource(JoinPoint point, DS targetDataSource) throws Throwable {
        //获取当前的指定的数据源;
        String dsId = targetDataSource.value();
        log.info("dsId=========:{}",dsId);
        //如果不在我们注入的所有的数据源范围之内，那么输出警告信息，系统自动使用默认的数据源。
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            log.info("数据源[{}]不存在，使用默认数据源 > {}"+targetDataSource.value()+point.getSignature());
        } else {
            log.info("Use DataSource : {} > {}"+targetDataSource.value()+point.getSignature());
            //找到的话，那么设置到动态数据源上下文中。
            DynamicDataSourceContextHolder.setDataSourceRouterKey(targetDataSource.value());
        }
    }

    @After("@annotation(targetDataSource)")
    public void restoreDataSource(JoinPoint point, DS targetDataSource) {
        log.info("Revert DataSource : {} > {}"+targetDataSource.value()+point.getSignature());
        //方法执行完毕之后，销毁当前数据源信息，进行垃圾回收。
        DynamicDataSourceContextHolder.removeDataSourceRouterKey();
    }
}
