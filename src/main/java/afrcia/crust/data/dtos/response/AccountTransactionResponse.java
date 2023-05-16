package afrcia.crust.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AccountTransactionResponse implements Serializable {

    @JsonProperty("AccountNumber")
    private String accountNumber;

    @JsonProperty("NUBAN")
    private String nuban;

    @JsonProperty("AccountName")
    private String accountName;

    @JsonProperty("TransactionDate")
    private String transactionDate;

    @JsonProperty("CurrentDate")
    private String currentDate;

    @JsonProperty("ReferenceID")
    private String referenceID;

    @JsonProperty("Narration")
    private String narration;

    @JsonProperty("RecordType")
    private int recordType;

    @JsonProperty("Amount")
    private double amount;
}
