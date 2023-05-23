package africa.crust.data.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class InitiatePaymentRequest {
    @JsonProperty(value = "AccountNumber")
    private String accountNumber;

    @JsonProperty(value = "uniqueReference")
    private String uniqueReference;

    @JsonProperty(value = "Amount")
    private BigDecimal amount;
}