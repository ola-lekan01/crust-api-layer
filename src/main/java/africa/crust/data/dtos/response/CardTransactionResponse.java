package africa.crust.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardTransactionResponse {
    private String status;
    private String message;
    private Data data;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Data {

            private String amount;
            private String bridgecard_transaction_reference;
            private String card_id;
            private String card_transaction_type;
            private String cardholder_id;
            private String client_transaction_reference;
            private String currency;
            private String description;
            private String issuing_app_id;
            private String livemode;
            private String transaction_date;
            private String transaction_timestamp;
            private String merchant_category_code;
    }
}