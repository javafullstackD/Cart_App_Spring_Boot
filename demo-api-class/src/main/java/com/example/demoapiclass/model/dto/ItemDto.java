package com.example.demoapiclass.model.dto;

import com.example.demoapiclass.model.Item;
import lombok.Data;

import java.util.Objects;

@Data
public class ItemDto {
    private Long id;
    private String serialNumber;
    private PlainCartDto plainCartDto;

    // its static because we are not creating an object to use it
    // translating Item into ItemDto Object
    public static ItemDto from(Item item){
        // using the default constructor to create an empty object
        // the default constructor is coming from the @Data annotation
        // (lombok)
        ItemDto itemDto = new ItemDto();
        // setting the itemDTo id field
        itemDto.setId(item.getId());
        // setting the itemDTo getSerialNumber field
        itemDto.setSerialNumber(item.getSerialNumber());
        // setting the itemDTo plainCartDto field
        if(Objects.nonNull(item.getCart())){
            itemDto.setPlainCartDto(PlainCartDto.from(item.getCart()));
        }
        return itemDto;
        // 1. transform the item to itemDTo
        // 2. we also need to transform itemDto to item (
        // in the item entity)

    }
}
