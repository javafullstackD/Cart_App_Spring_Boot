package com.example.demoapiclass.controller;

import com.example.demoapiclass.model.Item;
import com.example.demoapiclass.model.dto.ItemDto;
import com.example.demoapiclass.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")

public class ItemController {
    private final ItemService itemService;
    @Autowired
    public ItemController(ItemService itemService){this.itemService=itemService;}
    @PostMapping
    public ResponseEntity<ItemDto> addItem(@RequestBody final ItemDto itemDto){
        Item item = itemService.addItem(Item.from(itemDto));

        return new ResponseEntity<>(ItemDto.from(item), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ItemDto>> getItems(){
        List<Item> items = itemService.getItems();
        List<ItemDto> itemsDto =
                items.stream().map(ItemDto::from)
                        .collect(Collectors.toList());
        return new ResponseEntity<>(itemsDto, HttpStatus.OK);
    }
    @GetMapping(value = {"id"})
    public ResponseEntity<ItemDto> getItem(@PathVariable final Long id){
        Item item = itemService.getItem(id);
        return new ResponseEntity<>(ItemDto.from(item),
                HttpStatus.OK);
    }



}
