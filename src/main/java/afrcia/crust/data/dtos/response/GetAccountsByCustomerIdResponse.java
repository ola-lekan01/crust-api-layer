package afrcia.crust.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAccountsByCustomerIdResponse {

    private String address;
    private String age;
    @JsonProperty(value = "BVN")
    private String BVN;
    private String branchCode;
    private String customerID;
    private String dateOfBirth;
    private String email;
    private String gender;
    private String localGovernmentArea;
    private String name;
    private String phoneNumber;
    private String state;
    @JsonProperty(value = "Accounts")
    private List<Account> accounts;

    @Getter
    @Setter
    private static class Account{
        private String accessLevel;
        private String accountNumber;
        private String accountStatus;
        private String accountType;
        private String availableBalance;
        private String branch;
        private String customerID;
        private String accountName;
        private String productCode;
        private String dateCreated;
        private String lastActivityDate;
        private String ledgerBalance;
        @JsonProperty(value = "NUBAN")
        private String NUBAN;
        private String referenceNo;
        private String withdrawableAmount;
        private String kycLevel;
    }
}