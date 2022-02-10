package org.lucasnscr.shopapi.events;

import org.lucasnscr.shopapi.dto.ShopDTO;
import org.lucasnscr.shopapi.model.Shop;
import org.lucasnscr.shopapi.repository.ShopRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveKafkaMessage {
	
	private final ShopRepository shopRepository;

	private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";
	
	@KafkaListener(topics = SHOP_TOPIC_EVENT_NAME, groupId = "group")
	public void listenShopEvents(ShopDTO shopDTO) {
		try {
		    log.info("Status da compra recebida no tópico: {}.", shopDTO.getIdentifier());
		    
		    Shop shop = shopRepository.findByIdentifier(shopDTO.getIdentifier());
		    shop.setStatus(shopDTO.getStatus());
		    shopRepository.save(shop);
		} catch(Exception e) {
			log.error("Erro no processamento da compra {}", shopDTO.getIdentifier());
		}
	}

}
