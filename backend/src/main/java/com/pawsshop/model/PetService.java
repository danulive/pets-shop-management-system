package com.pawsshop.model;
import javax.persistence.*; import java.math.BigDecimal; import com.fasterxml.jackson.annotation.*;
@Entity @Table(name="pet_services") @Inheritance(strategy=InheritanceType.SINGLE_TABLE) @DiscriminatorColumn(name="service_type")
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,include=JsonTypeInfo.As.PROPERTY,property="type") @JsonSubTypes({@JsonSubTypes.Type(value=PetGrooming.class,name="GROOMING"),@JsonSubTypes.Type(value=VetConsultation.class,name="VET")})
public abstract class PetService {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(nullable=false) private String serviceName; @Column(length=1000) private String description; @Column(precision=12,scale=2) private BigDecimal price; private int duration;
 public Long getId(){return id;} public void setId(Long v){id=v;} public String getServiceName(){return serviceName;} public void setServiceName(String v){serviceName=v;} public String getDescription(){return description;} public void setDescription(String v){description=v;} public BigDecimal getPrice(){return price;} public void setPrice(BigDecimal v){price=v;} public int getDuration(){return duration;} public void setDuration(int v){duration=v;} @Transient public abstract String getServiceDetails();
}
