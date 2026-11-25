package com.pawsshop.model;
import javax.persistence.*;
/** Existing account entity/table retained for compatibility; role subtypes share one table. */
@Entity @Table(name="shop_users") @Inheritance(strategy=InheritanceType.SINGLE_TABLE) @DiscriminatorColumn(name="user_type")
public abstract class ShopUser extends User { }
