package afrcia.crust.auth;

import afrcia.crust.data.models.ApiUser;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class PrincipalApiUser implements UserDetails {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final boolean isEnabled;
    private Map<String, Object> attributes;
    private final Collection<? extends GrantedAuthority> authorities;

    public static PrincipalApiUser create(ApiUser user) {
        List<GrantedAuthority> authorities = getAuthorities(user);
        return new PrincipalApiUser(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getEmailVerified(),
                authorities
        );
    }

    public static PrincipalApiUser create(ApiUser apiUser, Map<String, Object> attributes) {
        PrincipalApiUser principalApiUser = create(apiUser);
        principalApiUser.setAttributes(attributes);
        return principalApiUser;
    }

    private static List<GrantedAuthority> getAuthorities(ApiUser apiUser) {
        return List.of(new SimpleGrantedAuthority(apiUser.getRole().name()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
