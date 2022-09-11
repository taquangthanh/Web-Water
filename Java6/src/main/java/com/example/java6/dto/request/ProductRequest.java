package com.example.java6.dto.request;

import com.example.java6.entity.ProductType;
import lombok.*;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductRequest {
    private String name;

    private Date createdDate;

    private BigDecimal price;

    private String photo;

    private String producer;

    private ProductType type;

    private Integer status;

    private Integer quantity;
}
