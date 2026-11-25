package com.pawsshop.model;
import javax.persistence.*;
@Entity @DiscriminatorValue("FOOD") public class Food extends Product { @Override public String getCategoryLabel(){return "Food & treats";} }
