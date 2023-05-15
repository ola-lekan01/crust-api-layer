package afrcia.crust.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BalanceResponse {
    @JsonProperty(value = "AccountNumber")
    private String accountNumber;
    @JsonProperty(value = "AvailableBalance")
    private String availableBalance;
    @JsonProperty(value = "FinancialDate")
    private String financialDate;
}