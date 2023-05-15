package afrcia.crust.data.dtos.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateCardRequest {

    @NotBlank(message = "This Field cannot be blank")
    @JsonProperty(value = "cardholder_id")
    private String cardholderId;

    @NotBlank(message = "This Field cannot be blank")
    @JsonProperty(value = "card_type")
    private String cardType;

    @NotBlank(message = "This Field cannot be blank")
    @JsonProperty(value = "card_brand")
    private String cardBrand;

    @NotBlank(message = "This Field cannot be blank")
    @JsonProperty(value = "card_currency")
    private String cardCurrency;

    @JsonProperty(value = "card_token_number")
    private String cardTokenNumber;

    @JsonProperty(value = "meta_data")
    private MetaData metaData;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class MetaData {
        private String user_id;
    }
}