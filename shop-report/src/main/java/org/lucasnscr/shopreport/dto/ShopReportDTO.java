package org.lucasnscr.shopreport.dto;

import lombok.Getter;
import lombok.Setter;
import org.lucasnscr.shopreport.model.ShopReport;

@Getter
@Setter
public class ShopReportDTO {
	    
    private String identifier;
    
    private Integer amount;
	
    public static ShopReportDTO convert(ShopReport shopReport) {
    	ShopReportDTO shopDTO = new ShopReportDTO();
    	shopDTO.setIdentifier(shopReport.getIdentifier());
    	shopDTO.setAmount(shopReport.getAmount());
    	return shopDTO;
    }

}
