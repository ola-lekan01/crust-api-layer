package africa.crust.service;

import africa.crust.auth.PrincipalApiUser;
import africa.crust.data.dtos.response.ApiUserKey;
import africa.crust.exceptions.GenericException;
import jakarta.servlet.http.HttpServletRequest;

public interface AccessService {
    boolean isCurrentUserIpAddressAndAccessKeyValid(HttpServletRequest request) throws GenericException;

    boolean isUserIpAddressAllowed(String email, String currentIpAddress) throws GenericException;

    ApiUserKey generateTestKey(PrincipalApiUser currentApiUser, HttpServletRequest request) throws GenericException;

    ApiUserKey generateLiveKey(PrincipalApiUser currentApiUser, HttpServletRequest request) throws GenericException;

}