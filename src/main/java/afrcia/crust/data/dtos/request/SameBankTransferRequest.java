package afrcia.crust.data.dtos.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SameBankTransferRequest {
    @NotBlank(message = "This Field cannot be blank")
    @JsonProperty(value = "credit_account")
    private String creditAccount;

    @NotBlank(message = "This Field cannot be blank")
    @JsonProperty(value = "narration")
    private String narration;

    @NotBlank(message = "This Field cannot be blank")
    @JsonProperty(value = "cardholder_id")
    private String cardHolderId;

    @NotBlank(message = "This Field cannot be blank")
    @JsonProperty(value = "transaction_amount")
    private String transactionAmount;
}