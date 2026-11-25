package com.pawsshop.model;
import javax.persistence.*; import java.math.BigDecimal; import com.fasterxml.jackson.annotation.*;
@Entity @Inheritance(strategy=InheritanceType.SINGLE_TABLE) @DiscriminatorColumn(name="product_type")
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,include=JsonTypeInfo.As.PROPERTY,property="type") @JsonSubTypes({@JsonSubTypes.Type(value=Food.class,name="FOOD"),@JsonSubTypes.Type(value=Accessory.class,name="ACCESSORY")})
public abstract class Product {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(nullable=false) private String name; private String description; @Column(precision=12,scale=2,nullable=false) private BigDecimal price; private int stock;
 public Long getId(){return id;} public void setId(Long v){id=v;} public String getName(){return name;} public void setName(String v){name=v;} public String getDescription(){return description;} public void setDescription(String v){description=v;} public BigDecimal getPrice(){return price;} public void setPrice(BigDecimal v){price=v;} public int getStock(){return stock;} public void setStock(int v){stock=v;}
 public void removeStock(int quantity){if(quantity<=0 || quantity>stock)throw new IllegalArgumentException("Insufficient stock for " + name); stock-=quantity;}
 @Transient public abstract String getCategoryLabel();
}
