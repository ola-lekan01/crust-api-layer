package africa.crust.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NameEnquiryResponse {
    @JsonProperty(value = "AccountNumber")

    private String accountNumber;
    @JsonProperty(value = "BVN")
    private String bvn;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Message")
    private String message;
}
