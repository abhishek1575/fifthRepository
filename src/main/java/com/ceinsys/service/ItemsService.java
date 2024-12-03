package com.ceinsys.service;


import com.ceinsys.model.Item;
import com.ceinsys.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemsService {

    @Autowired
    private ItemRepository itemRepository;

   public boolean isPresent(Item item){
       Optional<Item> item1 = itemRepository.findBySap_no(item.getSap_no());
       if(item1.isPresent()){
           return true;
       }else return false;
   }
    public boolean CheckIsPresent(Long sap_no){
        Optional<Item> item1 = itemRepository.findBySap_no(sap_no);
        if(item1.isPresent()){
            return true;
        }else return false;
    }

    public boolean delete(Long id){
      Optional<Item> item =  itemRepository.findById(id);
      if(item.isPresent()){
          return true;
      } else return false;
    }


}
