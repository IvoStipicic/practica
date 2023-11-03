package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.internals.Topic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {


    @Bean
    public NewTopic generateTopic(){
        Map<String, String> configuration = new HashMap<>();
        
        configuration.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE); //delete (borra mensaje), compact (mantiene el mensaje mas actual)
        configuration.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); //tiempo de retencion de mensaje, en defecto viene en -1 (significa que no se va a borrar nunca)
        configuration.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); //tamano maximo de cada segmento en bytes (en este caso es 1 giga)
        configuration.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012"); //Tamano maximo de un mensaje por defecto es un megabyte

        return TopicBuilder.name("topic")
                .partitions(2)
                .replicas(2)
                .configs(configuration)
                .build();
    }
}
