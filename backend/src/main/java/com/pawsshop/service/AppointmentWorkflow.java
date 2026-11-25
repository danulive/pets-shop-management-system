package com.pawsshop.service; import org.springframework.stereotype.Service; import java.util.*;
@Service public class AppointmentWorkflow {
 public void validateTransition(String from,String to){
  if(from==null||to==null)throw new IllegalArgumentException("Appointment status is required");
  if(from.equals(to))return;
  Map<String,List<String>> allowed=new HashMap<>();allowed.put("PENDING",Arrays.asList("CONFIRMED","CANCELLED"));allowed.put("CONFIRMED",Arrays.asList("COMPLETED","CANCELLED"));allowed.put("BOOKED",Arrays.asList("CONFIRMED","COMPLETED","CANCELLED"));allowed.put("COMPLETED",Collections.emptyList());allowed.put("CANCELLED",Collections.emptyList());
  if(!allowed.getOrDefault(from,Collections.emptyList()).contains(to))throw new IllegalArgumentException("Invalid appointment transition from "+from+" to "+to);
 }
}
