package com.ecommerce.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "HH:mm:ss yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    @Column(updatable = false)
    private Date orderDate;
    private Date orderDateFinal;

    private Integer status;
    private BigDecimal toltalMoney;
    private Long totalProduct;

    @PrePersist
    @PreUpdate
    public void pre(){
        for (OrderDetail orderDetail: orderDetails){
            orderDetail.setOrders(this);
        }
    }
}
