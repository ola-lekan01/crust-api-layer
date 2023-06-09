package africa.crust.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationResponse {
    private String firstName;

    private String email;

    private String livePublicKey;

    private String liveSecretKey;

    private String testPublicKey;

    private String testSecretKey;
}