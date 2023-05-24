package africa.crust.controller;

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
//@SecurityRequirement(name = "BearerAuth")
public class AccountController {

    private final AccountApiService accountApiService;

    @PostMapping("/create-gsw-account")
    public ResponseEntity<ApiResponse> createGswAccount(@RequestParam(name = "data") String data,
                                                        HttpServletRequest request) {
        try {
            CompletableFuture<CreateAccountResponse> response = accountApiService.createGswAccount(data, request);
            return new ResponseEntity<>(new ApiResponse(true, "GSW Account Created Successfully",
                    request.getRequestURL().toString(), response.get().getData()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-gsa-account")
    public ResponseEntity<ApiResponse> createGsaAccount(@RequestParam(name = "data") String data,
                                                        HttpServletRequest request) {
        try {
            CompletableFuture<CreateAccountResponse> response = accountApiService.createGsaAccount(data, request);
            return new ResponseEntity<>(new ApiResponse(true, "GSA Account Created Successfully",
                    request.getRequestURL().toString(), response.get().getData()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-customer-account")
    public ResponseEntity<ApiResponse> createCustomerAndAccount(@RequestBody CreateCustomerAndAccount createCustomerAndAccount,
                                                                HttpServletRequest request) {
        try {
            CompletableFuture<CustomerAndAccountResponse> response = accountApiService.createCustomerAndAccount(createCustomerAndAccount, request);
            return new ResponseEntity<>(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-customer-and-account-t3")
    public ResponseEntity<ApiResponse> createCustomerAndAccountT3(@RequestBody CreateCustomerAndAccount createCustomerAndAccount,
                                                                  HttpServletRequest request) {
        try {
            CompletableFuture<CustomerAndAccountResponse> response = accountApiService.createCustomerAndAccountT3(createCustomerAndAccount, request);
            return new ResponseEntity<>(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update-account-tier")
    public ResponseEntity<ApiResponse> updateAccountTier(@RequestBody UpdateAccountTierRequest updateAccountTierRequest,
                                                         HttpServletRequest request) {
        try {
            CompletableFuture<UpdateAccountResponse> response = accountApiService.updateAccountTier(updateAccountTierRequest, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update-account-tier2")
    public ResponseEntity<ApiResponse> updateAccountTier2(@RequestBody UpdateAccountTierRequest updateAccountTierRequest,
                                                          HttpServletRequest request) {
        try {
            CompletableFuture<UpdateAccountResponse> response = accountApiService.updateAccountTier2(updateAccountTierRequest, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update-account-notification-preference")
    public ResponseEntity<ApiResponse> updateAccountNotificationPreference(@RequestBody UpdateAccountNotificationPreference updateAccountNotificationPreference,
                                                                           HttpServletRequest request) {
        try {
            CompletableFuture<UpdateAccountResponse> response = accountApiService.updateAccountNotificationPreference(updateAccountNotificationPreference, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/update-account-supporting-doc")
    public ResponseEntity<ApiResponse> updateSupportingDocument(@RequestBody UpdateSupportingDocument updateSupportingDocument,
                                                                HttpServletRequest request) {
        try {
            CompletableFuture<UpdateAccountResponse> response = accountApiService.updateSupportingDocument(updateSupportingDocument, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/get-account-supporting-doc")
    public ResponseEntity<ApiResponse> getSupportingDocument(@RequestParam("accountNumber") String accountNumber,
                                                             @RequestParam("docType") Integer docType,
                                                             HttpServletRequest request) {
        try {
            CompletableFuture<UpdateAccountResponse> response = accountApiService.getSupportingDocument(accountNumber, docType, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/get-account-by-tracking-ref")
    public ResponseEntity<ApiResponse> getAccountByTransactionTrackingReference(@RequestParam("transactionTrackingRef") String transactionTrackingRef,
                                                                                @RequestParam("computeWithdrawalBalance") Boolean computeWithdrawalBalance,
                                                                                HttpServletRequest request) {
        try {
            CompletableFuture<AccountByTransactionReferenceResponse> response = accountApiService.getAccountByTransactionTrackingReference(transactionTrackingRef, computeWithdrawalBalance, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-account-by-tracking-ref2")
    public ResponseEntity<ApiResponse> getAccountByTransactionTrackingReference2(@RequestParam("transactionTrackingRef") String transactionTrackingRef,
                                                                                 @RequestParam("computeWithdrawalBalance") Boolean computeWithdrawalBalance,
                                                                                 HttpServletRequest request) {
        try {
            CompletableFuture<AccountByTransactionReferenceResponse> response = accountApiService.getAccountByTransactionTrackingReference2(transactionTrackingRef, computeWithdrawalBalance, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-account-by-account-number")
    public ResponseEntity<ApiResponse> getAccountByAccountNumber(@RequestParam("accountNumber") String accountNumber,
                                                                 @RequestParam("computeWithdrawalBalance") Boolean computeWithdrawalBalance,
                                                                 HttpServletRequest request) {
        try {
            CompletableFuture<AccountByTransactionReferenceResponse> response = accountApiService.getAccountByAccountNumber(accountNumber, computeWithdrawalBalance, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/get-active-saving-by-customer-id")
    public ResponseEntity<ApiResponse> getActiveSavingsAccountsByCustomerID(@RequestParam(name = "customerId") String customerId,
                                                                            HttpServletRequest request) {
        try {
            CompletableFuture<ActiveSavingResponse> response = accountApiService.getGetActiveSavingsAccountsByCustomerID(customerId, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-active-saving-by-customer-id-2")
    public ResponseEntity<ApiResponse> getActiveSavingsAccountsByCustomerID2(@RequestParam(name = "customerId") String customerId,
                                                                             HttpServletRequest request) {
        try {
            CompletableFuture<ActiveSavingResponse2> response = accountApiService.getGetActiveSavingsAccountsByCustomerID2(customerId, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-account-by-customer-id")
    public ResponseEntity<ApiResponse> getAccountsByCustomerID(@RequestParam(name = "customerId") String customerId,
                                                               HttpServletRequest request) {
        try {
            CompletableFuture<GetAccountsByCustomerIdResponse> response = accountApiService.getAccountsByCustomerID(customerId, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-quick-account")
    public ResponseEntity<ApiResponse> createQuickAccount(@RequestBody CreateCustomerAndAccount createCustomerAndAccount,
                                                          HttpServletRequest request) {
        try {
            CompletableFuture<CustomerAndAccountResponse> response = accountApiService.createQuickAccount(createCustomerAndAccount, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/onboard-customer")
    public ResponseEntity<ApiResponse> onBoardCustomer(@RequestBody OnBoardCustomerRequest onBoardCustomer,
                                                       HttpServletRequest request) {
        try {
            CompletableFuture<CustomerAndAccountResponse> response = accountApiService.onBoardCustomer(onBoardCustomer, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-new-customer-account")
    public ResponseEntity<ApiResponse> createCustomerAccount(@RequestBody CreateCustomerAndAccount createCustomerAndAccount,
                                                             HttpServletRequest request) {
        try {
            CompletableFuture<CustomerAndAccountResponse> response = accountApiService.createCustomerAccount(createCustomerAndAccount, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-account-Outstanding-Balance")
    public ResponseEntity<ApiResponse> getOutstandingBalance(@RequestParam("accountNumber") String accountNumber,
                                                             HttpServletRequest request) {
        try {
            CompletableFuture<BalanceResponse> response = accountApiService.getOutstandingBalance(accountNumber, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/add-account-to-customer")
    public ResponseEntity<ApiResponse> addAccountToCustomer(@RequestBody AddAccountToCustomerRequest addAccountToCustomerRequest,
                                                            HttpServletRequest request) {
        try {
            CompletableFuture<CustomerAndAccountResponse> response = accountApiService.addAccountToCustomer(addAccountToCustomerRequest, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-account")
    public ResponseEntity<ApiResponse> createAccount(@RequestBody CreateCustomerAndAccount createCustomerAndAccount,
                                                     HttpServletRequest request) {
        try {
            CompletableFuture<CustomerAndAccountResponse> response = accountApiService.createAccount(createCustomerAndAccount, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/retrieve-account-number")
    public ResponseEntity<ApiResponse> retrieveAccountNumber(@RequestParam("acctOpeningTrackingRef") String acctOpeningTrackingRef,
                                                             HttpServletRequest request) {
        try {
            CompletableFuture<CustomerAndAccountResponse> response = accountApiService.retrieveAccountNumber(acctOpeningTrackingRef, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/name-enquiry")
    public ResponseEntity<ApiResponse> doNameEnquiry(@RequestParam("String accountNumber") String accountNumber,
                                                     HttpServletRequest request) {
        try {
            CompletableFuture<NameEnquiryResponse> response = accountApiService.doNameEnquiry(accountNumber, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/do-balance-enquiry")
    public ResponseEntity<ApiResponse> doBalanceEnquiry(@RequestBody EnquiryRequest enquiryRequest,
                                                        HttpServletRequest request) {
        try {
            CompletableFuture<DoBalanceEnquiryResponse> response = accountApiService.doBalanceEnquiry(enquiryRequest, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/do-funds-transfer")
    public ResponseEntity<ApiResponse> doFundsTransfer(@RequestBody EnquiryRequest enquiryRequest,
                                                       HttpServletRequest request) {
        try {
            CompletableFuture<DoBalanceEnquiryResponse> response = accountApiService.doFundsTransfer(enquiryRequest, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/confirm-transaction")
    public ResponseEntity<ApiResponse> confirmTransaction(@RequestBody EnquiryRequest enquiryRequest,
                                                          HttpServletRequest request) {
        try {
            CompletableFuture<DoBalanceEnquiryResponse> response = accountApiService.confirmTransaction(enquiryRequest, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reverse-transaction")
    public ResponseEntity<ApiResponse> reverseTransaction(@RequestBody ReverseTransactionRequest reverseTransactionRequest,
                                                          HttpServletRequest request) {
        try {
            CompletableFuture<DoBalanceEnquiryResponse> response = accountApiService.reverseTransaction(reverseTransactionRequest, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}