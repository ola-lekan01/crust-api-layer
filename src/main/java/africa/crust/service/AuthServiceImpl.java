package africa.crust.service;

import africa.crust.auth.JwtTokenProvider;
import africa.crust.data.dtos.request.ApiUserRequest;
import africa.crust.data.dtos.request.LoginRequest;
import africa.crust.data.dtos.response.JwtTokenResponse;
import africa.crust.data.dtos.response.RegistrationResponse;
import africa.crust.data.models.ApiUser;
import africa.crust.data.models.ApiUserIpAddress;
import africa.crust.data.models.Token;
import africa.crust.data.repositories.ApiUserIpAddressRepository;
import africa.crust.data.repositories.ApiUserRepository;
import africa.crust.data.repositories.TokenRepository;
import africa.crust.exceptions.GenericException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static africa.crust.data.models.enums.Role.USER;
import static africa.crust.data.models.enums.TokenType.REFRESH;
import static africa.crust.utils.AppUtils.*;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{

    private final ModelMapper mapper;
    private final ApiUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final ApiUserIpAddressRepository ipAddressRepository;
    private final AccessService accessService;

    @Override
    public RegistrationResponse registerNewUserAccount(HttpServletRequest request, ApiUserRequest userRequest) throws GenericException {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(userRequest.getEmail()))) {
            throw new GenericException(String.format("%s is already in use", userRequest.getEmail()));
        }
        ApiUser apiUser = mapper.map(userRequest, ApiUser.class);

        apiUser.setRole(USER);
        apiUser.setPassword(passwordEncoder.encode(apiUser.getPassword()));

        ApiUser savedApiUser = saveAUser(apiUser);

        ApiUserIpAddress userIpAddress = new ApiUserIpAddress();
        userIpAddress.setApiUser(savedApiUser);
        userIpAddress.setIpAddress(request.getRemoteAddr());
        userIpAddress.setState("Lagos");
        userIpAddress.setCountry("Nigeria");

        ApiUserIpAddress savedApiUserIpAddress = ipAddressRepository.save(userIpAddress);

        Set<ApiUserIpAddress> ipAddresses = new HashSet<>();
        ipAddresses.add(savedApiUserIpAddress);
        savedApiUser.setApiUserIpAddress(ipAddresses);

        String generatedLiveKey = generateLiveSecretKey();
        String generatedTestKey = generateTestSecretKey();

        String encodedLiveKey =  passwordEncoder.encode(generatedLiveKey) + "user=" + savedApiUser.getId();
        String encodedTestKey =  passwordEncoder.encode(generatedTestKey) + "user=" + savedApiUser.getId();

        apiUser.setLivePublicKey(generateLivePublicKey());
        apiUser.setLiveSecretKey(encodedLiveKey);

        apiUser.setTestPublicKey(generateTestPublicKey());
        apiUser.setTestSecretKey(encodedTestKey);

        ApiUser savedUser = saveAUser(savedApiUser);

        RegistrationResponse mappedResponse = mapper.map(savedUser, RegistrationResponse.class);
        mappedResponse.setTestSecretKey(generatedTestKey + "user=" + savedApiUser.getId());
        mappedResponse.setLiveSecretKey(generatedLiveKey + "user=" + savedApiUser.getId());
        return mappedResponse;
    }

    @Override
    public ApiUser saveAUser(ApiUser user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public JwtTokenResponse login(HttpServletRequest request, LoginRequest loginRequest) throws GenericException{
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = tokenProvider.generateToken(loginRequest.getEmail());
        ApiUser user = internalFindUserByEmail(loginRequest.getEmail());

        boolean isUserIpAddressAllowed = accessService.isUserIpAddressAllowed(user.getEmail(), request.getRemoteAddr());
        if (!isUserIpAddressAllowed) throw new GenericException("Bad Credentials, Try Again");

        JwtTokenResponse jwtTokenResponse = new JwtTokenResponse();
        Optional<Token> optionalToken = tokenRepository.findByApiUser(user);

        if (optionalToken.isPresent() && isValidToken(optionalToken.get().getExpiryDate())) {
            jwtTokenResponse.setRefreshToken(optionalToken.get().getToken());
        } else if (optionalToken.isPresent() && !isValidToken(optionalToken.get().getExpiryDate())) {
            Token token = optionalToken.get();
            token.updateToken(UUID.randomUUID().toString(), REFRESH.name());
            jwtTokenResponse.setRefreshToken(tokenRepository.save(token).getToken());
        } else {
            Token refreshToken = new Token(user);
            jwtTokenResponse.setRefreshToken(tokenRepository.save(refreshToken).getToken());
        }
        jwtTokenResponse.setJwtToken(jwtToken);
        jwtTokenResponse.setEmail(user.getEmail());
        return jwtTokenResponse;
    }

    public ApiUser internalFindUserByEmail(String email) throws GenericException {
        return userRepository.findByEmailIgnoreCase(email).orElseThrow(()
                -> new GenericException(String.format("User with %s does not exist", email)));
    }
}