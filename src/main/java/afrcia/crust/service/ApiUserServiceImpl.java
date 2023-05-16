package afrcia.crust.service;

import afrcia.crust.auth.PrincipalApiUser;
import afrcia.crust.data.dtos.request.*;
import afrcia.crust.data.dtos.response.*;
import afrcia.crust.exceptions.GenericException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiUserServiceImpl implements ApiUserService {

    private final ObjectMapper mapper;
    private final String secretKey = System.getenv("CARD_SECRET_KEY");

    private final AccessService accessService;
    private final OkHttpClient client = new OkHttpClient();
    private final String baseUrl = "https://issuecards.api.bridgecard.co";

    @Override
    public CompletableFuture<CardResponse> registerCardHolderSynchronously(PrincipalApiUser currentApiUser,
                                                                           HttpServletRequest servletRequest,
                                                                           CardHolderRegistration cardHolderRegistration) throws GenericException, JsonProcessingException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<CardResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/cardholder/register_cardholder_synchronously")).newBuilder();

        String createAccountRequest = mapper.writeValueAsString(cardHolderRegistration);

        RequestBody body = RequestBody.create(createAccountRequest, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .header("token", "Bearer " + secretKey)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CardResponse apiCallResponse = mapper.readValue(responseBodyString, CardResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<CardHolderDetailResponse> getCardHolderDetails(PrincipalApiUser currentApiUser,
                                                                            HttpServletRequest servletRequest,
                                                                            String cardHolderId) throws GenericException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<CardHolderDetailResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(
                HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/cardholder/get_cardholder?cardholder_id=" + cardHolderId)).newBuilder();

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("token", "Bearer " + secretKey)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CardHolderDetailResponse apiCallResponse = mapper.readValue(responseBodyString, CardHolderDetailResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<CardHolderDetailResponse> deleteCardHolder(PrincipalApiUser currentApiUser,
                                                                        HttpServletRequest servletRequest,
                                                                        String cardHolderId) throws GenericException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<CardHolderDetailResponse> future = new CompletableFuture<>();


        HttpUrl.Builder urlBuilder = Objects.requireNonNull(
                HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/cardholder/delete_cardholder/" + cardHolderId)).newBuilder();
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .delete()
                .header("token", "Bearer " + secretKey)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CardHolderDetailResponse apiCallResponse = mapper.readValue(responseBodyString, CardHolderDetailResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<CardResponse> createCard(PrincipalApiUser currentApiUser,
                                                      HttpServletRequest servletRequest,
                                                      CreateCardRequest createCardRequest) throws GenericException, JsonProcessingException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<CardResponse> future = new CompletableFuture<>();

        String createAccountRequest = mapper.writeValueAsString(createCardRequest);
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(
                HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/cards/create_card")).newBuilder();

        RequestBody body = RequestBody.create(createAccountRequest, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .header("token", "Bearer " + secretKey)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CardResponse apiCallResponse = mapper.readValue(responseBodyString, CardResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<CardResponse> activatePhysicalCard(PrincipalApiUser currentApiUser,
                                                                HttpServletRequest servletRequest,
                                                                CreateCardRequest createCardRequest) throws GenericException, JsonProcessingException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<CardResponse> future = new CompletableFuture<>();

        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String createRequest = mapper.writeValueAsString(createCardRequest);
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/cards/activate_physical_card")).newBuilder();

        RequestBody body = RequestBody.create(createRequest, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .header("token", "Bearer " + secretKey)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    log.info(responseBodyString);
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CardResponse apiCallResponse = mapper.readValue(responseBodyString, CardResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }


    @Override
    public CompletableFuture<CardDetailResponse> getCardDetails(PrincipalApiUser currentApiUser,
                                                                HttpServletRequest servletRequest,
                                                                String cardId) throws GenericException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<CardDetailResponse> future = new CompletableFuture<>();


        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse("https://issuecards-api-bridgecard-co.relay.evervault.com/v1/issuing/sandbox/cards/get_card_details?card_id=" + cardId)).newBuilder();

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("token", "Bearer " + secretKey)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CardDetailResponse apiCallResponse = mapper.readValue(responseBodyString, CardDetailResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<CardBalanceResponse> getCardBalance(PrincipalApiUser currentApiUser,
                                                                 HttpServletRequest servletRequest,
                                                                 String cardId) throws GenericException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<CardBalanceResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(
                HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/cards/get_card_balance?card_id=" + cardId)).newBuilder();
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("token", "Bearer " + secretKey)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CardBalanceResponse apiCallResponse = mapper.readValue(responseBodyString, CardBalanceResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<CardResponse> fundCard(PrincipalApiUser currentApiUser,
                                                    HttpServletRequest servletRequest,
                                                    FundCardRequest fundCardRequest) throws GenericException, JsonProcessingException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<CardResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/cards/fund_card")).newBuilder();

        fundCardRequest.setTransaction_reference(UUID.randomUUID().toString());
        String createAccountRequest = mapper.writeValueAsString(fundCardRequest);

        RequestBody body = RequestBody.create(createAccountRequest, MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .header("token", "Bearer " + secretKey)
                .patch(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CardResponse apiCallResponse = mapper.readValue(responseBodyString, CardResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<CardResponse> unloadCard(PrincipalApiUser currentApiUser,
                                                      HttpServletRequest servletRequest,
                                                      FundCardRequest fundCardRequest) throws GenericException, JsonProcessingException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<CardResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/cards/unload_card")).newBuilder();

        fundCardRequest.setTransaction_reference(UUID.randomUUID().toString());
        String createAccountRequest = mapper.writeValueAsString(fundCardRequest);

        RequestBody body = RequestBody.create(createAccountRequest, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .header("token", "Bearer " + secretKey)
                .patch(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CardResponse apiCallResponse = mapper.readValue(responseBodyString, CardResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<CardTransactionsResponse> getCardTransactions(PrincipalApiUser currentApiUser,
                                                                           HttpServletRequest servletRequest,
                                                                           String cardId) throws GenericException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<CardTransactionsResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/cards/get_card_transactions?card_id=" + cardId + "&page=1")).newBuilder();

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("token", "Bearer " + secretKey)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CardTransactionsResponse apiCallResponse = mapper.readValue(responseBodyString, CardTransactionsResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<CardTransactionsResponse> getCardTransaction(PrincipalApiUser currentApiUser,
                                                                          HttpServletRequest servletRequest,
                                                                          String cardId, String clientTransactionReference) throws GenericException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<CardTransactionsResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder =
                Objects.requireNonNull(HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/cards/get_card_transaction_by_id?card_id=" + cardId + "&client_transaction_reference=" + clientTransactionReference)).newBuilder();

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("token", "Bearer " + secretKey)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CardTransactionsResponse apiCallResponse = mapper.readValue(responseBodyString, CardTransactionsResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<CardResponse> freezeCard(PrincipalApiUser currentApiUser,
                                                      HttpServletRequest servletRequest,
                                                      String cardId) throws GenericException {
        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<CardResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder =
                Objects.requireNonNull(HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/cards/freeze_card?card_id=" + cardId)).newBuilder();

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .patch(new FormBody.Builder().build())
                .header("token", "Bearer " + secretKey)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CardResponse apiCallResponse = mapper.readValue(responseBodyString, CardResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<CardResponse> unFreezeCard(PrincipalApiUser currentApiUser,
                                                        HttpServletRequest servletRequest,
                                                        String cardId) throws GenericException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<CardResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder =
                Objects.requireNonNull(HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/cards/unfreeze_card?card_id=" + cardId)).newBuilder();

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .patch(new FormBody.Builder().build())
                .header("token", "Bearer " + secretKey)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CardResponse apiCallResponse = mapper.readValue(responseBodyString, CardResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<CardHolderCardResponse> getCardHolderCardsDetails(PrincipalApiUser currentApiUser,
                                                                               HttpServletRequest servletRequest,
                                                                               String cardHolderId) throws GenericException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<CardHolderCardResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(
                HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/cards/get_all_cardholder_cards?cardholder_id=" + cardHolderId)).newBuilder();

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .header("token", "Bearer " + secretKey)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    log.info(responseBodyString);
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CardHolderCardResponse apiCallResponse = mapper.readValue(responseBodyString, CardHolderCardResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    // Naira Cards Endpoint

    @Override
    public CompletableFuture<CardResponse> createNairaVirtualAccount(PrincipalApiUser currentApiUser,
                                                                     HttpServletRequest servletRequest,
                                                                     String cardHolderId) throws GenericException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<CardResponse> future = new CompletableFuture<>();

        JSONObject cardRequest = new JSONObject();
        cardRequest.put("cardholder_id", cardHolderId);

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/naira_cards/create_naira_virtual_account")).newBuilder();

        RequestBody body = RequestBody.create(cardRequest.toString(), MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .header("token", "Bearer " + secretKey)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CardResponse apiCallResponse = mapper.readValue(responseBodyString, CardResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    public CompletableFuture<CardBalanceResponse> getNairaCardBalance(PrincipalApiUser currentApiUser,
                                                                      HttpServletRequest servletRequest,
                                                                      String cardHolderId) throws GenericException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<CardBalanceResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(
                "https://issuecards.api.bridgecard.co/v1/issuing/sandbox/naira_cards/check_account_balance?cardholder_id=" + cardHolderId)).newBuilder();

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .header("token", "Bearer " + secretKey)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CardBalanceResponse apiCallResponse = mapper.readValue(responseBodyString, CardBalanceResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }


    @Override
    public CompletableFuture<GetAllBanksCode> getAllBanksCode(PrincipalApiUser currentApiUser,
                                                              HttpServletRequest servletRequest) throws GenericException {
        CompletableFuture<GetAllBanksCode> future = new CompletableFuture<>();

        isCurrentUserValid(currentApiUser, servletRequest);

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/naira_cards/get_all_bank_codes")).newBuilder();
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .header("token", "Bearer " + secretKey)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        GetAllBanksCode apiCallResponse = mapper.readValue(responseBodyString, GetAllBanksCode.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<CardResponse> verifyAccountNumber(PrincipalApiUser currentApiUser,
                                                               HttpServletRequest servletRequest,
                                                               AccountNumberVerificationRequest verificationRequest) throws GenericException {
        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<CardResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/naira_" +
                "cards/verify_account_number?account_number=" + verificationRequest.getAccountNumber()
                + "&bank_code=" + verificationRequest.getBankCode())).newBuilder();

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .header("token", "Bearer " + secretKey)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CardResponse apiCallResponse = mapper.readValue(responseBodyString, CardResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<CardTransactionsResponse> getNairaCardTransactions(PrincipalApiUser currentApiUser,
                                                                                HttpServletRequest servletRequest,
                                                                                String cardHolderId) throws GenericException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<CardTransactionsResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/" +
                "naira_cards/get_naira_account_transactions?cardholder_id=" + cardHolderId + "&page=1")).newBuilder();

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .header("token", "Bearer " + secretKey)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CardTransactionsResponse apiCallResponse = mapper.readValue(responseBodyString, CardTransactionsResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });

        return future;

    }

    @Override
    public CompletableFuture<TransferResponse> thirdPartyTransfer(PrincipalApiUser currentApiUser,
                                                                  HttpServletRequest servletRequest,
                                                                  ThirdPartyTransferRequest thirdPartyTransferRequest) throws GenericException, JsonProcessingException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<TransferResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/naira_cards/third_party_transfer")).newBuilder();

        String createAccountRequest = mapper.writeValueAsString(thirdPartyTransferRequest);

        RequestBody body = RequestBody.create(createAccountRequest, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .header("token", "Bearer " + secretKey)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        TransferResponse apiCallResponse = mapper.readValue(responseBodyString, TransferResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<TransferResponse> sameBankTransfer(PrincipalApiUser currentApiUser,
                                                                HttpServletRequest servletRequest,
                                                                SameBankTransferRequest sameBankTransfer) throws GenericException, JsonProcessingException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<TransferResponse> future = new CompletableFuture<>();
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/naira_cards/providus_account_transfer")).newBuilder();

        String createHttpRequest = mapper.writeValueAsString(sameBankTransfer);

        RequestBody body = RequestBody.create(createHttpRequest, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .header("token", "Bearer " + secretKey)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        TransferResponse apiCallResponse = mapper.readValue(responseBodyString, TransferResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    // Miscellaneous APIs
    @Override
    public CompletableFuture<AllCardholdersResponse> getAllCreatedCardHolders(PrincipalApiUser currentApiUser,
                                                                              HttpServletRequest servletRequest) throws GenericException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<AllCardholdersResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/cards/get_all_cardholder?page=1")).newBuilder();

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .header("token", "Bearer " + secretKey)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        AllCardholdersResponse apiCallResponse = mapper.readValue(responseBodyString, AllCardholdersResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<IssuedCardResponse> getAllIssuedCard(PrincipalApiUser currentApiUser,
                                                                  HttpServletRequest servletRequest) throws GenericException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<IssuedCardResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/cards/get_all_cards?page=1")).newBuilder();

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .header("token", "Bearer " + secretKey)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        IssuedCardResponse apiCallResponse = mapper.readValue(responseBodyString, IssuedCardResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    private void isCurrentUserValid(PrincipalApiUser currentApiUser,
                                    HttpServletRequest servletRequest) throws GenericException {
        boolean isCurrentUserIpAddressAndAccessKeyValid =
                accessService.isCurrentUserIpAddressAndAccessKeyValid(currentApiUser, servletRequest);
        if (!isCurrentUserIpAddressAndAccessKeyValid)
            throw new GenericException("Bad Request, Check Credentials and try again");
    }

    @Override
    public CompletableFuture<IssuingWalletResponse> fundIssuingWallet(PrincipalApiUser currentApiUser,
                                                                      HttpServletRequest servletRequest,
                                                                      String amount) throws GenericException {
        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<IssuingWalletResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/cards/fund_issuing_wallet")).newBuilder();

        JSONObject httpRequest = new JSONObject();
        httpRequest.put("amount", amount);

        RequestBody body = RequestBody.create(httpRequest.toString(), MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .header("token", "Bearer " + secretKey)
                .patch(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        IssuingWalletResponse apiCallResponse = mapper.readValue(responseBodyString, IssuingWalletResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }


    @Override
    public CompletableFuture<IssuingWalletResponse> getIssuingWalletBalance(PrincipalApiUser currentApiUser,
                                                                            HttpServletRequest servletRequest) throws GenericException {
        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<IssuingWalletResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/cards/get_issuing_wallet_balance")).newBuilder();

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .header("token", "Bearer " + secretKey)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        IssuingWalletResponse apiCallResponse = mapper.readValue(responseBodyString, IssuingWalletResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<FXRateExchange> getFXRateExchange(PrincipalApiUser currentApiUser,
                                                               HttpServletRequest servletRequest) throws GenericException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<FXRateExchange> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/v1/issuing/cards/fx-rate")).newBuilder();

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .header("token", "Bearer " + secretKey)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        FXRateExchange apiCallResponse = mapper.readValue(responseBodyString, FXRateExchange.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<AllStatesResponse> getAllStates(PrincipalApiUser currentApiUser,
                                                             HttpServletRequest servletRequest) throws GenericException {

        isCurrentUserValid(currentApiUser, servletRequest);
        CompletableFuture<AllStatesResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/v1/issuing/sandbox/cardholder/get_all_states?country=Nigeria")).newBuilder();

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .header("token", "Bearer " + secretKey)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        AllStatesResponse apiCallResponse = mapper.readValue(responseBodyString, AllStatesResponse.class);
                        future.complete(apiCallResponse);
                    } else {
                        ErrorResponse errorResponse = mapper.readValue(responseBodyString, ErrorResponse.class);
                        String errorMessage = errorResponse.getMessage();
                        future.completeExceptionally(new GenericException(errorMessage));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException exception) {
                future.completeExceptionally(new GenericException("Error executing HTTP request: " + exception.getMessage()));
            }
        });
        return future;
    }
}