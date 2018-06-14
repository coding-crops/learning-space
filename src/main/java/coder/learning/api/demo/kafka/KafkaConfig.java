package coder.learning.api.demo.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Listener;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
@EnableKafka
public class KafkaConfig {
    
    @Bean(name = "kafkaProperty")
    @ConfigurationProperties(prefix = "kafka.producer")
    public KafkaProperty kafkaProperty() {
        return new KafkaProperty();
    }

    @Bean(name = "consumerProperty")
    @ConfigurationProperties(prefix = "kafka.consumer")
    public ConsumerProperty consumerProperty() {
        return new ConsumerProperty();
    }

    @Bean(name = "producerConfig")
    public Map<String, Object> producerConfigs(@Qualifier("kafkaProperty") KafkaProperty kafkaProperty) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperty.getBootstrapServers());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }
    
    @Bean(name = "producerFactory")
    public ProducerFactory<String, String> producerFactory(@Qualifier("producerConfig") Map<String, Object> producerConfig) {
    //    System.out.println("=============="+new DefaultKafkaProducerFactory<>(producerConfig).createProducer());
        return new DefaultKafkaProducerFactory<>(producerConfig);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(@Qualifier("producerFactory") ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }



    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory(@Qualifier("concurrency") ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setPollTimeout(6000);
        factory.setConcurrency(10);

        return factory;
    }
    @Bean("concurrency")
    public ConsumerFactory<String, String> consumerFactory(@Qualifier("consumer") Map<String, Object> consumer) {
        return new DefaultKafkaConsumerFactory<>(consumer);
    }

    @Bean("consumer")
    public Map<String, Object> consumerConfigs(@Qualifier("consumerProperty") ConsumerProperty consumerProperty) {
        Map<String, Object> propsMap = new HashMap<>();
        System.out.println("------------" + consumerProperty.getGroupId());

        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerProperty.getBootstrapServers());
        // propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, consumerProperty.getAutoCommitInterval());
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, consumerProperty.getGroupId());
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return propsMap;
    }

    @Bean
    public Listener listener() {
        return new Listener();
    }


}
