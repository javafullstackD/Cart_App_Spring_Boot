package com.example.demoapiclass.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    // specifying the relationship between the cart
    // and the item. ie one cart has many items
    @OneToMany(
            // CascadeType.ALL eg. If we delete the
            // card we also delete all the items
            // attached to the cart
            cascade = CascadeType.ALL
    )
    // specifying the join column name, this means we
    // will have the "cart_id" column in this table
    // this will tell us to which cart does the
    // specific item is assigned.
    @JoinColumn(name = "cart_id")
    private List<Item> items = new ArrayList<>();

}
