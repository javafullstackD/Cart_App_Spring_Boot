package com.example.demoapiclass.respository;

import com.example.demoapiclass.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
// crud repository is of generic type
// specify for which entity are we creating this repository for
// specify the type of the primary key
public interface ItemRepository extends CrudRepository<Item, Long> {
}

