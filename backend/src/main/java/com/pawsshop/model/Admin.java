package com.pawsshop.model; import javax.persistence.*; @Entity @DiscriminatorValue("ADMIN") public class Admin extends ShopUser { @Override public String getRole(){return "ADMIN";} }
