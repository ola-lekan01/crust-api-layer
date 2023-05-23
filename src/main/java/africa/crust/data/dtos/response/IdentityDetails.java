package africa.crust.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdentityDetails {
    private String blacklisted;
    private String date_of_birth;
    private String first_name;
    private String gender;
    private String id_no;
    private String id_type;
    private String last_name;
    private String phone;
}
