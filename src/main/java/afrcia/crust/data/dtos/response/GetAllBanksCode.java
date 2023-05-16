package afrcia.crust.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAllBanksCode {
    private String status;

    private String message;

    private Data data;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Data {
        private List<Bank> banks;

        @Getter
        @Setter
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private static class Bank {
            private String bankCode;

            private String bankName;
        }
    }
}
