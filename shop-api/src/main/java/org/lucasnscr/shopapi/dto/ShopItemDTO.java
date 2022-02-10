package org.lucasnscr.shopapi.dto;

import lombok.Getter;
import lombok.Setter;
import org.lucasnscr.shopapi.model.ShopItem;

@Getter
@Setter
public class ShopItemDTO {
	
	private String productIdentifier;
    private Integer amount;
    private Float price;

    public static ShopItemDTO convert(ShopItem shopItem) {
    	ShopItemDTO shopItemDTO = new ShopItemDTO();
    	shopItemDTO.setProductIdentifier(shopItem.getProductIdentifier());
    	shopItemDTO.setAmount(shopItem.getAmount());
    	shopItemDTO.setPrice(shopItem.getPrice());
    	return shopItemDTO;
    }

}
