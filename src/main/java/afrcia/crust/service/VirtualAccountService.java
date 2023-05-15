package afrcia.crust.service;

import afrcia.crust.auth.PrincipalApiUser;
import afrcia.crust.data.dtos.request.CreateVirtualAccountRequest;
import afrcia.crust.data.dtos.request.InitiatePaymentRequest;
import afrcia.crust.data.dtos.response.VirtualAccountResponse;
import afrcia.crust.exceptions.GenericException;
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
