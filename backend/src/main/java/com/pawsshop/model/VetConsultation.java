package com.pawsshop.model; import javax.persistence.*;
@Entity @DiscriminatorValue("VET") public class VetConsultation extends PetService { @Override public String getServiceDetails(){return "Clinical checkups and vaccination advice";} }
