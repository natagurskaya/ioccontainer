package ua.com.gurskaya.ioccontainer.service;

import ua.com.gurskaya.ioccontainer.entity.Bean;
import ua.com.gurskaya.ioccontainer.entity.BeanDefinition;

import java.util.List;

public class ClassPathApplicationContext implements ApplicationContext {

    String[] path;
    BeanDefinitionReader reader;
    List<Bean> beans;
    List<BeanDefinition> beanDefinitions;

    public Object getBean(Class clazz) {
        return null;
    }

    public Object getBean(String name, Class clazz) {
        return null;
    }

    public Object getBean(String name) {
        return null;
    }

    public List<String> getBeanNames() {
        return null;
    }

    public void setBeanDefinitionReader(BeanDefinitionReader beanDefinitionReader) {

    }
}
