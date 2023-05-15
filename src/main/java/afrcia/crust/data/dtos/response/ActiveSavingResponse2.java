package afrcia.crust.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ActiveSavingResponse2 {
    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Number")
    private String number;

    @JsonProperty(value = "NUBAN")
    private String nuban;

    @JsonProperty(value = "ProductName")
    private String productName;

    @JsonProperty(value = "ProductCode")
    private String productCode;

    @JsonProperty(value = "BranchCode")
    private String branchCode;

    @JsonProperty(value = "CustomerID")
    private String customerID;

    @JsonProperty(value = "Status")
    private int status;

    @JsonProperty(value = "AccountTier")
    private String accountTier;

    @JsonProperty(value = "LedgerBalance")
    private double ledgerBalance;

    @JsonProperty(value = "AvailableBalance")
    private double availableBalance;

    @JsonProperty(value = "WithdrawableAmount")
    private double withdrawableAmount;
}