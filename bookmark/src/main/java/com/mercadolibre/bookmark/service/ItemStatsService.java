package com.mercadolibre.bookmark.service;

import com.mercadolibre.bookmark.dto.ItemStats;
import com.mercadolibre.bookmark.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemStatsService {

    private final ItemRepository itemRepository;

    public List<ItemStats> getTopFiveItems() {
        return itemRepository.findTopFiveUsedItems().stream()
                .map(objArray -> {
                    String id = (String) objArray[0];
                    long quantity = (long) objArray[1];
                    return ItemStats.builder()
                            .id(id)
                            .quantity(quantity)
                            .build();
                })
                .toList();
    }
}
