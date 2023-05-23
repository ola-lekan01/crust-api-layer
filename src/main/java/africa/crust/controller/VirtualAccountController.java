package africa.crust.controller;

import africa.crust.data.dtos.request.CreateVirtualAccountRequest;
import africa.crust.data.dtos.request.InitiatePaymentRequest;
import africa.crust.data.dtos.response.ApiResponse;
import africa.crust.data.dtos.response.VirtualAccountResponse;
import africa.crust.exceptions.GenericException;
import africa.crust.service.VirtualAccountService;
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
//@SecurityRequirement(name = "BearerAuth")
public class VirtualAccountController {

    private final VirtualAccountService virtualAccount;

    @GetMapping("/get-virtual-account-creation-status")
    public ResponseEntity<ApiResponse> getVirtualAccountCreationStatus(@RequestParam("trackingRef") String trackingRef,
                                                                       HttpServletRequest request) {
        try {
            CompletableFuture<VirtualAccountResponse> response = virtualAccount.getVirtualAccountCreationStatus(trackingRef, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/verify-transaction")
    public ResponseEntity<ApiResponse> verifyTransaction(@RequestParam("uniqueReference") String uniqueReference,
                                                         HttpServletRequest request) {
        try {
            CompletableFuture<VirtualAccountResponse> response = virtualAccount.verifyTransaction(uniqueReference, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-virtual-account")
    public ResponseEntity<ApiResponse> createVirtualAccount(@RequestBody CreateVirtualAccountRequest virtualAccountRequest,
                                                            HttpServletRequest request) {
        try {
            CompletableFuture<VirtualAccountResponse> response = virtualAccount.createVirtualAccount(virtualAccountRequest, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/initiate-payment")
    public ResponseEntity<ApiResponse> initiatePayment(@RequestBody InitiatePaymentRequest initiatePayment,
                                                       HttpServletRequest request) {
        try {
            CompletableFuture<VirtualAccountResponse> response = virtualAccount.initiatePayment(initiatePayment, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}