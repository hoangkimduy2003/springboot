package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.entity.OrderDetail;
import com.ecommerce.ecommerce.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrdersDTO {
    private Long id;
    private List<OrderDetailDTO> orderDetails;

    private UserDTO user;

    private String fullName;
    private String phoneNumber;
    private String address;
    private String note;
    private Date orderDate;
    private Date orderDateFinal;
    private Integer status;
    private BigDecimal toltalMoney;

    private Date updatedAt;
}
