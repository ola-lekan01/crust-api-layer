package africa.crust.data.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReverseTransactionRequest {
    @JsonIgnore
    @JsonProperty(value = "InstitutionCode")
    private String institutionCode;
    @JsonProperty(value = "TransactionReference")
    private String transactionReference;
    @JsonProperty(value = "TransactionSuccessRef")
    private String transactionSuccessRef;
}
