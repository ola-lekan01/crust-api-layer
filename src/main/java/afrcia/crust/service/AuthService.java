package afrcia.crust.service;

import afrcia.crust.data.dtos.request.ApiUserRequest;
import afrcia.crust.data.dtos.request.LoginRequest;
import afrcia.crust.data.dtos.response.JwtTokenResponse;
import afrcia.crust.data.dtos.response.RegistrationResponse;
import afrcia.crust.exceptions.GenericException;
import afrcia.crust.data.models.ApiUser;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {

    RegistrationResponse registerNewUserAccount(HttpServletRequest request, ApiUserRequest userRequest) throws GenericException;
    JwtTokenResponse login( HttpServletRequest request, LoginRequest loginRequest) throws GenericException;
    ApiUser internalFindUserByEmail(String email) throws GenericException;

    ApiUser saveAUser(ApiUser user);

}
