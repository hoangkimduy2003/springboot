package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.entity.OrderDetail;
import com.ecommerce.ecommerce.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrdersDTO {
    private Long id;
    private List<OrderDetailDTO> orderDetails;

    private UserDTO user;

    @NotBlank(message = "Fullname cannot be blank")
    private String fullName;
    @NotBlank(message = "Phonenumber cannot be blank")
    private String phoneNumber;
    @NotBlank(message = "Address cannot be blank")
    private String address;
    private String note;
    @JsonFormat(pattern = "HH:mm:ss dd/MM/yyyy" ,  timezone = "Asia/Ho_Chi_Minh")
    private Date orderDate;
    private Date orderDateFinal;
    private Integer status;
    private BigDecimal toltalMoney;
    private Long totalProduct;

    private Date updatedAt;
}
