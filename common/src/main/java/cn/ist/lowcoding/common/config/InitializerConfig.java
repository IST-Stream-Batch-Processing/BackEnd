package cn.ist.lowcoding.common.config;

import cn.ist.lowcoding.common.initializer.Initializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 配置初始化类
 */
@Configuration
public class InitializerConfig {
    @Autowired
    ApplicationContext applicationContext;

    @Bean
    CommandLineRunner initializers() {
        return (args) -> {
            List<Initializer> toSort = new ArrayList<>(applicationContext.getBeansOfType(Initializer.class).values());
            toSort.sort(Comparator.comparing(Initializer::getOrder));
            for (Initializer initializer : toSort) {
                initializer.run();
            }
        };
    }
}
