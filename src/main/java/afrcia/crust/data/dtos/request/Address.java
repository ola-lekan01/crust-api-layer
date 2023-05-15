package afrcia.crust.data.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    @JsonProperty(value = "house_no")
    private String houseNo;

    @JsonProperty(value = "address")
    private String address;

    @JsonProperty(value = "city")
    private String city;

    @JsonProperty(value = "state")
    private String state;

    @JsonProperty(value = "country")
    private String country;

    @JsonProperty(value = "postal_code")
    private String postalCode;
}
