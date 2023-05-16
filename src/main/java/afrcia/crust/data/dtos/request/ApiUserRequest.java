package afrcia.crust.data.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiUserRequest {
    @NotBlank(message = "This Field cannot be blank")
    private String firstName;

    @NotBlank(message = "This Field cannot be blank")
    private String lastName;

    @NotBlank(message = "This Field cannot be blank")
    private String email;

    private String password;
}
