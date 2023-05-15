package afrcia.crust.controller;

import afrcia.crust.data.dtos.request.ApiUserRequest;
import afrcia.crust.data.dtos.request.LoginRequest;
import afrcia.crust.data.dtos.response.ApiResponse;
import afrcia.crust.data.dtos.response.JwtTokenResponse;
import afrcia.crust.data.dtos.response.RegistrationResponse;
import afrcia.crust.exceptions.GenericException;
import afrcia.crust.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/crust/auth")
@RequiredArgsConstructor
@Tag(name = "Crust Authentication Endpoint", description = "This endpoints are used to Authenticate a User")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody ApiUserRequest apiUserRequest,
                                      HttpServletRequest request) {
        try {
            RegistrationResponse user = authService.registerNewUserAccount(request, apiUserRequest);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully", request.getRequestURL().toString(), user), HttpStatus.CREATED);
        } catch (GenericException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        try {
            JwtTokenResponse authenticationDetail = authService.login(request, loginRequest);
            return new ResponseEntity<>(new ApiResponse(true, "User is successfully logged in",
                    request.getRequestURL().toString(), authenticationDetail), HttpStatus.OK);
        }catch (GenericException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}