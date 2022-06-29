package com.example.demoapiclass.service;

import com.example.demoapiclass.model.Item;
import com.example.demoapiclass.model.exception.ItemNotFoundException;
import com.example.demoapiclass.respository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

// mark it with @service annotation
@Service
public class ItemService {
    // dependency injection
    // create variable itemRepository of type ItemRepository
    // spring is injecting itemRepository into the Item Service
    // we will be able to use the itemRepository methods in our service
    // ItemRepository will create a bean for this service/class
    private final ItemRepository itemRepository;

    // constructor
    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    // create methods for CRUD operations
    // persist the item into the database and we return the persisted
    // object
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }
    // create a method that will return all items from the database
    public List<Item> getItems() {
        // the database returns an Iterable then we use the stream support
        return StreamSupport.stream
                        (itemRepository.findAll()
                                .spliterator(), false)
                .collect(Collectors.toList());
    }
    public Item getItem (Long id) {
        return itemRepository.findById(id).
                orElseThrow(() ->
                        new ItemNotFoundException(id));
    }
    public Item deleteItem (Long id) {
        // check if the item exists
        Item item = getItem(id);
        itemRepository.delete(item);
        return item;
    }
    @Transactional
    public Item editItem(Long id, Item item){
        Item itemToEdit = getItem(id);
        itemToEdit.setSerialNumber(item.getSerialNumber());
        // return will just persist
        return itemToEdit;
    }
}
