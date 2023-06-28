//package com.example.greenlifeproject.entity;
//
//import com.example.greenlifeproject.constant.OrderStatus;
//import lombok.Data;
//import lombok.ToString;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Entity
//@Table(name = "ORDER")
//@Data
//@ToString
//public class OrderEntity {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "ORDER_ID")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "MEMBER_ID")
//    private MemberEntity member;
//    //누가 주문했는지 알기 위해 회원 정보 연결 매핑
//
//    private List<String> orderItems = new ArrayList<>();
//    //오더 아이템으로 바꿔주는것이 필요함
//
//    private Date OrderDate;
//
//    @Enumerated (EnumType.STRING)
//    private OrderStatus status;
//
//}
