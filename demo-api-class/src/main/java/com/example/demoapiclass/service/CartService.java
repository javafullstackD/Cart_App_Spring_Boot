package com.example.demoapiclass.service;

import com.example.demoapiclass.model.Cart;
import com.example.demoapiclass.respository.CartRepository;
import com.example.demoapiclass.respository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

// mark it with @service annotation
@Service
public class CartService {
    // dependency injection
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    // constructor
    @Autowired
    public CartService(CartRepository cartRepository,
                       ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
    }
    // create methods for crud operations
    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }
    public List<Cart> getCarts() {
        return StreamSupport.stream(cartRepository.findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
    }
    public Cart getCart (Long id) {
        return null;
    }
    public Cart deleteCart (Long id) {
        // check if the cart exists
        Cart cart = getCart(id);
        cartRepository.delete(cart);
        return cart;
    }
}

