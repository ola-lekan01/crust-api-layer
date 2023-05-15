package afrcia.crust.service;

import afrcia.crust.auth.PrincipalApiUser;
import afrcia.crust.data.dtos.response.ApiUserKey;
import afrcia.crust.data.models.ApiUser;
import afrcia.crust.data.models.ApiUserIpAddress;
import afrcia.crust.data.repositories.ApiUserIpAddressRepository;
import afrcia.crust.data.repositories.ApiUserRepository;
import afrcia.crust.exceptions.GenericException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

import static afrcia.crust.utils.AppUtils.*;

@Service
@RequiredArgsConstructor
public class AccessServiceImpl implements AccessService {

    private final ApiUserRepository apiUserRepository;
    private final ApiUserIpAddressRepository ipAddressRepository;
    private final PasswordEncoder passwordEncoder;

    private boolean isUserIpAddressAllowed(PrincipalApiUser currentApiUser, String currentIpAddress) throws GenericException {
        ApiUser apiUser = internalFindUserByEmail(currentApiUser.getEmail());
        Set<ApiUserIpAddress> ipAddresses = ipAddressRepository.findByApiUser(apiUser);

        for (ApiUserIpAddress ipAddress : ipAddresses)
            if (Objects.equals(ipAddress.getIpAddress(), currentIpAddress)) return true;
        return false;
    }

    public boolean isUserIpAddressAllowed(String email, String currentIpAddress) throws GenericException {
        ApiUser apiUser = internalFindUserByEmail(email);
        Set<ApiUserIpAddress> ipAddresses = ipAddressRepository.findByApiUser(apiUser);

        for (ApiUserIpAddress ipAddress : ipAddresses)
            if (Objects.equals(ipAddress.getIpAddress(), currentIpAddress)) return true;
        return false;
    }

    private boolean isAccessKeyValid(PrincipalApiUser currentApiUser, HttpServletRequest request) throws GenericException {
        String currentApiUserAccessKey = getUserAccessKey(request);
        ApiUser apiUser = internalFindUserByEmail(currentApiUser.getEmail());

        if (currentApiUserAccessKey == null) throw new GenericException("Invalid Request");
        if (passwordEncoder.matches(currentApiUserAccessKey, apiUser.getLiveSecretKey())) return true;
        return passwordEncoder.matches(currentApiUserAccessKey, apiUser.getTestSecretKey());
    }


    private String getUserAccessKey(HttpServletRequest request) {
        return request.getHeader("SECRET_KEY");
    }

    public boolean isCurrentUserIpAddressAndAccessKeyValid(PrincipalApiUser currentApiUser,
                                                           HttpServletRequest request) throws GenericException {
        String currentIpAddress = request.getRemoteAddr();
        return isUserIpAddressAllowed(currentApiUser, currentIpAddress) && isAccessKeyValid(currentApiUser, request);
    }

    @Override
    public ApiUserKey generateTestKey(PrincipalApiUser currentApiUser, HttpServletRequest request) throws GenericException {
        boolean isCurrentUserIpAddressAndAccessKeyValid = isCurrentUserIpAddressAndAccessKeyValid(currentApiUser, request);
        if (!isCurrentUserIpAddressAndAccessKeyValid)
            throw new GenericException("Bad Request, Check Credentials and try again");
        String testSecretKey = generateTestSecretKey();
        String testPublicKey = generateTestPublicKey();

        ApiUser user = internalFindUserByEmail(currentApiUser.getEmail());
        user.setTestPublicKey(testPublicKey);
        user.setTestSecretKey(passwordEncoder.encode(testSecretKey));
        apiUserRepository.save(user);

        return ApiUserKey.builder()
                .testSecretKey(testSecretKey)
                .testPublicKey(testPublicKey)
                .build();
    }

    @Override
    public ApiUserKey generateLiveKey(PrincipalApiUser currentApiUser, HttpServletRequest request) throws GenericException {
        boolean isCurrentUserIpAddressAndAccessKeyValid = isCurrentUserIpAddressAndAccessKeyValid(currentApiUser, request);
        if (!isCurrentUserIpAddressAndAccessKeyValid)
            throw new GenericException("Bad Request, Check Credentials and try again");

        String liveSecretKey = generateLiveSecretKey();
        String livePublicKey = generateLivePublicKey();
        ApiUser user = internalFindUserByEmail(currentApiUser.getEmail());
        user.setLivePublicKey(livePublicKey);
        user.setLiveSecretKey(passwordEncoder.encode(liveSecretKey));

        apiUserRepository.save(user);
        return ApiUserKey.builder()
                .testSecretKey(liveSecretKey)
                .testPublicKey(livePublicKey)
                .build();
    }

    private ApiUser internalFindUserByEmail(String email) throws GenericException {
        return apiUserRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new GenericException(String.format("User with %s does not exist", email)));

    }
}