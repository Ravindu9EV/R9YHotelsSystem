package edu.icet.entity;

import jakarta.persistence.*;

@Entity
@Table(name="food_orders")
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String foodItem;
    private String description;
    private String status;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User orderedBy;


}
