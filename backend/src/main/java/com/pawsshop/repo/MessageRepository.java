package com.pawsshop.repo; import com.pawsshop.model.Message; import org.springframework.data.jpa.repository.JpaRepository;
public interface MessageRepository extends JpaRepository<Message,Long> { }
