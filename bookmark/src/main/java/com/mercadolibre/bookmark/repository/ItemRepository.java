package com.mercadolibre.bookmark.repository;

import com.mercadolibre.bookmark.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT i.mId AS id, COUNT(ui.item_id) AS quantity " +
            "FROM Item i " +
            "JOIN user_item ui ON i.id = ui.item_id " +
            "GROUP BY i.mId " +
            "ORDER BY quantity DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> findTopFiveUsedItems();
}
