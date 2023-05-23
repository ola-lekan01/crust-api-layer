package africa.crust.data.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AddAccountToCustomerRequest implements Serializable {
    @JsonProperty("TransactionTrackingRef")
    private String transactionTrackingRef;

    @JsonProperty("AccountOpeningTrackingRef")
    private String accountOpeningTrackingRef;

    @JsonProperty("CustomerID")
    private String customerID;

    @JsonProperty("ProductCode")
    private String productCode;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("BVN")
    private String bvn;

    @JsonProperty("AccountName")
    private String accountName;

    @JsonProperty("AccountOfficerCode")
    private String accountOfficerCode;
}
