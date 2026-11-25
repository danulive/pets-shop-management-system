package com.pawsshop.model;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;
@Entity @Inheritance(strategy=InheritanceType.SINGLE_TABLE) @DiscriminatorColumn(name="pet_type")
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,include=JsonTypeInfo.As.PROPERTY,property="type") @JsonSubTypes({@JsonSubTypes.Type(value=Dog.class,name="DOG"),@JsonSubTypes.Type(value=Cat.class,name="CAT")})
public abstract class Pet {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String name; private String breed; private Integer age; private String ownerName; private Long ownerId; private java.math.BigDecimal weight; @Column(length=2000) private String medicalNotes;
 public Long getId(){return id;} public void setId(Long v){id=v;} public String getName(){return name;} public void setName(String v){name=v;} public String getBreed(){return breed;} public void setBreed(String v){breed=v;} public Integer getAge(){return age;} public void setAge(Integer v){age=v;} public String getOwnerName(){return ownerName;} public void setOwnerName(String v){ownerName=v;}
 public Long getOwnerId(){return ownerId;} public void setOwnerId(Long v){ownerId=v;} public java.math.BigDecimal getWeight(){return weight;} public void setWeight(java.math.BigDecimal v){weight=v;} public String getMedicalNotes(){return medicalNotes;} public void setMedicalNotes(String v){medicalNotes=v;}
 @Transient public abstract String getDisplayInfo();
 @Transient public abstract String getCareAdvice();
}
