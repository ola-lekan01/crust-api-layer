package afrcia.crust.auth;

import afrcia.crust.data.models.ApiUser;
import afrcia.crust.data.repositories.ApiUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class ApiAccessDetailsService implements UserDetailsService {

    private final ApiUserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ApiUser user = userRepository.findByEmailIgnoreCase(email).orElseThrow(
                () -> new UsernameNotFoundException(format("User not found with email %s", email)));
        return PrincipalApiUser.create(user);
    }
}