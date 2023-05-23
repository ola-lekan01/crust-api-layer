package africa.crust.service;

import africa.crust.data.dtos.request.*;
import africa.crust.data.dtos.response.*;
import africa.crust.exceptions.GenericException;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.concurrent.CompletableFuture;

public interface ApiUserService {
    CompletableFuture<CardResponse> registerCardHolderSynchronously(HttpServletRequest request,
                                                                    CardHolderRegistration cardHolderRegistration) throws GenericException, JsonProcessingException;


    CompletableFuture<CardHolderDetailResponse> getCardHolderDetails(HttpServletRequest request,
                                                                     String cardHolderId) throws GenericException;

    CompletableFuture<CardHolderDetailResponse> deleteCardHolder(HttpServletRequest request,
                                                                 String cardHolderId) throws GenericException;

    CompletableFuture<CardResponse> createCard(HttpServletRequest servletRequest,
                                               CreateCardRequest createCardRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<CardResponse> activatePhysicalCard(HttpServletRequest servletRequest,
                                                         CreateCardRequest createCardRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<CardDetailResponse> getCardDetails(HttpServletRequest servletRequest,
                                                         String cardId) throws GenericException;

    CompletableFuture<CardBalanceResponse> getCardBalance(HttpServletRequest servletRequest,
                                                          String cardId) throws GenericException;

    CompletableFuture<CardResponse> fundCard(HttpServletRequest servletRequest,
                                             FundCardRequest fundCardRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<CardResponse> unloadCard(HttpServletRequest servletRequest,
                                               FundCardRequest fundCardRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<CardTransactionsResponse> getCardTransactions(HttpServletRequest servletRequest,
                                                                    String cardId) throws GenericException;

    CompletableFuture<CardTransactionsResponse> getCardTransaction(HttpServletRequest servletRequest,
                                                                   String cardId,
                                                                   String clientTransactionReference) throws GenericException;

    CompletableFuture<CardResponse> freezeCard(HttpServletRequest servletRequest,
                                               String cardId) throws GenericException;

    CompletableFuture<CardResponse> unFreezeCard(HttpServletRequest servletRequest, String cardId) throws GenericException;


    CompletableFuture<CardHolderCardResponse> getCardHolderCardsDetails(HttpServletRequest servletRequest,
                                                                        String cardHolderId) throws GenericException;

    CompletableFuture<CardResponse> createNairaVirtualAccount(HttpServletRequest servletRequest,
                                                              String cardHolderId) throws GenericException;

    CompletableFuture<CardBalanceResponse> getNairaCardBalance(HttpServletRequest servletRequest,
                                                               String cardHolderId) throws GenericException;

    CompletableFuture<GetAllBanksCode> getAllBanksCode(HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<CardResponse> verifyAccountNumber(HttpServletRequest servletRequest,
                                                        AccountNumberVerificationRequest verificationRequest) throws GenericException;

    CompletableFuture<CardTransactionsResponse> getNairaCardTransactions(HttpServletRequest servletRequest,
                                                                         String cardHolderId) throws GenericException;

    CompletableFuture<TransferResponse> thirdPartyTransfer(HttpServletRequest servletRequest,
                                                           ThirdPartyTransferRequest thirdPartyTransferRequest) throws GenericException, JsonProcessingException;

    CompletableFuture<TransferResponse> sameBankTransfer(HttpServletRequest servletRequest,
                                      SameBankTransferRequest sameBankTransfer) throws GenericException, JsonProcessingException;

    CompletableFuture<AllCardholdersResponse>getAllCreatedCardHolders(HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<IssuedCardResponse> getAllIssuedCard(HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<IssuingWalletResponse> fundIssuingWallet(HttpServletRequest servletRequest,
                                                               String amount) throws GenericException, JsonProcessingException;

    CompletableFuture<IssuingWalletResponse> getIssuingWalletBalance(HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<FXRateExchange> getFXRateExchange(HttpServletRequest servletRequest) throws GenericException;

    CompletableFuture<AllStatesResponse> getAllStates(HttpServletRequest servletRequest) throws GenericException;
}