package org.lucasnscr.shopreport.controller;

import lombok.RequiredArgsConstructor;
import org.lucasnscr.shopreport.dto.ShopReportDTO;
import org.lucasnscr.shopreport.repository.ReportRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shop_report")
@RequiredArgsConstructor
public class ShopController {

    private final ReportRepository reportRepository;

    @GetMapping
    public List<ShopReportDTO> getShop() {
        return reportRepository.findAll()
        		.stream()
                .map(shop -> ShopReportDTO.convert(shop))
                .collect(Collectors.toList());
    }

}
