package africa.crust.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardResponse {
    private String status;
    private String message;
    private Data data;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Data {
        private String cardholder_id;
        private String card_id;
        private String currency;
        private String transaction_reference;
        private String account_nuban;
        private String account_name;
        private String bank_code;
        private String account_number;
        private String is_verified;
    }
}