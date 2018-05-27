package coder.learning.api.demo.property;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by xin.liu on 2018/5/1.
 */
@Slf4j
@Configuration
public class ConfigurationDemo {

    @Bean(name = "readDemo")
    @ConfigurationProperties(prefix = "spring.redis")
    public PropertiesReader read() {
        return new PropertiesReader();
    }

    @Bean(name = "configDemo")
    public String config(@Qualifier("readDemo")PropertiesReader reader) {
        log.info("reader info is : {}",reader);
        // hehe h = new hehe();
        return reader.getHost();
    }
}
