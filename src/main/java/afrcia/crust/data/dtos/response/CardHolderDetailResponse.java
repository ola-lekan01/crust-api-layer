package afrcia.crust.data.dtos.response;

import afrcia.crust.data.dtos.request.Address;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardHolderDetailResponse {

    private String status;
    private String message;
    private Data data;
    private MetaData meta_data;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Data {
        private Address address;
        private String cardholder_id;
        private String created_at;
        private String email_address;
        private String first_name;
        private IdentityDetails identity_details;
        private String is_active;
        private String is_id_verified;
        private String issuing_app_id;
        private String last_name;
        private String phone;
    }

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class MetaData {
        private String user_id;
    }
}