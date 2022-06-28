package com.example.demoapiclass.respository;

import com.example.demoapiclass.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CartRepository extends CrudRepository<Cart, Long> {
}
