package afrcia.crust.data.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

import static afrcia.crust.data.models.enums.TokenType.REFRESH;
import static afrcia.crust.utils.AppConstants.EXPIRATION;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String token;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = ApiUser.class)
    @JoinColumn(nullable = false, name = "app_user_id", foreignKey = @ForeignKey(name = "FK_VERIFY_USER"))
    private ApiUser apiUser;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime updatedDate;

    private LocalDateTime expiryDate;

    private String tokenType;

    public Token(String token, ApiUser apiUser, String tokenType) {
        this.token = token;
        this.apiUser = apiUser;
        this.tokenType = tokenType;
        this.expiryDate = calculateExpiryDate();
    }

    private LocalDateTime calculateExpiryDate(){
        return LocalDateTime.now().plusMinutes(EXPIRATION);
    }

    public void updateToken(String code){
        this.token = code;
        this.tokenType = REFRESH.toString();
        this.expiryDate = calculateExpiryDate();
    }

    public Token(ApiUser apiUser){
        this.token = UUID.randomUUID().toString();
        this.tokenType = REFRESH.toString();
        this.expiryDate = calculateExpiryDate();
        this.apiUser = apiUser;
    }

    public void updateToken(String code, String tokenType){
        this.token = code;
        this.tokenType = tokenType;
        this.expiryDate = calculateExpiryDate();
    }
}