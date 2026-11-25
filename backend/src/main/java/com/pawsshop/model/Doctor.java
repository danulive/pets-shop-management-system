package com.pawsshop.model; import javax.persistence.*;
@Entity @DiscriminatorValue("DOCTOR") public class Doctor extends ShopUser {
 private String specialization; private String consultationDetails;
 public String getSpecialization(){return specialization;} public void setSpecialization(String v){specialization=v;}
 public String getConsultationDetails(){return consultationDetails;} public void setConsultationDetails(String v){consultationDetails=v;}
 @Override public String getRole(){return "DOCTOR";}
}
