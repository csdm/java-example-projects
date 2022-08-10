package it.claudiodimauro.springkafkarestcontroller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import io.swagger.v3.oas.annotations.media.Schema;

public class MessageDTO {
	@NotEmpty
	@NotNull
	@Schema(description = "Message to send on Kafka topic.")
	private String message;
	
	@Nullable
	@Schema(description = "Sender identificator. Could be null.")
	private String senderId;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getSenderId() {
		return senderId;
	}
	
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	/**
	 * the toString retrurns just the message text
	 */
	@Override
	public String toString() {
		return message;
	}	
	
	
}