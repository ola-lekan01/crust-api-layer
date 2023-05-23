package africa.crust.data.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FundCardRequest {
    @NotBlank(message = "This Field cannot be blank")
    @JsonProperty(value = "card_id")
    private String cardId;

    @NotBlank(message = "This Field cannot be blank")
    private String amount;

    @NotBlank(message = "This Field cannot be blank")
    private String currency;

    private String transaction_reference;
}