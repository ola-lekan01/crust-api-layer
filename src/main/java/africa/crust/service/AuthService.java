package africa.crust.service;

import africa.crust.data.dtos.request.ApiUserRequest;
import africa.crust.data.dtos.request.LoginRequest;
import africa.crust.data.dtos.response.JwtTokenResponse;
import africa.crust.data.dtos.response.RegistrationResponse;
import africa.crust.exceptions.GenericException;
import africa.crust.data.models.ApiUser;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {

    RegistrationResponse registerNewUserAccount(HttpServletRequest request, ApiUserRequest userRequest) throws GenericException;
    JwtTokenResponse login( HttpServletRequest request, LoginRequest loginRequest) throws GenericException;
    ApiUser internalFindUserByEmail(String email) throws GenericException;
    ApiUser saveAUser(ApiUser user);
}
