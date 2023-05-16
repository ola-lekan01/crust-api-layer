package afrcia.crust.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VirtualAccountResponse {

    @JsonProperty(value = "IsSuccessful")
    private String isSuccessful;
    @JsonProperty(value = "CustomerIDInString")
    private String customerIdInString;
    @JsonProperty(value = "Message")
    private String message;
    @JsonProperty(value = "TransactionTrackingRef")
    private String transactionTrackingReference;
    @JsonProperty(value = "Page")
    private String page;
}