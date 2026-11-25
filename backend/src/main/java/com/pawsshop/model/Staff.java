package com.pawsshop.model; import javax.persistence.*; @Entity @DiscriminatorValue("STAFF") public class Staff extends ShopUser { @Override public String getRole(){return "STAFF";} }
