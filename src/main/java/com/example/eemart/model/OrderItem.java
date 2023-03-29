package com.example.eemart.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity @AllArgsConstructor @Getter @Setter @NoArgsConstructor @Builder
@Table(name = "orderitems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantity")
    private @NotNull int quantity;

    @Column(name = "price")
    private @NotNull double price;

    @Column(name = "created_date")
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;



    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
