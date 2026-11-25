package com.pawsshop.service;
import com.pawsshop.model.*; import com.pawsshop.repo.*; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.*; import org.springframework.core.annotation.Order; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.transaction.support.TransactionTemplate; import java.math.BigDecimal;
@Configuration public class ExtendedSeedData {
 @Bean @Order(2) CommandLineRunner extendedSeed(DataMigrationRepository migrations,ShopUserRepository users,PetRepository pets,PetServiceRepository services,ProductRepository products,PasswordEncoder encoder,TransactionTemplate tx){return args->tx.executeWithoutResult(status->{
  String key="extended-v1";if(migrations.existsById(key))return;
  if(!users.findByUsername("doctor").isPresent()){Doctor d=new Doctor();d.setUsername("doctor");d.setFullName("Dr. Maya Silva");d.setEmail("doctor@example.com");d.setSpecialization("Small animal care");d.setConsultationDetails("General consultations");d.setPasswordHash(encoder.encode("doctor123"));users.save(d);}
  ShopUser customer=users.findByUsername("customer").orElseGet(()->{CustomerAccount c=new CustomerAccount();c.setUsername("customer");c.setFullName("Alex Perera");c.setEmail("customer@example.com");c.setPasswordHash(encoder.encode("customer123"));return users.save(c);});
  Dog dog=new Dog();dog.setName("Charlie");dog.setBreed("Golden Retriever");dog.setAge(3);dog.setOwnerId(customer.getId());dog.setOwnerName(customer.getFullName());dog.setWeight(new BigDecimal("24.5"));dog.setMedicalNotes("No known allergies.");pets.save(dog);
  Cat cat=new Cat();cat.setName("Coco");cat.setBreed("Domestic Shorthair");cat.setAge(2);cat.setOwnerId(customer.getId());cat.setOwnerName(customer.getFullName());cat.setWeight(new BigDecimal("4.2"));pets.save(cat);
  if(services.count()==0){service(services,new PetGrooming(),"The Fresh & Fluffy","Bath, coat styling and nail trim",3500,60);service(services,new VetConsultation(),"Wellness Consultation","Clinical checkup and vaccination advice",4500,30);}
  String[][] items={{"FOOD","Pedigree Dog Food"},{"FOOD","Whiskas Cat Food"},{"FOOD","Goat Milk"},{"ACCESSORY","Dog Grooming Kit"},{"ACCESSORY","Espree Cat Shampoo"},{"ACCESSORY","Medicated Dog Shampoo"},{"ACCESSORY","Drontal Dewormer"},{"ACCESSORY","Warm/Clear Ear Care"},{"ACCESSORY","Dog Bed"},{"ACCESSORY","Cat Bed"},{"ACCESSORY","Dog Collar"},{"ACCESSORY","Cat Collar"},{"ACCESSORY","Dog Feeding Bowl"},{"ACCESSORY","Cat Bowl"},{"ACCESSORY","Dog Chew Toy"}};
  for(String[] item:items)if(products.findAll().stream().noneMatch(p->p.getName().equalsIgnoreCase(item[1]))){Product p=item[0].equals("FOOD")?new Food():new Accessory();p.setName(item[1]);p.setDescription("Demo catalog item");p.setPrice(new BigDecimal("2500.00"));p.setStock(12);products.save(p);}
  migrations.save(new DataMigration(key));
 });}
 private void service(PetServiceRepository repo,PetService s,String name,String description,int price,int duration){s.setServiceName(name);s.setDescription(description);s.setPrice(BigDecimal.valueOf(price));s.setDuration(duration);repo.save(s);}
}
