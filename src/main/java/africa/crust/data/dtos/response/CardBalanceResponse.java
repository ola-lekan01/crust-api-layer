package africa.crust.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardBalanceResponse {
    private String status;
    private String message;
    private Data data;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Data {
        private String card_id;
        private String balance;
        private BigDecimal available_balance;
    }
}