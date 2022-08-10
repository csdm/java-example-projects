package it.claudiodimauro.springkafkarestcontroller.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import it.claudiodimauro.springkafkarestcontroller.dto.MessageDTO;

@Configuration
public class KafkaProducerConfig {
	@Value(value = "${kafka.server.endpoint}")
	private String kafkaServerEndpoint = "127.0.0.1:9092";
	
	@Bean
	public ProducerFactory<String, MessageDTO> producerFactory() {
		Map<String, Object> configurationProperties = new HashMap<>();
		configurationProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerEndpoint);
		configurationProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configurationProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return new DefaultKafkaProducerFactory<>(configurationProperties);
	}
	
	@Bean
	public KafkaTemplate<String, MessageDTO> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
}