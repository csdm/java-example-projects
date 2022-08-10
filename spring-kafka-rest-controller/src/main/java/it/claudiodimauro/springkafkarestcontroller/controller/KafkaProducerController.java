package it.claudiodimauro.springkafkarestcontroller.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import it.claudiodimauro.springkafkarestcontroller.dto.MessageDTO;

@RestController
@RequestMapping(value = "api/producer")
public class KafkaProducerController {
	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerController.class);
	
	private static final UUID defaultSenderId = UUID.randomUUID();
	
	@Autowired
	private KafkaTemplate<String, MessageDTO> kafkaTemplate;
	
	@Value(value = "${kafka.topic}")
	private String topicName;
	
	private String status = "";
	
	@PostMapping(value = "/sendMessage")
	@Operation(method = "POST", 
	summary = "Send a message on Kafka topic", 
	description = "The api send the specified message on the CDM-ENV-JAVA_EXAMPLE_PROJECT-SKRC-TOPIC topic")
	public ResponseEntity<String> sendMessage(@RequestBody MessageDTO message) {
		logger.info("Sending message: {}", message);
		
		//limiting the message length to the first 10 characters in order to avoid verbose logs
		String truncatedMessage = message.toString().length() >= 10 ? message.toString().substring(0, 10) + "..." : message.toString(); 
		
		if(message.getSenderId() == null) {
			logger.info("Setting up default senderId");
			message.setSenderId(defaultSenderId.toString() + "_sendMessage");
		}
		
		
		ListenableFuture<SendResult<String, MessageDTO>> future = kafkaTemplate.send(topicName, message);
		
		future.addCallback(new ListenableFutureCallback<SendResult<String, MessageDTO>>() {
			@Override
			public void onSuccess(SendResult<String, MessageDTO> result) {
				status = "Message sent succesfully";
				logger.info("Succesfully sent message = [{}], on topic = {}, with offset = {}", truncatedMessage, topicName, result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.info("Failed to send message = [{}], error = {}", truncatedMessage, ex.getMessage());
                status = "Message sending failed";				
			}
		});
		
		return ResponseEntity.ok(status);
	}
}
