package com.example.demoapiclass.model;

import com.example.demoapiclass.model.dto.ItemDto;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String serialNumber;
    @ManyToOne
    private Cart cart;

    public static Item from(ItemDto itemDto){
        Item item =new Item();
        item.setSerialNumber(itemDto.getSerialNumber());
        return item;
    }

}
