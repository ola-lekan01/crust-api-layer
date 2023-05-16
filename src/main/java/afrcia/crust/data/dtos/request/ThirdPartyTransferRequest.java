package afrcia.crust.data.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThirdPartyTransferRequest {

    @NotBlank(message = "This Field cannot be blank")
    @JsonProperty(value = "beneficiary_account_name")
    private String beneficiaryAccountName;

    @NotBlank(message = "This Field cannot be blank")
    @JsonProperty(value = "transaction_amount")
    private String transactionAmount;

    @NotBlank(message = "This Field cannot be blank")
    @JsonProperty(value = "beneficiary_account_number")
    private String beneficiaryAccountNumber;

    @NotBlank(message = "This Field cannot be blank")
    @JsonProperty(value = "beneficiary_bank_code")
    private String beneficiaryBankCode;

    @NotBlank(message = "This Field cannot be blank")
    private String narration;

    @NotBlank(message = "This Field cannot be blank")
    @JsonProperty(value = "cardholder_id")
    private String cardholderId;
}