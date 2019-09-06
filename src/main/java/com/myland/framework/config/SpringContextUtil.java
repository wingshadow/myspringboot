package com.myland.framework.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZHANGBIN
 * @version 1.0
 * @Description 获取容器中bean
 * @Date 2019/1/4 13:40
 **/
@Configuration
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static Object getBean(Class c) {
        return applicationContext.getBean(c);
    }

    /**
     * 根据bean ID和类型参数获取相应的Bean对象
     *
     * @param <T>   Bean类型泛型表示
     * @param name  Bean的ID
     * @param calzz Bean的类型
     * @return Spring Bean对象，获取不到对象则有可能会抛出异常
     */
    public static <T> T getBean(String name, Class<T> calzz) {
        return applicationContext.getBean(name, calzz);
    }
}
