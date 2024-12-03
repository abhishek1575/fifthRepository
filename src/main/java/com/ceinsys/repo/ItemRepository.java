package com.ceinsys.repo;

import com.ceinsys.dto.ItemDto;
import com.ceinsys.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
        List<Item> findAll();

        @Query(value="SELECT * FROM item WHERE category='asset' ",nativeQuery=true)
        List<ItemDto> findAllAsset();

        @Query(value= "SELECT * FROM item WHERE category='component' ",nativeQuery=true)
        List<ItemDto> findAllComponent();

        @Query(value="SELECT * FROM item WHERE sap_no = :sap_no", nativeQuery=true)
        Optional<Item> findBySap_no(Long sap_no);
}
