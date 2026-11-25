package com.pawsshop.service; import com.pawsshop.model.Message; import com.pawsshop.repo.MessageRepository; import org.springframework.stereotype.Service; import java.time.LocalDateTime;
@Service public class MessageService {
 private final MessageRepository messages; public MessageService(MessageRepository messages){this.messages=messages;}
 public Message submit(Message m){if(m.getName()==null||m.getName().trim().isEmpty()||m.getEmail()==null||!m.getEmail().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")||m.getBody()==null||m.getBody().trim().isEmpty())throw new IllegalArgumentException("Name, a valid email and message are required");if(m.getName().length()>200||m.getEmail().length()>254||m.getBody().length()>2000)throw new IllegalArgumentException("Message is too long");m.setId(null);m.setName(m.getName().trim());m.setEmail(m.getEmail().trim());m.setBody(m.getBody().trim());m.setCreatedAt(LocalDateTime.now());m.setResolved(false);return messages.save(m);}
}
