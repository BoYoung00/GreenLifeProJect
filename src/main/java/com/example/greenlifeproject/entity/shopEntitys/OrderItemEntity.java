package com.example.greenlifeproject.entity.shopEntitys;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;


@Entity
@ToString
@Data
@Table(name = "ORDER_ITEM")
public class OrderItemEntity {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private ItemEntity item;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity order;

    @Column(name = "Order_Price")
    private int orderPrice; //주문 가격 처리

    @Column(name = "Order_Count")
    private int count; //주문 수량 처리
}