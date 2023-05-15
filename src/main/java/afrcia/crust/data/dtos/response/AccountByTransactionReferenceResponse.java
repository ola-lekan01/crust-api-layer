package afrcia.crust.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountByTransactionReferenceResponse {
        @JsonProperty("AccessLevel")
        private String accessLevel;

        @JsonProperty("AccountNumber")
        private String accountNumber;

        @JsonProperty("AccountStatus")
        private String accountStatus;

        @JsonProperty("AccountType")
        private String accountType;

        @JsonProperty("AvailableBalance")
        private String availableBalance;

        @JsonProperty("WithdrawableBalance")
        private String withdrawableBalance;

        @JsonProperty("Branch")
        private String branch;

        @JsonProperty("CustomerID")
        private String customerID;

        @JsonProperty("CustomerName")
        private String customerName;

        @JsonProperty("DateCreated")
        private String dateCreated;

        @JsonProperty("LastActivityDate")
        private String lastActivityDate;

        @JsonProperty("NUBAN")
        private String nuban;

        @JsonProperty("Refree1CustomerID")
        private String refree1CustomerID;

        @JsonProperty("Refree2CustomerID")
        private String refree2CustomerID;

        @JsonProperty("ReferenceNo")
        private String referenceNo;

        @JsonProperty("PNDStatus")
        private boolean pndStatus;

        @JsonProperty("AccountTier")
        private String accountTier;

    }