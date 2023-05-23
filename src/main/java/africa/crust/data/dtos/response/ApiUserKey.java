package africa.crust.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiUserKey {
    private String testSecretKey;
    private String testPublicKey;
    private String liveSecretKey;
    private String livePublicKey;
}