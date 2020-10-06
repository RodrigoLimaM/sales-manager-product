package br.com.salesmanager.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "products")
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Product {

    @MongoId(value = FieldType.OBJECT_ID)
    private final String _id;

    @Field(name = "product_name")
    private String name;

    @Field(name = "product_unitary_value")
    private BigDecimal unitaryValue;

    @Field(name = "product_quantity")
    private Integer quantity;

    @CreatedDate
    @Field(name = "creation_date")
    private final LocalDateTime creationDate;

    @LastModifiedDate
    @Field(name = "update_date")
    private LocalDateTime updateDate;
}
