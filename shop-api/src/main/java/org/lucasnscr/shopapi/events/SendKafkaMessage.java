package org.lucasnscr.shopapi.events;

import org.lucasnscr.shopapi.dto.ShopDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendKafkaMessage {
	
	private final KafkaTemplate<String, ShopDTO> kafkaTemplate;

	private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";

	public void sendMessage(ShopDTO msg) {
	    kafkaTemplate.send(SHOP_TOPIC_NAME, msg.getBuyerIdentifier(), msg);
	}
	
}
