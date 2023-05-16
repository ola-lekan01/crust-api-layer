package afrcia.crust.controller;

import afrcia.crust.auth.PrincipalApiUser;
import afrcia.crust.auth.annotation.CurrentApiUser;
import afrcia.crust.data.dtos.request.CreateVirtualAccountRequest;
import afrcia.crust.data.dtos.request.InitiatePaymentRequest;
import afrcia.crust.data.dtos.response.ApiResponse;
import afrcia.crust.data.dtos.response.VirtualAccountResponse;
import afrcia.crust.exceptions.GenericException;
import afrcia.crust.service.VirtualAccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/v1/crust/virtual-account")
@RequiredArgsConstructor
@Tag(name = "Crust Virtual Account Endpoint", description = "This endpoints contains related Virtual Account Endpoints")
@SecurityRequirement(name = "SECRET_KEY")
@SecurityRequirement(name = "BearerAuth")
public class VirtualAccountController {

    private final VirtualAccountService virtualAccount;

    @GetMapping("/get-virtual-account-creation-status")
    public ResponseEntity<ApiResponse> getVirtualAccountCreationStatus(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                                       @RequestParam("trackingRef") String trackingRef,
                                                                       HttpServletRequest request) {
        try {
            CompletableFuture<VirtualAccountResponse> response = virtualAccount.getVirtualAccountCreationStatus(currentApiUser, trackingRef, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/verify-transaction")
    public ResponseEntity<ApiResponse> verifyTransaction(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                         @RequestParam("uniqueReference") String uniqueReference,
                                                         HttpServletRequest request) {
        try {
            CompletableFuture<VirtualAccountResponse> response = virtualAccount.verifyTransaction(currentApiUser, uniqueReference, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-virtual-account")
    public ResponseEntity<ApiResponse> createVirtualAccount(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                            @RequestBody CreateVirtualAccountRequest virtualAccountRequest,
                                                            HttpServletRequest request) {
        try {
            CompletableFuture<VirtualAccountResponse> response = virtualAccount.createVirtualAccount(currentApiUser, virtualAccountRequest, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/initiate-payment")
    public ResponseEntity<ApiResponse> initiatePayment(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                       @RequestBody InitiatePaymentRequest initiatePayment,
                                                       HttpServletRequest request) {
        try {
            CompletableFuture<VirtualAccountResponse> response = virtualAccount.initiatePayment(currentApiUser, initiatePayment, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}