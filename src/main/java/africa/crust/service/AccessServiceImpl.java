package africa.crust.service;

import africa.crust.auth.PrincipalApiUser;
import africa.crust.data.dtos.response.ApiUserKey;
import africa.crust.data.models.ApiUser;
import africa.crust.data.models.ApiUserIpAddress;
import africa.crust.data.repositories.ApiUserIpAddressRepository;
import africa.crust.data.repositories.ApiUserRepository;
import africa.crust.exceptions.GenericException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

import static africa.crust.utils.AppUtils.*;

@Service
@RequiredArgsConstructor
public class AccessServiceImpl implements AccessService {

    private final ApiUserRepository apiUserRepository;
    private final ApiUserIpAddressRepository ipAddressRepository;
    private final PasswordEncoder encoder;


    @Override
    public boolean isUserIpAddressAllowed(String email, String currentIpAddress) throws GenericException {
        ApiUser apiUser = internalFindUserByEmail(email);
        Set<ApiUserIpAddress> ipAddresses = ipAddressRepository.findByApiUser(apiUser);

        for (ApiUserIpAddress ipAddress : ipAddresses)
            if (Objects.equals(ipAddress.getIpAddress(), currentIpAddress)) return true;
        return false;
    }


    private String getUserAccessKey(HttpServletRequest request) {
        return request.getHeader("SECRET_KEY");
    }



    @Override
    public ApiUserKey generateTestKey(PrincipalApiUser currentApiUser, HttpServletRequest request) throws GenericException {
        String userId = currentApiUser.getId();
        String currentIpAddress = request.getRemoteAddr();

        boolean isCurrentUserIpAddressValid = isUserIpAddressValid(userId, currentIpAddress);
        if (!isCurrentUserIpAddressValid) throw new GenericException("Bad Request, Check Credentials and try again");

        ApiUser user = internalFindUserByUserId(userId);

        String generatedTestSecretKey = generateTestSecretKey();
        String generatedTestPublicKey = generateTestPublicKey();

        String encodedTestSecretKey =  encoder.encode(generatedTestSecretKey) + "user=" + user.getId();

        user.setTestPublicKey(generatedTestPublicKey);
        user.setTestSecretKey(encodedTestSecretKey);

        apiUserRepository.save(user);

        return ApiUserKey.builder()
                .testSecretKey(generatedTestSecretKey + "user=" + user.getId() )
                .testPublicKey(generatedTestPublicKey)
                .build();
    }

    @Override
    public ApiUserKey generateLiveKey(PrincipalApiUser currentApiUser, HttpServletRequest request) throws GenericException {
        String userId = currentApiUser.getId();
        String currentIpAddress = request.getRemoteAddr();

        boolean isCurrentUserIpAddressValid = isUserIpAddressValid(userId, currentIpAddress);
        if (!isCurrentUserIpAddressValid) throw new GenericException("Bad Request, Check Credentials and try again");

        ApiUser user = internalFindUserByUserId(userId);

        String generatedLiveSecretKey = generateLiveSecretKey();
        String generatedLivePublicKey = generateLivePublicKey();

        String encodedLiveSecretKey =  encoder.encode(generatedLiveSecretKey) + "user=" + user.getId();

        user.setLivePublicKey(generatedLivePublicKey);
        user.setLiveSecretKey(encodedLiveSecretKey);

        apiUserRepository.save(user);
        return ApiUserKey.builder()
                .testSecretKey(generatedLiveSecretKey + "user=" + user.getId())
                .testPublicKey(generatedLivePublicKey)
                .build();
    }

    @Override
    public boolean isCurrentUserIpAddressAndAccessKeyValid(HttpServletRequest servletRequest) throws GenericException {
        String currentIpAddress = servletRequest.getRemoteAddr();
        String userId = getUserIdFromSecretKey(servletRequest);
        return isUserIpAddressValid(currentIpAddress, userId);
    }

    private String getUserIdFromSecretKey(HttpServletRequest servletRequest) {
        String secretKey = getUserAccessKey(servletRequest);
        int userIdIndex = secretKey.indexOf("user=");
        return secretKey.substring(userIdIndex + "user=".length());
    }

    private boolean isUserIpAddressValid(String currentIpAddress, String userId) throws GenericException {
        ApiUser apiUser = internalFindUserByUserId(userId);
        Set<ApiUserIpAddress> ipAddresses = ipAddressRepository.findByApiUser(apiUser);

        for (ApiUserIpAddress ipAddress : ipAddresses)
            if (Objects.equals(ipAddress.getIpAddress(), currentIpAddress)) return true;
        return false;
    }

    private ApiUser internalFindUserByEmail(String email) throws GenericException {
        return apiUserRepository.findByEmailIgnoreCase(email).orElseThrow(()
                -> new GenericException(String.format("User with %s does not exist", email)));
    }

    private ApiUser internalFindUserByUserId(String userId) throws GenericException {
        ApiUser apiUser =  apiUserRepository.findById(userId).orElse(null);

        if (apiUser != null) return apiUser;
        else throw new GenericException(String.format("User Id: %s does not exist", userId));
    }
}