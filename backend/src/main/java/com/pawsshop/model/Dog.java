package com.pawsshop.model; import javax.persistence.*;
@Entity @DiscriminatorValue("DOG") public class Dog extends Pet {
 private String exercisePreference;
 public String getExercisePreference(){return exercisePreference;} public void setExercisePreference(String v){exercisePreference=v;}
 @Override public String getDisplayInfo(){return "Dog · " + (getBreed()==null?"":getBreed());}
 @Override public String getCareAdvice(){return "Regular walks and coat care.";}
}
