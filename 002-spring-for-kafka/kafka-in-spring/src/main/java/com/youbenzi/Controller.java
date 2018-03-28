package com.youbenzi;

import java.util.logging.Logger;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	public static Logger logger = Logger.getLogger(Controller.class.getName()); 
	
	@Autowired
	private KafkaTemplate<String, String> template;

	@RequestMapping("/send")
	public String send(String topic, String key, String data) {
		template.send(topic, key, data);
		return "success";
	}

	@KafkaListener(id = "t1", topics = "t1")
	public void listenT1(ConsumerRecord<?, ?> cr) throws Exception {
		logger.info(cr.toString());
	}
	
	@KafkaListener(id = "t2", topics = "t2")
	public void listenT2(ConsumerRecord<?, ?> cr) throws Exception {
		logger.info(cr.toString());
	}
}