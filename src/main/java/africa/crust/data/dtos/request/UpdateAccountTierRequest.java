package africa.crust.data.dtos.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAccountTierRequest {

    private String AccountNumber;
    private Integer AccountTier;
    private Boolean SkipAddressVerification;
}
