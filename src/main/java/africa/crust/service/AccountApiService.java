package africa.crust.service;

import africa.crust.data.dtos.request.*;
import africa.crust.data.dtos.response.*;
import africa.crust.exceptions.GenericException;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.concurrent.CompletableFuture;

public interface AccountApiService {
    CompletableFuture<CreateAccountResponse> createGswAccount(String data,
                                                              HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<CreateAccountResponse> createGsaAccount(String data,
                                                              HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<CustomerAndAccountResponse> createCustomerAndAccount(CreateCustomerAndAccount createCustomerAndAccount,
                                                                           HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<CustomerAndAccountResponse> createCustomerAndAccountT3(CreateCustomerAndAccount createCustomerAndAccount,
                                                                             HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<UpdateAccountResponse> updateAccountTier(UpdateAccountTierRequest updateAccountTierRequest,
                                                               HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;


    CompletableFuture<UpdateAccountResponse> updateAccountTier2(UpdateAccountTierRequest updateAccountTierRequest,
                                                                HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<UpdateAccountResponse> updateAccountNotificationPreference(UpdateAccountNotificationPreference updateAccountNotificationPreference,
                                                                                 HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<UpdateAccountResponse> updateSupportingDocument(UpdateSupportingDocument updateSupportingDocument,
                                                                      HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<UpdateAccountResponse> getSupportingDocument(String accountNumber, Integer docType,
                                                                   HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<AccountByTransactionReferenceResponse> getAccountByTransactionTrackingReference(String transactionTrackingRef,
                                                                                                      Boolean computeWithdrawalBalance,
                                                                                                      HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<AccountByTransactionReferenceResponse> getAccountByTransactionTrackingReference2(String transactionTrackingRef,
                                                                                                       Boolean computeWithdrawalBalance,
                                                                                                       HttpServletRequest servletRequest) throws GenericException;


    CompletableFuture<AccountByTransactionReferenceResponse> getAccountByAccountNumber(String accountNumber,
                                                                                       Boolean computeWithdrawalBalance,
                                                                                       HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<ActiveSavingResponse> getGetActiveSavingsAccountsByCustomerID(String customerId,
                                                                                    HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<ActiveSavingResponse2> getGetActiveSavingsAccountsByCustomerID2(String customerId,
                                                                                      HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<GetAccountsByCustomerIdResponse> getAccountsByCustomerID(String customerId,
                                                                               HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<CustomerAndAccountResponse> createQuickAccount(CreateCustomerAndAccount createCustomerAndAccount,
                                                                     HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<CustomerAndAccountResponse> onBoardCustomer(OnBoardCustomerRequest onBoardCustomer,
                                                                  HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<CustomerAndAccountResponse> createCustomerAccount(CreateCustomerAndAccount createCustomerAndAccount,
                                                                        HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    public CompletableFuture<BalanceResponse> getOutstandingBalance(String accountNumber,
                                                                    HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<CustomerAndAccountResponse> addAccountToCustomer(AddAccountToCustomerRequest addAccountToCustomerRequest,
                                                                       HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<CustomerAndAccountResponse> createAccount(CreateCustomerAndAccount createCustomerAndAccount,
                                                                HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<CustomerAndAccountResponse> retrieveAccountNumber(String acctOpeningTrackingRef,
                                                                        HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<NameEnquiryResponse> doNameEnquiry(String accountNumber,
                                                         HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<DoBalanceEnquiryResponse> doBalanceEnquiry(EnquiryRequest enquiryRequest,
                                                                 HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<DoBalanceEnquiryResponse> doFundsTransfer(EnquiryRequest enquiryRequest,
                                                                HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<DoBalanceEnquiryResponse> confirmTransaction(EnquiryRequest enquiryRequest,
                                                                   HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<DoBalanceEnquiryResponse> reverseTransaction(ReverseTransactionRequest reverseTransactionRequest,
                                                                   HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;
}