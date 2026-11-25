package com.pawsshop.repo;
import com.pawsshop.model.Pet; import org.springframework.data.jpa.repository.JpaRepository;
public interface PetRepository extends JpaRepository<Pet,Long> { }
