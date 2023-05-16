package africa.crust.controller;

import africa.crust.auth.PrincipalApiUser;
import africa.crust.auth.annotation.CurrentApiUser;
import africa.crust.data.dtos.request.*;
import africa.crust.data.dtos.response.*;
import africa.crust.exceptions.GenericException;
import africa.crust.service.AccountApiService;
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
@RequestMapping("api/v1/crust/account")
@RequiredArgsConstructor
@Tag(name = "Crust Account Endpoint", description = "This endpoints contains related Accounts Endpoints")
@SecurityRequirement(name = "SECRET_KEY")
@SecurityRequirement(name = "BearerAuth")
public class AccountController {

    private final AccountApiService accountApiService;

    @PostMapping("/create-gsw-account")
    public ResponseEntity<ApiResponse> createGswAccount(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                        @RequestParam(name = "data") String data,
                                                        HttpServletRequest request) {
        try {
            CompletableFuture<CreateAccountResponse> response = accountApiService.createGswAccount(currentApiUser, data, request);
            return new ResponseEntity<>(new ApiResponse(true, "GSW Account Created Successfully",
                    request.getRequestURL().toString(), response.get().getData()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-gsa-account")
    public ResponseEntity<ApiResponse> createGsaAccount(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                        @RequestParam(name = "data") String data,
                                                        HttpServletRequest request) {
        try {
            CompletableFuture<CreateAccountResponse> response = accountApiService.createGsaAccount(currentApiUser, data, request);
            return new ResponseEntity<>(new ApiResponse(true, "GSA Account Created Successfully",
                    request.getRequestURL().toString(), response.get().getData()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-customer-account")
    public ResponseEntity<ApiResponse> createCustomerAndAccount(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                                @RequestBody CreateCustomerAndAccount createCustomerAndAccount,
                                                                HttpServletRequest request) {
        try {
            CompletableFuture<CustomerAndAccountResponse> response = accountApiService.createCustomerAndAccount(currentApiUser, createCustomerAndAccount, request);
            return new ResponseEntity<>(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-customer-and-account-t3")
    public ResponseEntity<ApiResponse> createCustomerAndAccountT3(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                                  @RequestBody CreateCustomerAndAccount createCustomerAndAccount,
                                                                  HttpServletRequest request) {
        try {
            CompletableFuture<CustomerAndAccountResponse> response = accountApiService.createCustomerAndAccountT3(currentApiUser, createCustomerAndAccount, request);
            return new ResponseEntity<>(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update-account-tier")
    public ResponseEntity<ApiResponse> updateAccountTier(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                         @RequestBody UpdateAccountTierRequest updateAccountTierRequest,
                                                         HttpServletRequest request) {
        try {
            CompletableFuture<UpdateAccountResponse> response = accountApiService.updateAccountTier(currentApiUser, updateAccountTierRequest, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update-account-tier2")
    public ResponseEntity<ApiResponse> updateAccountTier2(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                          @RequestBody UpdateAccountTierRequest updateAccountTierRequest,
                                                          HttpServletRequest request) {
        try {
            CompletableFuture<UpdateAccountResponse> response = accountApiService.updateAccountTier2(currentApiUser, updateAccountTierRequest, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update-account-notification-preference")
    public ResponseEntity<ApiResponse> updateAccountNotificationPreference(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                                           @RequestBody UpdateAccountNotificationPreference updateAccountNotificationPreference,
                                                                           HttpServletRequest request) {
        try {
            CompletableFuture<UpdateAccountResponse> response = accountApiService.updateAccountNotificationPreference(currentApiUser, updateAccountNotificationPreference, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/update-account-supporting-doc")
    public ResponseEntity<ApiResponse> updateSupportingDocument(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                                @RequestBody UpdateSupportingDocument updateSupportingDocument,
                                                                HttpServletRequest request) {
        try {
            CompletableFuture<UpdateAccountResponse> response = accountApiService.updateSupportingDocument(currentApiUser, updateSupportingDocument, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/get-account-supporting-doc")
    public ResponseEntity<ApiResponse> getSupportingDocument(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                             @RequestParam("accountNumber") String accountNumber,
                                                             @RequestParam("docType") Integer docType,
                                                             HttpServletRequest request) {
        try {
            CompletableFuture<UpdateAccountResponse> response = accountApiService.getSupportingDocument(currentApiUser, accountNumber, docType, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/get-account-by-tracking-ref")
    public ResponseEntity<ApiResponse> getAccountByTransactionTrackingReference(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                                                @RequestParam("transactionTrackingRef") String transactionTrackingRef,
                                                                                @RequestParam("computeWithdrawalBalance") Boolean computeWithdrawalBalance,
                                                                                HttpServletRequest request) {
        try {
            CompletableFuture<AccountByTransactionReferenceResponse> response = accountApiService.getAccountByTransactionTrackingReference(currentApiUser, transactionTrackingRef, computeWithdrawalBalance, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-account-by-tracking-ref2")
    public ResponseEntity<ApiResponse> getAccountByTransactionTrackingReference2(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                                                 @RequestParam("transactionTrackingRef") String transactionTrackingRef,
                                                                                 @RequestParam("computeWithdrawalBalance") Boolean computeWithdrawalBalance,
                                                                                 HttpServletRequest request) {
        try {
            CompletableFuture<AccountByTransactionReferenceResponse> response = accountApiService.getAccountByTransactionTrackingReference2(currentApiUser, transactionTrackingRef, computeWithdrawalBalance, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-account-by-account-number")
    public ResponseEntity<ApiResponse> getAccountByAccountNumber(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                                 @RequestParam("accountNumber") String accountNumber,
                                                                 @RequestParam("computeWithdrawalBalance") Boolean computeWithdrawalBalance,
                                                                 HttpServletRequest request) {
        try {
            CompletableFuture<AccountByTransactionReferenceResponse> response = accountApiService.getAccountByAccountNumber(currentApiUser, accountNumber, computeWithdrawalBalance, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/get-active-saving-by-customer-id")
    public ResponseEntity<ApiResponse> getActiveSavingsAccountsByCustomerID(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                                            @RequestParam(name = "customerId") String customerId,
                                                                            HttpServletRequest request) {
        try {
            CompletableFuture<ActiveSavingResponse> response = accountApiService.getGetActiveSavingsAccountsByCustomerID(currentApiUser, customerId, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-active-saving-by-customer-id-2")
    public ResponseEntity<ApiResponse> getActiveSavingsAccountsByCustomerID2(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                                             @RequestParam(name = "customerId") String customerId,
                                                                             HttpServletRequest request) {
        try {
            CompletableFuture<ActiveSavingResponse2> response = accountApiService.getGetActiveSavingsAccountsByCustomerID2(currentApiUser, customerId, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-account-by-customer-id")
    public ResponseEntity<ApiResponse> getAccountsByCustomerID(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                               @RequestParam(name = "customerId") String customerId,
                                                               HttpServletRequest request) {
        try {
            CompletableFuture<GetAccountsByCustomerIdResponse> response = accountApiService.getAccountsByCustomerID(currentApiUser, customerId, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-quick-account")
    public ResponseEntity<ApiResponse> createQuickAccount(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                          @RequestBody CreateCustomerAndAccount createCustomerAndAccount,
                                                          HttpServletRequest request) {
        try {
            CompletableFuture<CustomerAndAccountResponse> response = accountApiService.createQuickAccount(currentApiUser, createCustomerAndAccount, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/onboard-customer")
    public ResponseEntity<ApiResponse> onBoardCustomer(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                       @RequestBody OnBoardCustomerRequest onBoardCustomer,
                                                       HttpServletRequest request) {
        try {
            CompletableFuture<CustomerAndAccountResponse> response = accountApiService.onBoardCustomer(currentApiUser, onBoardCustomer, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-new-customer-account")
    public ResponseEntity<ApiResponse> createCustomerAccount(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                             @RequestBody CreateCustomerAndAccount createCustomerAndAccount,
                                                             HttpServletRequest request) {
        try {
            CompletableFuture<CustomerAndAccountResponse> response = accountApiService.createCustomerAccount(currentApiUser, createCustomerAndAccount, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-account-Outstanding-Balance")
    public ResponseEntity<ApiResponse> getOutstandingBalance(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                             @RequestParam("accountNumber") String accountNumber,
                                                             HttpServletRequest request) {
        try {
            CompletableFuture<BalanceResponse> response = accountApiService.getOutstandingBalance(currentApiUser, accountNumber, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/add-account-to-customer")
    public ResponseEntity<ApiResponse> addAccountToCustomer(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                            @RequestBody AddAccountToCustomerRequest addAccountToCustomerRequest,
                                                            HttpServletRequest request) {
        try {
            CompletableFuture<CustomerAndAccountResponse> response = accountApiService.addAccountToCustomer(currentApiUser, addAccountToCustomerRequest, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-account")
    public ResponseEntity<ApiResponse> createAccount(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                     @RequestBody CreateCustomerAndAccount createCustomerAndAccount,
                                                     HttpServletRequest request) {
        try {
            CompletableFuture<CustomerAndAccountResponse> response = accountApiService.createAccount(currentApiUser, createCustomerAndAccount, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/retrieve-account-number")
    public ResponseEntity<ApiResponse> retrieveAccountNumber(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                             @RequestParam("acctOpeningTrackingRef") String acctOpeningTrackingRef,
                                                             HttpServletRequest request) {
        try {
            CompletableFuture<CustomerAndAccountResponse> response = accountApiService.retrieveAccountNumber(currentApiUser, acctOpeningTrackingRef, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/name-enquiry")
    public ResponseEntity<ApiResponse> doNameEnquiry(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                     @RequestParam("String accountNumber") String accountNumber,
                                                     HttpServletRequest request) {
        try {
            CompletableFuture<NameEnquiryResponse> response = accountApiService.doNameEnquiry(currentApiUser, accountNumber, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/do-balance-enquiry")
    public ResponseEntity<ApiResponse> doBalanceEnquiry(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                        @RequestBody EnquiryRequest enquiryRequest,
                                                        HttpServletRequest request) {
        try {
            CompletableFuture<DoBalanceEnquiryResponse> response = accountApiService.doBalanceEnquiry(currentApiUser, enquiryRequest, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/do-funds-transfer")
    public ResponseEntity<ApiResponse> doFundsTransfer(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                       @RequestBody EnquiryRequest enquiryRequest,
                                                       HttpServletRequest request) {
        try {
            CompletableFuture<DoBalanceEnquiryResponse> response = accountApiService.doFundsTransfer(currentApiUser, enquiryRequest, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/confirm-transaction")
    public ResponseEntity<ApiResponse> confirmTransaction(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                          @RequestBody EnquiryRequest enquiryRequest,
                                                          HttpServletRequest request) {
        try {
            CompletableFuture<DoBalanceEnquiryResponse> response = accountApiService.confirmTransaction(currentApiUser, enquiryRequest, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reverse-transaction")
    public ResponseEntity<ApiResponse> reverseTransaction(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                          @RequestBody ReverseTransactionRequest reverseTransactionRequest,
                                                          HttpServletRequest request) {
        try {
            CompletableFuture<DoBalanceEnquiryResponse> response = accountApiService.reverseTransaction(currentApiUser, reverseTransactionRequest, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }
}