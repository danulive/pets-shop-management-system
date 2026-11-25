package com.pawsshop.model; import javax.persistence.*;
@Entity @DiscriminatorValue("CAT") public class Cat extends Pet {
 private Boolean indoorOnly;
 public Boolean getIndoorOnly(){return indoorOnly;} public void setIndoorOnly(Boolean v){indoorOnly=v;}
 @Override public String getDisplayInfo(){return "Cat · " + (getBreed()==null?"":getBreed());}
 @Override public String getCareAdvice(){return "Regular brushing and a calm environment.";}
}
