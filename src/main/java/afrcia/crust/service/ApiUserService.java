package afrcia.crust.service;

import afrcia.crust.auth.PrincipalApiUser;
import afrcia.crust.data.dtos.request.*;
import afrcia.crust.data.dtos.response.*;
import afrcia.crust.exceptions.GenericException;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.concurrent.CompletableFuture;

public interface ApiUserService {
    CompletableFuture<CardResponse> registerCardHolderSynchronously(PrincipalApiUser currentApiUser,
                                                                    HttpServletRequest request,
                                                                    CardHolderRegistration cardHolderRegistration) throws GenericException, JsonProcessingException;


    CompletableFuture<CardHolderDetailResponse> getCardHolderDetails(PrincipalApiUser currentApiUser,
                                                                     HttpServletRequest request,
                                                                     String cardHolderId) throws GenericException;

    CompletableFuture<CardHolderDetailResponse> deleteCardHolder(PrincipalApiUser currentApiUser,
                                                                 HttpServletRequest request,
                                                                 String cardHolderId) throws GenericException;

    CompletableFuture<CardResponse> createCard(PrincipalApiUser currentApiUser,
                                               HttpServletRequest servletRequest,
                                               CreateCardRequest createCardRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<CardResponse> activatePhysicalCard(PrincipalApiUser currentApiUser,
                                                         HttpServletRequest servletRequest,
                                                         CreateCardRequest createCardRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<CardDetailResponse> getCardDetails(PrincipalApiUser currentApiUser,
                                                         HttpServletRequest servletRequest,
                                                         String cardId) throws GenericException;

    CompletableFuture<CardBalanceResponse> getCardBalance(PrincipalApiUser currentApiUser,
                                                          HttpServletRequest servletRequest,
                                                          String cardId) throws GenericException;

    CompletableFuture<CardResponse> fundCard(PrincipalApiUser currentApiUser,
                                             HttpServletRequest servletRequest,
                                             FundCardRequest fundCardRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<CardResponse> unloadCard(PrincipalApiUser currentApiUser,
                                               HttpServletRequest servletRequest,
                                               FundCardRequest fundCardRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<CardTransactionsResponse> getCardTransactions(PrincipalApiUser currentApiUser,
                                                                    HttpServletRequest servletRequest,
                                                                    String cardId) throws GenericException;

    CompletableFuture<CardTransactionsResponse> getCardTransaction(PrincipalApiUser currentApiUser,
                                                                   HttpServletRequest servletRequest,
                                                                   String cardId,
                                                                   String clientTransactionReference) throws GenericException;

    CompletableFuture<CardResponse> freezeCard(PrincipalApiUser currentApiUser,
                                               HttpServletRequest servletRequest,
                                               String cardId) throws GenericException;

    CompletableFuture<CardResponse> unFreezeCard(PrincipalApiUser currentApiUser,
                                                 HttpServletRequest servletRequest,
                                                 String cardId) throws GenericException;


    CompletableFuture<CardHolderCardResponse> getCardHolderCardsDetails(PrincipalApiUser currentApiUser,
                                                                        HttpServletRequest servletRequest,
                                                                        String cardHolderId) throws GenericException;

    CompletableFuture<CardResponse> createNairaVirtualAccount(PrincipalApiUser currentApiUser,
                                                              HttpServletRequest servletRequest,
                                                              String cardHolderId) throws GenericException;

    CompletableFuture<CardBalanceResponse> getNairaCardBalance(PrincipalApiUser currentApiUser,
                                                               HttpServletRequest servletRequest,
                                                               String cardHolderId) throws GenericException;

    CompletableFuture<GetAllBanksCode> getAllBanksCode(PrincipalApiUser currentApiUser,
                                                       HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<CardResponse> verifyAccountNumber(PrincipalApiUser currentApiUser,
                                                        HttpServletRequest servletRequest,
                                                        AccountNumberVerificationRequest verificationRequest) throws GenericException;

    CompletableFuture<CardTransactionsResponse> getNairaCardTransactions(PrincipalApiUser currentApiUser,
                                                                         HttpServletRequest servletRequest,
                                                                         String cardHolderId) throws GenericException;

    CompletableFuture<TransferResponse> thirdPartyTransfer(PrincipalApiUser currentApiUser,
                                        HttpServletRequest servletRequest,
                                        ThirdPartyTransferRequest thirdPartyTransferRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<TransferResponse> sameBankTransfer(PrincipalApiUser currentApiUser,
                                      HttpServletRequest servletRequest,
                                      SameBankTransferRequest sameBankTransfer) throws GenericException, JsonProcessingException;

    CompletableFuture<AllCardholdersResponse >getAllCreatedCardHolders(PrincipalApiUser currentApiUser,
                                                    HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<IssuedCardResponse> getAllIssuedCard(PrincipalApiUser currentApiUser,
                                        HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<IssuingWalletResponse> fundIssuingWallet(PrincipalApiUser currentApiUser,
                                            HttpServletRequest servletRequest,
                                            String amount) throws GenericException, JsonProcessingException;

    CompletableFuture<IssuingWalletResponse> getIssuingWalletBalance(PrincipalApiUser currentApiUser,
                                                  HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<FXRateExchange> getFXRateExchange(PrincipalApiUser currentApiUser,
                                     HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<AllStatesResponse> getAllStates(PrincipalApiUser currentApiUser,
                                   HttpServletRequest servletRequest) throws GenericException;
}