package afrcia.crust.controller;

import afrcia.crust.auth.PrincipalApiUser;
import afrcia.crust.auth.annotation.CurrentApiUser;
import afrcia.crust.data.dtos.response.ApiResponse;
import afrcia.crust.data.dtos.response.ApiUserKey;
import afrcia.crust.exceptions.GenericException;
import afrcia.crust.service.AccessService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/crust/api_keys")
@RequiredArgsConstructor
@Tag(name = "Crust Access Management", description = "Endpoints for managing Public and Test Keys")
@SecurityRequirement(name = "BearerAuth")
public class AccessServiceController {

    private final AccessService accessService;

    @PostMapping("/generate_test_key")
    public ResponseEntity<ApiResponse> generateTestKey(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                       HttpServletRequest request) {
        try {
            ApiUserKey response = accessService.generateTestKey(currentApiUser, request);
            return ResponseEntity.ok(new ApiResponse(true, "Test Key Generated Successfully",
                    request.getRequestURL().toString(), response));
        } catch (GenericException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/generate_live_key")
    public ResponseEntity<ApiResponse> generateLiveKey(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                       HttpServletRequest request) {
        try {
            ApiUserKey response = accessService.generateLiveKey(currentApiUser, request);
            return ResponseEntity.ok(new ApiResponse(true, "Live Key Generated Successfully",
                    request.getRequestURL().toString(), response));
        } catch (GenericException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
