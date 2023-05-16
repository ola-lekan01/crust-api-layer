package africa.crust.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ActiveSavingResponse {

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
    @JsonProperty(value = "CustomerID")
    private String customerID;
    @JsonProperty(value = "Balance")
    private Balance balance;

    @Setter
    @Getter
    @RequiredArgsConstructor
    @AllArgsConstructor
    public static class Balance {
        @JsonProperty(value = "LedgerBalance")
        private int ledgerBalance;
        @JsonProperty(value = "AvailableBalance")
        private int availableBalance;
        @JsonProperty(value = "WithdrawableAmount")
        private int withdrawableAmount;
    }

    @JsonProperty(value = "Status")
    private int status;
    @JsonProperty(value = "AccountTier")
    private String accountTier;
    @JsonProperty(value = "DepositCategory")
    private String depositCategory;
}