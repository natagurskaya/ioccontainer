package ua.com.gurskaya.ioccontainer.service;

import java.util.List;

public interface ApplicationContext<T> {
    T getBean(Class<T> clazz);

    T getBean(String name, Class<T> clazz);

    Object getBean(String name);

    List<String> getBeanNames();

    void setBeanDefinitionReader(BeanDefinitionReader beanDefinitionReader);
}
