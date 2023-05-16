package africa.crust.data.models;

import africa.crust.data.models.enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class ApiUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private Boolean emailVerified = true;

    private String password;

    private String livePublicKey;

    private String liveSecretKey;

    private String testPublicKey;

    private String testSecretKey;

    private Role role;

    @OneToMany
    @JoinColumn(name = "api_user_ip_address_id")
    private Set<ApiUserIpAddress> apiUserIpAddress;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @CreationTimestamp
    private ZonedDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}