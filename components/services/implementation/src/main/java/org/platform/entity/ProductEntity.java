package org.platform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.platform.constants.DatabaseConstants;
import org.platform.model.Product;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name= DatabaseConstants.PRODUCT_TABLE, schema = DatabaseConstants.SCHEMA)
public class ProductEntity {
    @Id
    private UUID productId;
    @Column(name = "product_name")
    private String productName;
    private String description;
    @Column(name = "product_code")
    private String productCode;

   public ProductEntity(Product product){
       this.setProductName(product.getProductName());
       this.productCode = product.getProductCode();
       this.description = product.getProductDescription();
   }
   public Product toProduct(){
       return new Product(this.getProductName(),this.getDescription(),this.getProductCode());
   }

}
