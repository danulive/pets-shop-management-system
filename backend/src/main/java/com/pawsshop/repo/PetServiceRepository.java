package com.pawsshop.repo; import com.pawsshop.model.PetService; import org.springframework.data.jpa.repository.JpaRepository;
public interface PetServiceRepository extends JpaRepository<PetService,Long> { }
