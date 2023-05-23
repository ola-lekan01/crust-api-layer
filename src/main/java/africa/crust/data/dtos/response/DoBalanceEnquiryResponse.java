package africa.crust.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoBalanceEnquiryResponse {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("AvailableBalance")
    private int availableBalance;

    @JsonProperty("WithdrawableAmount")
    private int withdrawableAmount;

    @JsonProperty("Balance2")
    private int balance2;

    @JsonProperty("ReferenceNo")
    private String referenceNo;

    @JsonProperty("IsSuccessful")
    private boolean isSuccessful;

    @JsonProperty("IsCashAssetAccount")
    private boolean isCashAssetAccount;

    @JsonProperty("IsBankOrSuspenseAssetAccount")
    private boolean isBankOrSuspenseAssetAccount;

    @JsonProperty("Code")
    private String code;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("UniqueLogID")
    private String uniqueLogID;
}