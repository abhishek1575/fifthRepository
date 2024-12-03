package com.ceinsys.controller;

import com.ceinsys.dto.ItemDto;
import com.ceinsys.model.Item;
import com.ceinsys.repo.ItemRepository;
import com.ceinsys.service.ItemsService;
import com.ceinsys.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private RequestService requestService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllComponent(){
        try {
            List<Item> itemList=itemRepository.findAll();
            List<ItemDto> list=new ArrayList<>();

            for (Item item : itemList) {
                ItemDto itemDto=new ItemDto();
                itemDto.setId(item.getId());
                itemDto.setName(item.getName());
                itemDto.setCategory(item.getCategory());
                itemDto.setSubCategory(item.getSubCategory());
                itemDto.setValue(item.getValue());
                itemDto.setDescription(item.getDescription());
                itemDto.setManufacturer(item.getManufacturer());
                itemDto.setPackage_box(item.getPackage_box());
                itemDto.setMpn(item.getMpn());
                itemDto.setSap_no(item.getSap_no());
                itemDto.setLocation(item.getLocation());
                itemDto.setStock(item.getStock());
                itemDto.setStatus(item.isStatus());

                list.add(itemDto);
            }
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addItems(@RequestBody Item item){
        try {
            if (itemsService.isPresent(item)) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Item Already Exist");
            }else {
                itemRepository.save(item);
                return ResponseEntity.ok("Item Added Successfully");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected Error");
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<?> modifyUser(@RequestBody ItemDto itemDto){
        try {
            Optional<Item> presentItems=itemRepository.findById(itemDto.getId());
            if (presentItems.isPresent()) {
                Item item1=presentItems.get();
                item1.setName(itemDto.getName());
                item1.setCategory(itemDto.getCategory());
                item1.setSubCategory(itemDto.getSubCategory());
                item1.setDescription(itemDto.getDescription());
                item1.setValue(itemDto.getValue());
                item1.setManufacturer(itemDto.getManufacturer());
                item1.setPackage_box(itemDto.getPackage_box());
                item1.setMpn(itemDto.getMpn());
                item1.setSap_no(itemDto.getSap_no());
                item1.setLocation(itemDto.getLocation());
                item1.setStock(itemDto.getStock());

                itemRepository.save(item1);
            }
            return ResponseEntity.ok("Item Modify Successfully");
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected Body");
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteItem(@RequestParam Long ID){
        try {
            if (itemsService.delete(ID)) {
                itemRepository.deleteById(ID);
                return ResponseEntity.ok("Item Delete Successfully");
            }else {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Item Does Not Found");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected Error");
        }
    }

    @PostMapping("/request")
    public ResponseEntity<?> requestItem(@RequestParam Long id, @RequestParam int requestedQuantity){
        String response = requestService.requestItem(id, requestedQuantity);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/approved")
    public ResponseEntity<?> approvedRequest(@RequestParam Long requestId){
        String response = requestService.approveRequest(requestId);
        return ResponseEntity.ok(response);
    }
}
