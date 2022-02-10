package org.lucasnscr.shopapi.controller;

import lombok.RequiredArgsConstructor;
import org.lucasnscr.shopapi.dto.ShopDTO;
import org.lucasnscr.shopapi.events.SendKafkaMessage;
import org.lucasnscr.shopapi.model.Shop;
import org.lucasnscr.shopapi.model.ShopItem;
import org.lucasnscr.shopapi.repository.ShopRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopRepository shopRepository;
    private final SendKafkaMessage sendKafkaMessage;

    @GetMapping
    public List<ShopDTO> getShop() {
        return shopRepository.findAll()
        		.stream()
                .map(shop -> ShopDTO.convert(shop))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ShopDTO saveShop(@RequestBody ShopDTO shopDTO) {    	
    	shopDTO.setIdentifier(UUID.randomUUID().toString());
    	shopDTO.setDateShop(LocalDate.now());
    	shopDTO.setStatus("PENDING");
    	
    	Shop shop = Shop.convert(shopDTO);
    	for (ShopItem shopItem : shop.getItems()) {
    		shopItem.setShop(shop);
    	}
    	
        shopDTO = ShopDTO.convert(shopRepository.save(shop));
        sendKafkaMessage.sendMessage(shopDTO);
        return shopDTO;
        
    }

}
