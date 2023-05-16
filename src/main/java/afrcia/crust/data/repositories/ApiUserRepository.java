package afrcia.crust.data.repositories;

import afrcia.crust.data.models.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiUserRepository extends JpaRepository<ApiUser, String> {
    Optional<ApiUser> findByEmailIgnoreCase(String email);

    Boolean existsByEmail(String email);

}
