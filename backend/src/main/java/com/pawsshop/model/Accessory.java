package com.pawsshop.model;
import javax.persistence.*;
@Entity @DiscriminatorValue("ACCESSORY") public class Accessory extends Product { @Override public String getCategoryLabel(){return "Accessories";} }
