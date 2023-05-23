package africa.crust.data.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CreateCustomerAndAccount implements Serializable {

        @JsonProperty("TransactionTrackingRef")
        private String transactionTrackingRef;

        @JsonProperty("CustomerID")
        private String customerID;

        @JsonProperty("AccountReferenceNumber")
        private String accountReferenceNumber;

        @JsonProperty("AccountOpeningTrackingRef")
        private String accountOpeningTrackingRef;

        @JsonProperty("ProductCode")
        private String productCode;

        @JsonProperty("LastName")
        private String lastName;

        @JsonProperty("OtherNames")
        private String otherNames;

        @JsonProperty("AccountName")
        private String accountName;

        @JsonProperty("BVN")
        private String bvn;

        @JsonProperty("FullName")
        private String fullName;

        @JsonProperty("PhoneNo")
        private String phoneNo;

        @JsonProperty("Gender")
        private int gender;

        @JsonProperty("PlaceOfBirth")
        private String placeOfBirth;

        @JsonProperty("DateOfBirth")
        private String dateOfBirth;

        @JsonProperty("Address")
        private String address;

        @JsonProperty("NationalIdentityNo")
        private String nationalIdentityNo;

        @JsonProperty("NextOfKinPhoneNo")
        private String nextOfKinPhoneNo;

        @JsonProperty("NextOfKinName")
        private String nextOfKinName;

        @JsonProperty("ReferralPhoneNo")
        private String referralPhoneNo;

        @JsonProperty("ReferralName")
        private String referralName;

        @JsonProperty("HasSufficientInfoOnAccountInfo")
        private boolean hasSufficientInfoOnAccountInfo;

        @JsonProperty("AccountInformationSource")
        private int accountInformationSource;

        @JsonProperty("OtherAccountInformationSource")
        private String otherAccountInformationSource;

        @JsonProperty("AccountOfficerCode")
        private String accountOfficerCode;

        @JsonProperty("AccountNumber")
        private String accountNumber;

        @JsonProperty("Email")
        private String email;

        @JsonProperty("CustomerImage")
        private String customerImage;

        @JsonProperty("IdentificationImage")
        private String identificationImage;

        @JsonProperty("IdentificationImageType")
        private int identificationImageType;

        @JsonProperty("CustomerSignature")
        private String customerSignature;

        @JsonProperty("NotificationPreference")
        private int notificationPreference;

        @JsonProperty("TransactionPermission")
        private int transactionPermission;

        @JsonProperty("AccountTier")
        private int accountTier;
}
