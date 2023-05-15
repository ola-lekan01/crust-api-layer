package afrcia.crust.service;

import afrcia.crust.auth.PrincipalApiUser;
import afrcia.crust.data.dtos.response.ApiUserKey;
import afrcia.crust.exceptions.GenericException;
import jakarta.servlet.http.HttpServletRequest;

public interface AccessService {
    boolean isCurrentUserIpAddressAndAccessKeyValid(PrincipalApiUser currentApiUser,
                                                    HttpServletRequest request) throws GenericException;

    boolean isUserIpAddressAllowed(String email, String currentIpAddress) throws GenericException;

    ApiUserKey generateTestKey(PrincipalApiUser currentApiUser, HttpServletRequest request) throws GenericException;

    ApiUserKey generateLiveKey(PrincipalApiUser currentApiUser, HttpServletRequest request) throws GenericException;
}