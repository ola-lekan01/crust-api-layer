package afrcia.crust.controller;

import afrcia.crust.auth.PrincipalApiUser;
import afrcia.crust.auth.annotation.CurrentApiUser;
import afrcia.crust.data.dtos.request.*;
import afrcia.crust.data.dtos.response.*;
import afrcia.crust.exceptions.GenericException;
import afrcia.crust.service.ApiUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/v1/crust/card")
@RequiredArgsConstructor
@Tag(name = "Crust Card Api Management", description = "All the APIs to issue and managing Cards")
@SecurityRequirement(name = "SECRET_KEY")
@SecurityRequirement(name = "BearerAuth")
public class CardController {

    private final ApiUserService apiUserService;

    @PostMapping("/create-card")
    @Operation(summary = "Use this endpoint to create a card for a verified cardholder.")
    public ResponseEntity<ApiResponse> createCard(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                  HttpServletRequest request,
                                                  @RequestBody @Valid CreateCardRequest cardRequest) {
        try {
            CompletableFuture<CardResponse> response = apiUserService.createCard(currentApiUser, request, cardRequest);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/activate-physical-card")
    @Operation(summary = "Use this endpoint to activate a physical card for a verified cardholder.")
    public ResponseEntity<ApiResponse> activatePhysicalCard(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                            HttpServletRequest request,
                                                            @RequestBody @Valid CreateCardRequest cardRequest) {
        try {
            CompletableFuture<CardResponse> response = apiUserService.activatePhysicalCard(currentApiUser, request, cardRequest);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_card_details/{cardId}")
    @Operation(summary = "Use this endpoint to fetch the details for a card created.")
    public ResponseEntity<ApiResponse> getCardDetails(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                      HttpServletRequest request,
                                                      @PathVariable("cardId") String cardId) {
        try {
            CompletableFuture<CardDetailResponse> response = apiUserService.getCardDetails(currentApiUser, request, cardId);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_card_balance/{cardId}")
    @Operation(summary = "Use this endpoint to fetch the balance for a card created.")
    public ResponseEntity<ApiResponse> getCardBalance(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                      HttpServletRequest request,
                                                      @PathVariable("cardId") String cardId) {
        try {
            CompletableFuture<CardBalanceResponse> response = apiUserService.getCardBalance(currentApiUser, request, cardId);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/fund_card")
    public ResponseEntity<ApiResponse> fundCard(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                HttpServletRequest request,
                                                @RequestBody @Valid FundCardRequest fundRequest) {
        try {
            CompletableFuture<CardResponse> response = apiUserService.fundCard(currentApiUser, request, fundRequest);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/unload_card")
    public ResponseEntity<ApiResponse> unloadCard(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                  HttpServletRequest request,
                                                  @RequestBody @Valid FundCardRequest fundRequest) {
        try {
            CompletableFuture<CardResponse> response = apiUserService.unloadCard(currentApiUser, request, fundRequest);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_card_transactions/{cardId}")
    public ResponseEntity<ApiResponse> getCardTransactions(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                           HttpServletRequest request,
                                                           @PathVariable("cardId") String cardId) {
        try {
            CompletableFuture<CardTransactionsResponse> response = apiUserService.getCardTransactions(currentApiUser, request, cardId);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_card_transaction")
    public ResponseEntity<ApiResponse> getCardTransaction(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                          HttpServletRequest request,
                                                          @RequestParam(name = "cardId") String cardId,
                                                          @RequestParam(name = "clientTransactionReference") String clientTransactionReference) {
        try {
            CompletableFuture<CardTransactionsResponse> response = apiUserService.getCardTransaction(currentApiUser, request, cardId, clientTransactionReference);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get()));
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/freeze_card/{cardId}")
    public ResponseEntity<ApiResponse> freezeCard(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                  HttpServletRequest request,
                                                  @PathVariable("cardId") String cardId) {
        try {
            CompletableFuture<CardResponse> response = apiUserService.freezeCard(currentApiUser, request, cardId);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/unfreeze_card/{cardId}")
    public ResponseEntity<ApiResponse> unfreezeCard(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                    HttpServletRequest request,
                                                    @PathVariable("cardId") String cardId) {
        try {
            CompletableFuture<CardResponse> response = apiUserService.unFreezeCard(currentApiUser, request, cardId);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_card_holder_cards")
    public ResponseEntity<ApiResponse> getCardHolderCards(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                          HttpServletRequest request,
                                                          @RequestParam("cardHolderId") String cardHolderId) {
        try {
            CompletableFuture<CardHolderCardResponse> response = apiUserService.getCardHolderCardsDetails(currentApiUser, request, cardHolderId);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    //Card Holders Endpoints

    @PostMapping("/register")
    @Operation(summary = "Use this endpoint to register your user as a cardholder on our platform.",
            description = "The cardholders ID verification is done on the fly so the response might take about " +
                    "30 seconds so you might want to increase your request timeout secs. ")
    public ResponseEntity<ApiResponse> registerCardHolderSynchronously(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                                       HttpServletRequest request,
                                                                       @RequestBody @Valid CardHolderRegistration cardHolderRegistration) {
        try {
            CompletableFuture<CardResponse> response = apiUserService.registerCardHolderSynchronously(currentApiUser, request, cardHolderRegistration);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));

        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_cardholder/{cardholderId}")
    @Operation(summary = "Use this API to view a cardholder's profile.")
    public ResponseEntity<ApiResponse> getCardHolderDetails(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                            @PathVariable("cardholderId") String cardHolderId,
                                                            HttpServletRequest request) {
        try {
            CompletableFuture<CardHolderDetailResponse> response = apiUserService.getCardHolderDetails(currentApiUser, request, cardHolderId);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage().substring(42)), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete_cardholder/{cardholderId}")
    @Operation(summary = "Use this API to delete a cardholder's profile.")
    public ResponseEntity<ApiResponse> deleteCardHolder(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                        @PathVariable("cardholderId") String cardHolderId,
                                                        HttpServletRequest request) {
        try {
            CompletableFuture<CardHolderDetailResponse> response = apiUserService.deleteCardHolder(currentApiUser, request, cardHolderId);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    // Naira Cards Controller

    @PostMapping("/create-naira-virtual-account")
    public ResponseEntity<ApiResponse> createNairaVirtualAccount(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                                 HttpServletRequest request,
                                                                 @RequestParam("cardHolderId") String cardHolderId) {
        try {
            CompletableFuture<CardResponse> response = apiUserService.createNairaVirtualAccount(currentApiUser, request, cardHolderId);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/get-naira-card-balance")
    public ResponseEntity<ApiResponse> getNairaCardBalance(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                           HttpServletRequest request,
                                                           @RequestParam("cardHolderId") String cardHolderId) {
        try {
            CompletableFuture<CardBalanceResponse> response = apiUserService.getNairaCardBalance(currentApiUser, request, cardHolderId);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_all_banks_code")
    public ResponseEntity<ApiResponse> getAllBankCode(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                      HttpServletRequest request) {
        try {
            CompletableFuture<GetAllBanksCode> response = apiUserService.getAllBanksCode(currentApiUser, request);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/verify-account-number")
    public ResponseEntity<ApiResponse> verifyAccountNumber(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                           HttpServletRequest request,
                                                           @RequestBody @Valid AccountNumberVerificationRequest verificationRequest) {
        try {
            CompletableFuture<CardResponse> response = apiUserService.verifyAccountNumber(currentApiUser, request, verificationRequest);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_naira_card_transactions/{cardHolderId}")
    public ResponseEntity<ApiResponse> getNairaCardTransactions(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                                HttpServletRequest request,
                                                                @PathVariable("cardHolderId") String cardHolderId) {
        try {
            CompletableFuture<CardTransactionsResponse> response = apiUserService.getNairaCardTransactions(currentApiUser, request, cardHolderId);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/third-party-transfer")
    public ResponseEntity<ApiResponse> thirdPartyTransfer(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                          HttpServletRequest request,
                                                          @RequestBody ThirdPartyTransferRequest transferRequest) {
        try {
            CompletableFuture<TransferResponse> response = apiUserService.thirdPartyTransfer(currentApiUser, request, transferRequest);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException  exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/same-bank-transfer")
    public ResponseEntity<ApiResponse> sameBankTransfer(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                        HttpServletRequest request,
                                                        @RequestBody SameBankTransferRequest transferRequest) {
        try {
            CompletableFuture<TransferResponse> response = apiUserService.sameBankTransfer(currentApiUser, request, transferRequest);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    // Misc Cards Endpoints

    @GetMapping("/get_all_created_card_holders")
    public ResponseEntity<ApiResponse> getAllCardHolders(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                         HttpServletRequest request) {
        try {
            CompletableFuture<AllCardholdersResponse> response = apiUserService.getAllCreatedCardHolders(currentApiUser, request);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_all_issued_cards")
    public ResponseEntity<ApiResponse> getAllIssuedCard(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                        HttpServletRequest request) {
        try {
            CompletableFuture<IssuedCardResponse> response = apiUserService.getAllIssuedCard(currentApiUser, request);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/fund_issued_wallet")
    public ResponseEntity<ApiResponse> fundIssuedWallet(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                        HttpServletRequest request,
                                                        @RequestParam(name = "amount") String amount) {
        try {
            CompletableFuture<IssuingWalletResponse> response = apiUserService.fundIssuingWallet(currentApiUser, request, amount);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException | JsonProcessingException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_issuing_wallet_balance")
    public ResponseEntity<ApiResponse> getIssuingWallet(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                        HttpServletRequest request) {
        try {
            CompletableFuture<IssuingWalletResponse> response = apiUserService.getIssuingWalletBalance(currentApiUser, request);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_fx_rate")
    public ResponseEntity<ApiResponse> getFxRate(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                 HttpServletRequest request) {
        try {
            CompletableFuture<FXRateExchange> response = apiUserService.getFXRateExchange(currentApiUser, request);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_all_states")
    public ResponseEntity<ApiResponse> getAllStates(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                    HttpServletRequest request) {
        try {
            CompletableFuture<AllStatesResponse> response = apiUserService.getAllStates(currentApiUser, request);
            return ResponseEntity.ok(new ApiResponse(true, response.get().getMessage(),
                    request.getRequestURL().toString(), response.get().getData()));
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}