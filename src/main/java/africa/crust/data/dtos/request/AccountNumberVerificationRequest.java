package africa.crust.data.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountNumberVerificationRequest {
    @NotBlank(message = "This Field cannot be blank")
    private String accountNumber;

    @NotBlank(message = "This Field cannot be blank")
    private String bankCode;
}
