package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Orders extends TimeAuditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "orders",
            cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @ManyToOne
    private User user;

    private String fullName;
    private String phoneNumber;
    private String address;
    private String note;

    @CreatedDate
    @Column(updatable = false)
    private Date orderDate;
    private Date orderDateFinal;

    private Integer status;
    private BigDecimal toltalMoney;
}
