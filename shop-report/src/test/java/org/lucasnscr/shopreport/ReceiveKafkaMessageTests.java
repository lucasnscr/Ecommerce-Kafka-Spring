package org.lucasnscr.shopreport;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lucasnscr.shopreport.dto.ShopDTO;
import org.lucasnscr.shopreport.events.ReceiveKafkaMessage;
import org.lucasnscr.shopreport.repository.ReportRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ReceiveKafkaMessageTests {

	@InjectMocks
	private ReceiveKafkaMessage receiveKafkaMessage;
	
	@Mock
	private ReportRepository reportRepository;
		
	public ShopDTO getShopDTO() {
		ShopDTO shopDTO = new ShopDTO();
		shopDTO.setStatus("SUCCESS");		
		return shopDTO;
	}
	

	@Test
	public void testProcessShopSuccess() {

		ShopDTO shopDTO = getShopDTO();
		
		receiveKafkaMessage.listenShopTopic(shopDTO);
		
		Mockito
			.verify(reportRepository, Mockito.times(1))
			.incrementShopStatus(shopDTO.getStatus());
				
	}
}
