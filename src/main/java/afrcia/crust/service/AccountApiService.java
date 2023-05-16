package afrcia.crust.service;

import afrcia.crust.auth.PrincipalApiUser;
import afrcia.crust.data.dtos.request.*;
import afrcia.crust.data.dtos.response.*;
import afrcia.crust.exceptions.GenericException;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.concurrent.CompletableFuture;

public interface AccountApiService {
    CompletableFuture<CreateAccountResponse> createGswAccount(PrincipalApiUser currentApiUser,
                                                              String data,
                                                              HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<CreateAccountResponse> createGsaAccount(PrincipalApiUser currentApiUser,
                                                              String data,
                                                              HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<CustomerAndAccountResponse> createCustomerAndAccount(PrincipalApiUser currentApiUser,
                                                                           CreateCustomerAndAccount createCustomerAndAccount,
                                                                           HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<CustomerAndAccountResponse> createCustomerAndAccountT3(PrincipalApiUser currentApiUser,
                                                                             CreateCustomerAndAccount createCustomerAndAccount,
                                                                             HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<UpdateAccountResponse> updateAccountTier(PrincipalApiUser currentApiUser,
                                                               UpdateAccountTierRequest updateAccountTierRequest,
                                                               HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;


    CompletableFuture<UpdateAccountResponse> updateAccountTier2(PrincipalApiUser currentApiUser,
                                                                UpdateAccountTierRequest updateAccountTierRequest,
                                                                HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<UpdateAccountResponse> updateAccountNotificationPreference(PrincipalApiUser currentApiUser,
                                                                                 UpdateAccountNotificationPreference updateAccountNotificationPreference,
                                                                                 HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<UpdateAccountResponse> updateSupportingDocument(PrincipalApiUser currentApiUser,
                                                                      UpdateSupportingDocument updateSupportingDocument,
                                                                      HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<UpdateAccountResponse> getSupportingDocument(PrincipalApiUser currentApiUser,
                                                                   String accountNumber,
                                                                   Integer docType,
                                                                   HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<AccountByTransactionReferenceResponse> getAccountByTransactionTrackingReference(PrincipalApiUser currentApiUser,
                                                                                                      String transactionTrackingRef,
                                                                                                      Boolean computeWithdrawalBalance,
                                                                                                      HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<AccountByTransactionReferenceResponse> getAccountByTransactionTrackingReference2(PrincipalApiUser currentApiUser,
                                                                                                       String transactionTrackingRef,
                                                                                                       Boolean computeWithdrawalBalance,
                                                                                                       HttpServletRequest servletRequest) throws GenericException;


    CompletableFuture<AccountByTransactionReferenceResponse> getAccountByAccountNumber(PrincipalApiUser currentApiUser,
                                                                                       String accountNumber,
                                                                                       Boolean computeWithdrawalBalance,
                                                                                       HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<ActiveSavingResponse> getGetActiveSavingsAccountsByCustomerID(PrincipalApiUser currentApiUser,
                                                                                    String customerId,
                                                                                    HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<ActiveSavingResponse2> getGetActiveSavingsAccountsByCustomerID2(PrincipalApiUser currentApiUser,
                                                                                      String customerId,
                                                                                      HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<GetAccountsByCustomerIdResponse> getAccountsByCustomerID(PrincipalApiUser currentApiUser,
                                                                               String customerId,
                                                                               HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<CustomerAndAccountResponse> createQuickAccount(PrincipalApiUser currentApiUser,
                                                                     CreateCustomerAndAccount createCustomerAndAccount,
                                                                     HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<CustomerAndAccountResponse> onBoardCustomer(PrincipalApiUser currentApiUser,
                                                                  OnBoardCustomerRequest onBoardCustomer,
                                                                  HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<CustomerAndAccountResponse> createCustomerAccount(PrincipalApiUser currentApiUser,
                                                                        CreateCustomerAndAccount createCustomerAndAccount,
                                                                        HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    public CompletableFuture<BalanceResponse> getOutstandingBalance(PrincipalApiUser currentApiUser,
                                                                    String accountNumber,
                                                                    HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<CustomerAndAccountResponse> addAccountToCustomer(PrincipalApiUser currentApiUser,
                                                                       AddAccountToCustomerRequest addAccountToCustomerRequest,
                                                                       HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<CustomerAndAccountResponse> createAccount(PrincipalApiUser currentApiUser,
                                                                CreateCustomerAndAccount createCustomerAndAccount,
                                                                HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<CustomerAndAccountResponse> retrieveAccountNumber(PrincipalApiUser currentApiUser,
                                                                        String acctOpeningTrackingRef,
                                                                        HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<NameEnquiryResponse> doNameEnquiry(PrincipalApiUser currentApiUser,
                                                         String accountNumber,
                                                         HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<DoBalanceEnquiryResponse> doBalanceEnquiry(PrincipalApiUser currentApiUser,
                                                                 EnquiryRequest enquiryRequest,
                                                                 HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<DoBalanceEnquiryResponse> doFundsTransfer(PrincipalApiUser currentApiUser,
                                                                EnquiryRequest enquiryRequest,
                                                                HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<DoBalanceEnquiryResponse> confirmTransaction(PrincipalApiUser currentApiUser,
                                                                   EnquiryRequest enquiryRequest,
                                                                   HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<DoBalanceEnquiryResponse> reverseTransaction(PrincipalApiUser currentApiUser,
                                                                   ReverseTransactionRequest reverseTransactionRequest,
                                                                   HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;
}
