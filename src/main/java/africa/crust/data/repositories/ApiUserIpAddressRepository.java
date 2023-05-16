package africa.crust.data.repositories;

import africa.crust.data.models.ApiUser;
import africa.crust.data.models.ApiUserIpAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ApiUserIpAddressRepository extends JpaRepository<ApiUserIpAddress, String> {
    Set<ApiUserIpAddress> findByApiUser(ApiUser user);
}
