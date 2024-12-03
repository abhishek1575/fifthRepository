package com.ceinsys.service;

import com.ceinsys.model.Item;
import com.ceinsys.model.ItemRequest;
import com.ceinsys.repo.ItemRepository;
import com.ceinsys.repo.RequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ItemRepository itemRepository;

    public String  requestItem(Long id, int quantityRequested){
        Optional<Item> optionalItem = itemRepository.findById(id);
        if(optionalItem.isEmpty()){
            return "Item Not Found";
        }
        Item item = optionalItem.get();
        if(item.getStock()< quantityRequested){
            return "Requested Quantity is exceeds Available Quantity";
        }

            ItemRequest itemRequest = new ItemRequest();
            itemRequest.setItem(item);
            itemRequest.setQuantityRequested(quantityRequested);

            itemRequest.setApproved(false);

            requestRepository.save(itemRequest);
            return "Request sent to the admin";
    }

    @Transactional
    public String approveRequest(Long requestId){
        Optional<ItemRequest> itemRequest = requestRepository.findById(requestId);
        if(itemRequest.isEmpty()){
            return "Request Not Found";
        }

        ItemRequest request = itemRequest.get();
        if(request.isApproved()){
            return "Request is Already Approved";
        }

        Item item = request.getItem();
        item.setStock(item.getStock()- request.getQuantityRequested());
        request.setApproved(true);
        itemRepository.save(item);
        requestRepository.save(request);

        return "Request Approved and Stock Updated";
    }
}
