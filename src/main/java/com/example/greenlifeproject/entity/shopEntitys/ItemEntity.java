package com.example.greenlifeproject.entity.shopEntitys;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
@Table(name = "Item")
public class ItemEntity {
    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private Long productId;  // 물건의 ID

    @Column(name = "ITEM_NAME")
    private String name;  // 물건의 이름

    @Column(name = "PRICE")
    private long price;  // 물건의 가격

    @Column(name = "Description")
    private String description;  // 물건의 설명

    @Column(name = "DELIVERY")
    private int deliveryCost; //배달 비용

    @Column(name = "StockQuantity")
    private int stockQuantity; //재고
}

