package africa.crust.data.dtos.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnquiryRequest implements Serializable {

    @JsonProperty("InstitutionCode")
    private String InstitutionCode;

    @JsonProperty("AccountNumber")
    private String AccountNumber;

    @JsonProperty("Amount")
    private int Amount;

    @JsonProperty("Fee")
    private int Fee;

    @JsonProperty("InstrumentNo")
    private String InstrumentNo;

    @JsonProperty("TransactionReference")
    private String TransactionReference;

    @JsonProperty("TransactionType")
    private int TransactionType;

    @JsonProperty("Narration")
    private String Narration;

    @JsonProperty("Message")
    private String Message;
}