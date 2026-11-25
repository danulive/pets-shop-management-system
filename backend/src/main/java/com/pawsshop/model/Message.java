package com.pawsshop.model; import javax.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="contact_messages") public class Message {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; private String name; private String email; @Column(length=2000) private String body; private LocalDateTime createdAt; private boolean resolved;
 public Long getId(){return id;} public void setId(Long v){id=v;} public String getName(){return name;} public void setName(String v){name=v;} public String getEmail(){return email;} public void setEmail(String v){email=v;} public String getBody(){return body;} public void setBody(String v){body=v;} public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;} public boolean isResolved(){return resolved;} public void setResolved(boolean v){resolved=v;}
}
