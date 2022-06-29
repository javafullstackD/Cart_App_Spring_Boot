package com.example.demoapiclass.respository;

import com.example.demoapiclass.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
// crud repository is of generic type
// specify for which entity are we creating this repository for
// specify the type of the primary key
public interface CartRepository extends CrudRepository<Cart, Long> {
}
