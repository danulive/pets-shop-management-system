package com.pawsshop.model;
import javax.persistence.*; import com.fasterxml.jackson.annotation.JsonIgnore;
/** Shared encapsulated account fields. Mapped superclass retains the existing shop_users table. */
@MappedSuperclass public abstract class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(unique=true,nullable=false) private String username;
 @JsonIgnore @Column(nullable=false) private String passwordHash;
 private String fullName; private String email; private String phone;
 public Long getId(){return id;} public String getUsername(){return username;} public void setUsername(String v){username=v;}
 public String getPasswordHash(){return passwordHash;} public void setPasswordHash(String v){passwordHash=v;}
 public String getFullName(){return fullName;} public void setFullName(String v){fullName=v;}
 public String getEmail(){return email;} public void setEmail(String v){email=v;} public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
 @Transient public abstract String getRole();
 @Transient public String getDashboardPath(){return "/dashboard/"+getRole().toLowerCase();}
}
