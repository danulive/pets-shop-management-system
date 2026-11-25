package com.pawsshop.model; import javax.persistence.*;
/** Login identity, separate from the pre-existing Customer shop record to preserve legacy rows/API. */
@Entity @DiscriminatorValue("CUSTOMER") public class CustomerAccount extends ShopUser { @Override public String getRole(){return "CUSTOMER";} }
