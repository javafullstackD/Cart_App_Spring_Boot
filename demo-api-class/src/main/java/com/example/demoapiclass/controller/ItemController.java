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
// art or endpoints
// if using localhost : localhost:PORT/items
// localhost:8081/items
@RequestMapping("/items")

public class ItemController {
    // dependency injection
    // able to use the methods in the item service
    private final ItemService itemService;
    // constructor
    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // we want to use the post request to create new items
    @PostMapping

    // the ItemDto is the object that we want to create
    // accepts 2 arguments @@RequestBody and the itemDto object
    // ResponseEntity is a springboot return type. Accepts a generic type. in this case we are passing
    // the ItemDto
    // we are returning the ItemDto to the client because we might not need to return sensitive information
    // e.g passwords
    // addItem is the method in our item controller
    // itemDto is coming from the client
    public ResponseEntity<ItemDto> addItem(@RequestBody final ItemDto itemDto){
        // there are two things happening on this line
        // "Item item = itemService.addItem(Item.from(itemDto))"
        // 1. converting itemDto from the client using the from method in the item class
        //  - from method in item class accepts itemDto as an argument
        // 2 we are getting an item from Item.from(itemDto) and we pass it
        // as an argument to the addItem method
        // in itemService that persist the item into the database
        Item item = itemService.addItem(Item.from(itemDto));
        // return the created object
        // the addItem method in the  itemService returns an item
        // we want to give the client the itemDto so we convert the item
        // to itemDto "ItemDto.from(item"
        return new ResponseEntity<>(ItemDto.from(item), HttpStatus.OK);
    }

    @GetMapping
    // Retrieve items
    // ResponseEntity is the return type
    // returning a list of  itemsDto
    public ResponseEntity<List<ItemDto>> getItems(){
        // items is a variable to store the list of items
        // itemService.getItems() is getting the list of items
        List<Item> items = itemService.getItems();
        // itemsDto is a variable to store the list of
        // itemsDtos
        // we want to convert each item in the list (items)
        // to itemsDto collection
        // thats why we create a list that will store a
        // collection of itemsDtos
        // Iterate over the items collection for each element in
        // the collection, we would like to call the from
        // method from the ItemDto class
        // double colon syntax is called the range operator
        // .collect(Collectors.toList() converts the stream to list
        List<ItemDto> itemsDto =
                items.stream().map(ItemDto::from)
                        .collect(Collectors.toList());
        // return to the client
        return new ResponseEntity<>(itemsDto, HttpStatus.OK);
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<ItemDto>
    getItem(@PathVariable final Long id){
        Item item = itemService.getItem(id);
        return new ResponseEntity<>(ItemDto.from(item),
                HttpStatus.OK);
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<ItemDto> deleteItem(@PathVariable final Long id){
        Item item = itemService.deleteItem(id);
        return new ResponseEntity<>(ItemDto.from(item), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<ItemDto> editItem(@PathVariable final Long id,
                                            @RequestBody final ItemDto itemDto){
        Item editedItem = itemService.editItem(id, Item.from(itemDto));
        return new ResponseEntity<>(ItemDto.from(editedItem), HttpStatus.OK);
    }

}
