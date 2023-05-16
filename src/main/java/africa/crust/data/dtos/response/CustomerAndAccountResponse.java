package africa.crust.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerAndAccountResponse {

    private String IsSuccessful;
    private String CustomerIDInString;
    private String TransactionTrackingRef;
    @JsonProperty(value = "Message")
    private String message;
    private String AccountDetails;
    @JsonProperty(value = "Page")
    private Page page;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class Page{
        private int Size;
        private int TotalElements;
        private int TotalPages;
        private int PageIndex;

    }
}