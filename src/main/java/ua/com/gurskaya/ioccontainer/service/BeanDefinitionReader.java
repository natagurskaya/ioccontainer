package ua.com.gurskaya.ioccontainer.service;

import ua.com.gurskaya.ioccontainer.entity.BeanDefinition;

import java.util.List;

public interface BeanDefinitionReader {
    List<BeanDefinition> readBeanDefinition();
}
