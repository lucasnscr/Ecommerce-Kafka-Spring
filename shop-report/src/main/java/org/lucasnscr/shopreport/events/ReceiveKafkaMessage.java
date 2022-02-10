package org.lucasnscr.shopreport.events;

import javax.transaction.Transactional;

import org.lucasnscr.shopreport.dto.ShopDTO;
import org.lucasnscr.shopreport.repository.ReportRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveKafkaMessage {

	private static final String SHOP_TOPIC_EVENT_NAME 
		= "SHOP_TOPIC_EVENT";
	
	private final ReportRepository reportRepository;
	
		@Transactional
		@KafkaListener(
				topics = SHOP_TOPIC_EVENT_NAME, 
				groupId = "group_report")
		public void listenShopTopic(ShopDTO shopDTO) {
			try {
		    log.info("Compra recebida no tópico: {}.",
		    		shopDTO.getIdentifier());	    
		    reportRepository
		    	.incrementShopStatus(shopDTO.getStatus());
			} catch (Exception e) {
				log.error("Erro no processamento da mensagem", e);
			}
		}

}
