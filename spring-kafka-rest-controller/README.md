# Spring-Kafka-Rest-Controller
## _Produce a message on Kafka topic using a Rest Controller as producer_

This project allows the user to understand how to produce a message on a Kafka topic using a Rest Controller.
This kind of message-producing could be useful when you need to test a Kafka listener and you don't have the possibility to produce a message directly on the topic.
The controller developed in this project could be seen as an interface beetween the user and the Kafka broker. 

This Spring Boot application inject in the context two configuration beans that initialize the producer with all needed properties.
`package it.claudiodimauro.springkafkarestcontroller.config.KafkaProducerConfig`

The message travel on a DTO that represents the JSON used in the request on the `api/producer/sendMessage` api:
````JSON
{
    "message": "string of the message",
    "senderId": "the sender's identificator"
}
````
The `message` field is a required string that represent the message to send on the topic; the `senderId` represents an identification code for the user who call the api. This field could be null or blank but in this case a default id will be assigned to the sender.

All the needed configuration are written in the `application.yml` and in this Development Environment overwriter, `application-dev.yml`.