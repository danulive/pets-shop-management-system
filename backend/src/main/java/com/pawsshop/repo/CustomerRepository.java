package com.pawsshop.repo;
import com.pawsshop.model.Customer; import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Customer,Long> { }
