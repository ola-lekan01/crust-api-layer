package africa.crust.service;

import africa.crust.auth.PrincipalApiUser;
import africa.crust.data.dtos.request.CreateVirtualAccountRequest;
import africa.crust.data.dtos.request.InitiatePaymentRequest;
import africa.crust.data.dtos.response.VirtualAccountResponse;
import africa.crust.exceptions.GenericException;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.concurrent.CompletableFuture;

public interface VirtualAccountService {

    CompletableFuture<VirtualAccountResponse> createVirtualAccount(PrincipalApiUser currentApiUser,
                                                                   CreateVirtualAccountRequest accountRequest,
                                                                   HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<VirtualAccountResponse> getVirtualAccountCreationStatus(PrincipalApiUser currentApiUser,
                                                                              String trackingRef,
                                                                              HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<VirtualAccountResponse> initiatePayment(PrincipalApiUser currentApiUser,
                                                              InitiatePaymentRequest initiatePayment,
                                                              HttpServletRequest servletRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<VirtualAccountResponse> verifyTransaction(PrincipalApiUser currentApiUser,
                                                                String uniqueReference,
                                                                HttpServletRequest servletRequest) throws GenericException;

}
