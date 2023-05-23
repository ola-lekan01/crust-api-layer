package africa.crust.data.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
public class ApiUserIpAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String ipAddress;

    @ManyToOne
    @JoinColumn(name = "api_user_id")
    private ApiUser apiUser;

    private String State;

    private String country;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private ZonedDateTime dateAccessed;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private ZonedDateTime dateCreated;
}