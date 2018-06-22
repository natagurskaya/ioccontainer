package ua.com.gurskaya.ioccontainer.service;

import ua.com.gurskaya.ioccontainer.entity.BeanDefinition;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class XMLBeanDefinitionReader implements BeanDefinitionReader {

    String[] path = {"src/main/resources/context.xml"};

    public XMLBeanDefinitionReader(String[] path) {
        this.path = path;
    }

    public List<BeanDefinition> readBeanDefinition() {

        List<BeanDefinition> beanDefinitions = new ArrayList<>();

        for (int i = 0; i < path.length; i++) {

            try (FileReader file = new FileReader(path[i]);
                 BufferedReader reader = new BufferedReader(file)) {
                String line = reader.readLine();
                line = reader.readLine();
                while (!line.equals("</beans>")) {
                    line = line.trim();
                    String[] splitLine = line.split(" ");
                    if (splitLine[0].equals("<bean")) {
                        BeanDefinition beanDefinition = new BeanDefinition();

                        String[] idElements = splitLine[1].split("=");
                        String idVal = idElements[1].substring(1, idElements[1].length() - 1);
                        beanDefinition.setId(idVal);

                        String[] classElements = splitLine[2].split("=");
                        String clazz = classElements[1].substring(1, classElements[1].length() - 2);
                        beanDefinition.setBeanClassName(clazz);

                        line = reader.readLine();
                        line = line.trim();
                        while (!line.equals("</bean>")) {
                            line = line.trim();
                            String[] splitLineDependencies = line.split(" ");
                            String[] dependencyValues = splitLineDependencies[2].split("=");
                            if (dependencyValues[0].equals("value")) {
                                Map<String, String> map = parsePropertiesValues(splitLineDependencies);
                                beanDefinition.setDependencies(map);
                                line = reader.readLine();
                                line = line.trim();
                            } else {
                                Map<String, String> map = parsePropertiesRefs(splitLineDependencies);
                                beanDefinition.setRefDependencies(map);
                                line = reader.readLine();
                                line = line.trim();
                            }
                        }
                        beanDefinitions.add(beanDefinition);
                    }
                    line = reader.readLine();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return beanDefinitions;
    }

    private Map<String, String> parsePropertiesValues(String[] splitDependencies) {
        Map<String, String> dependencies = new HashMap<>();

        String[] splitDependenciesName = splitDependencies[1].split("=");
        dependencies.put(splitDependenciesName[0], splitDependenciesName[1].substring(1, splitDependenciesName[1].length() - 1));

        String[] splitDependenciesValue = splitDependencies[2].split("=");
        dependencies.put(splitDependenciesValue[0], splitDependenciesValue[1].substring(1, splitDependenciesValue[1].length() - 3));

        return dependencies;
    }

    private Map<String, String> parsePropertiesRefs(String[] splitDependencies) {
        Map<String, String> dependencies = new HashMap<>();

        String[] splitDependenciesName = splitDependencies[1].split("=");
        dependencies.put(splitDependenciesName[0], splitDependenciesName[1].substring(1, splitDependenciesName[1].length() - 1));

        String[] splitDependenciesRef = splitDependencies[2].split("=");
        dependencies.put(splitDependenciesRef[0], splitDependenciesRef[1].substring(1, splitDependenciesRef[1].length() - 3));

        return dependencies;
    }
}
