package africa.crust.data.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CardHolderRegistration implements Serializable {
    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;

    @JsonProperty(value = "phone")
    private String phone;

    @JsonProperty(value = "email_address")
    private String email;

    @JsonProperty(value = "address")
    private Address address;

    @JsonProperty(value = "identity")
    private Identity identity;

    @JsonProperty(value = "meta_data")
    private String metadata;
}