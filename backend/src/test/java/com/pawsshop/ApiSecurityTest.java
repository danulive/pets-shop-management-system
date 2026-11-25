package com.pawsshop;
import com.fasterxml.jackson.databind.JsonNode; import com.fasterxml.jackson.databind.ObjectMapper; import org.junit.jupiter.api.Test; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc; import org.springframework.boot.test.context.SpringBootTest; import org.springframework.test.web.servlet.MockMvc; import java.nio.charset.StandardCharsets; import java.util.Base64; import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest(properties={"spring.datasource.url=jdbc:h2:mem:security-test;DB_CLOSE_DELAY=-1","spring.jpa.hibernate.ddl-auto=create-drop"}) @AutoConfigureMockMvc class ApiSecurityTest {
 @Autowired MockMvc mvc; @Autowired ObjectMapper json;
 String auth(String user,String password){return "Basic "+Base64.getEncoder().encodeToString((user+":"+password).getBytes(StandardCharsets.UTF_8));}
 @Test void roleBoundariesAndContact() throws Exception {
  mvc.perform(post("/api/messages").contentType("application/json").content("{\"name\":\"Jane\",\"email\":\"jane@example.com\",\"body\":\"Hello\"}")).andExpect(status().isOk());
  mvc.perform(get("/api/messages").header("Authorization",auth("customer","customer123"))).andExpect(status().isForbidden());
  mvc.perform(get("/api/messages").header("Authorization",auth("admin","admin123"))).andExpect(status().isOk());
  mvc.perform(get("/api/pets").header("Authorization",auth("customer","customer123"))).andExpect(status().isForbidden());
  mvc.perform(get("/api/admin/services").header("Authorization",auth("doctor","doctor123"))).andExpect(status().isForbidden());
  mvc.perform(get("/api/customer/pets").header("Authorization",auth("customer","customer123"))).andExpect(status().isOk()).andExpect(jsonPath("$[0].name").value("Charlie"));
  mvc.perform(get("/api/summary").header("Authorization",auth("staff","staff123"))).andExpect(status().isOk());
 }
 @Test void customerBookingAssignedDoctor() throws Exception {
  String customer=auth("customer","customer123"),doctor=auth("doctor","doctor123");
  JsonNode pets=json.readTree(mvc.perform(get("/api/customer/pets").header("Authorization",customer)).andReturn().getResponse().getContentAsString());
  JsonNode services=json.readTree(mvc.perform(get("/api/services")).andReturn().getResponse().getContentAsString());
  JsonNode doctors=json.readTree(mvc.perform(get("/api/doctors")).andReturn().getResponse().getContentAsString());
  long vet=0;for(JsonNode s:services)if(s.get("type").asText().equals("VET"))vet=s.get("id").asLong();
  String body="{\"petId\":"+pets.get(0).get("id").asLong()+",\"serviceId\":"+vet+",\"doctorId\":"+doctors.get(0).get("id").asLong()+",\"date\":\"2099-01-01\",\"time\":\"10:00\"}";
  mvc.perform(post("/api/customer/appointments").header("Authorization",customer).contentType("application/json").content(body)).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("PENDING"));
  mvc.perform(get("/api/doctor/appointments").header("Authorization",doctor)).andExpect(status().isOk()).andExpect(jsonPath("$[0].pet.name").value("Charlie"));
 }
}
