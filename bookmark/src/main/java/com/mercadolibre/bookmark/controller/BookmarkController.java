package com.mercadolibre.bookmark.controller;

import com.mercadolibre.bookmark.dto.ItemStats;
import com.mercadolibre.bookmark.service.ItemStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("coupon")
public class BookmarkController {

    private final ItemStatsService itemStatsService;

    @GetMapping("stats")
    public List<ItemStats> getTopFiveItems() {
        return itemStatsService.getTopFiveItems();
    }
}