package afrcia.crust.data.repositories;

import afrcia.crust.data.models.ApiUser;
import afrcia.crust.data.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {
    Optional<Token> findByApiUser(ApiUser apiUser);
}
