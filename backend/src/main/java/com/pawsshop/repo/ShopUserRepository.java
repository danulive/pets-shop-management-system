package com.pawsshop.repo; import com.pawsshop.model.ShopUser; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface ShopUserRepository extends JpaRepository<ShopUser,Long> { Optional<ShopUser> findByUsername(String username); }
