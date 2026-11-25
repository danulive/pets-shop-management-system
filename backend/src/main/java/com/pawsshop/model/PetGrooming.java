package com.pawsshop.model; import javax.persistence.*;
@Entity @DiscriminatorValue("GROOMING") public class PetGrooming extends PetService { @Override public String getServiceDetails(){return "Bath, coat styling and nail trim";} }
