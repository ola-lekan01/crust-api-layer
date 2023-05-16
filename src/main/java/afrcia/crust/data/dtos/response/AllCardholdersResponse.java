package afrcia.crust.data.dtos.response;

import afrcia.crust.data.dtos.request.Address;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AllCardholdersResponse {
    private String status;
    private String message;
    private Data data;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Data {
        private List<Cardholder> cardholders;
        @Getter
        @Setter
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Cardholder {
            private Address address;
            private String cardholder_id;
            private String createdAt;
            private String email_address;
            private String first_name;
            private IdentityDetails identity_details;
            private String is_active;
            private String is_id_verified;
            private String issuing_app_id;
            private String last_name;
            private String phone;
        }
        private Meta meta;
        @Getter
        @Setter
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Meta{
            private String total;
            private String pages;
            private String previous;
            private String next;
        }
    }
}