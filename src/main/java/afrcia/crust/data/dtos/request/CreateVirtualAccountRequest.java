package afrcia.crust.data.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateVirtualAccountRequest {
    @JsonProperty("AccountNumber")
    private String accountNumber;

    @JsonProperty("ProductCode")
    private String productCode;

    @JsonProperty("NumberOfAccounts")
    private int numberOfAccounts;

    @JsonProperty("TrackingRef")
    private String trackingRef;
}
