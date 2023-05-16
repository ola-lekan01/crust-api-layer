package afrcia.crust.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDetailResponse {
    private String status;
    private String message;
    private Data data;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Data {
        private BillingAddress billing_address;
        @Getter
        @Setter
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private static class BillingAddress{
            private String billing_city;
            private String billing_country;
            private String billing_zip_code;
            private String country_code;
        }
        private String brand;
        private String card_currency;
        private String card_id;
        private String card_number;
        private String card_type;
        private String cardholder_id;
        private String createdAt;
        private String cvv;
        private String expiry_month;
        private String expiry_year;
        private String is_active;
        private String issuing_app_id;
        private String last_4;
        private String livemode;
        private MetaData meta_data;
        private String balance;
        private String pin_3ds_activated;
    }
}