package africa.crust.data.repositories;

import africa.crust.data.models.ApiUser;
import africa.crust.data.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {
    Optional<Token> findByApiUser(ApiUser apiUser);
}
