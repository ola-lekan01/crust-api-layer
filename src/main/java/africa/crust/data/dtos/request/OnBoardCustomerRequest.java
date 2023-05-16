package africa.crust.data.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OnBoardCustomerRequest implements Serializable {

    @JsonProperty("TransactionTrackingRef")
    private String transactionTrackingRef;

    @JsonProperty("PlaceOfBirth")
    private String placeOfBirth;

    @JsonProperty("AccountOpeningTrackingRef")
    private String accountOpeningTrackingRef;

    @JsonProperty("ProductCode")
    private String productCode;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("Address")
    private String address;

    @JsonProperty("StateOfOrigin")
    private String stateOfOrigin;

    @JsonProperty("PhoneNumber")
    private String phoneNumber;

    @JsonProperty("Passport")
    private String passport;

    @JsonProperty("NIN")
    private String nin;

    @JsonProperty("Gender")
    private int gender;

    @JsonProperty("DateOfBirth")
    private String dateOfBirth;

    @JsonProperty("BVN")
    private String bvn;

    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("OtherNames")
    private String otherNames;

    @JsonProperty("AccountOfficerCode")
    private String accountOfficerCode;

    @JsonProperty("NotificationPreference")
    private int notificationPreference;
}
