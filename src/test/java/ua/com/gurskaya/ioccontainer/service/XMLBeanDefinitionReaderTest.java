package ua.com.gurskaya.ioccontainer.service;

import ua.com.gurskaya.ioccontainer.entity.BeanDefinition;

import java.util.List;

import static org.junit.Assert.*;

public class XMLBeanDefinitionReaderTest {
    @org.junit.Test
    public void testReadBeanDefinition() throws Exception {

        //prepare
        String[] path = {"src/main/resources/context.xml"};
        BeanDefinitionReader beanDefinitionReader = new XMLBeanDefinitionReader(path);

        //when
        List<BeanDefinition> beanDefinitions = beanDefinitionReader.readBeanDefinition();

        //then
        assertEquals(3, beanDefinitions.size());
    }

}