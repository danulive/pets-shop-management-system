package com.pawsshop.repo;
import com.pawsshop.model.Appointment; import org.springframework.data.jpa.repository.JpaRepository;
public interface AppointmentRepository extends JpaRepository<Appointment,Long> { }
