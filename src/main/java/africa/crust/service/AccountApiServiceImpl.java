package africa.crust.service;

import africa.crust.data.dtos.request.*;
import africa.crust.data.dtos.response.*;
import africa.crust.exceptions.GenericException;
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
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountApiServiceImpl implements AccountApiService {

    private final ObjectMapper mapper;

    private final AccessService accessService;
    private final String authToken = System.getenv("AUTHENTICATION_TOKEN");
    private final String mfbCode = System.getenv("INSTITUTION_ID");
    private final String institutionCode = System.getenv("INSTITUTION_CODE");
    private final String baseUrl = System.getenv("BASE_URL");
    private final OkHttpClient client = new OkHttpClient();


    @Override
    public CompletableFuture<CreateAccountResponse> createGswAccount(String data, HttpServletRequest servletRequest) throws GenericException {
        CompletableFuture<CreateAccountResponse> future = new CompletableFuture<>();
        isCurrentUserValid(servletRequest);

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/sanef/api/v1/agencybanking/createGswAccount?authtoken=" + authToken)).newBuilder();
        JSONObject body = new JSONObject();
        body.put("data", data);
        RequestBody requestBody = RequestBody.create(body.toString(), MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CreateAccountResponse apiCallResponse = mapper.readValue(responseBodyString, CreateAccountResponse.class);
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
    public CompletableFuture<CreateAccountResponse> createGsaAccount(String data, HttpServletRequest servletRequest) throws GenericException {
        CompletableFuture<CreateAccountResponse> future = new CompletableFuture<>();
        isCurrentUserValid(servletRequest);

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/sanef/api/v1/agencybanking/createGsaAccount?authtoken=" + authToken)).newBuilder();
        JSONObject body = new JSONObject();
        body.put("data", data);
        RequestBody requestBody = RequestBody.create(body.toString(), MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CreateAccountResponse apiCallResponse = mapper.readValue(responseBodyString, CreateAccountResponse.class);
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
    public CompletableFuture<CustomerAndAccountResponse> createCustomerAndAccount(CreateCustomerAndAccount createCustomerAndAccount,
                                                                                  HttpServletRequest servletRequest) throws GenericException, JsonProcessingException {

        isCurrentUserValid(servletRequest);
        CompletableFuture<CustomerAndAccountResponse> future = new CompletableFuture<>();
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/api/Account/CreateCustomerAndAccount/2?authtoken=" + authToken)).newBuilder();
        String body = mapper.writeValueAsString(createCustomerAndAccount);
        RequestBody requestBody = RequestBody.create(body, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CustomerAndAccountResponse apiCallResponse = mapper.readValue(responseBodyString, CustomerAndAccountResponse.class);
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
    public CompletableFuture<CustomerAndAccountResponse> createCustomerAndAccountT3(CreateCustomerAndAccount createCustomerAndAccount,
                                                                                    HttpServletRequest servletRequest) throws GenericException, JsonProcessingException {
        CompletableFuture<CustomerAndAccountResponse> future = new CompletableFuture<>();
        isCurrentUserValid(servletRequest);

        String url = baseUrl + "/api/Account/CreateCustomerAndAccountT3/2?authtoken=" + authToken;
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        String body = mapper.writeValueAsString(createCustomerAndAccount);
        RequestBody requestBody = RequestBody.create(body, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CustomerAndAccountResponse apiCallResponse = mapper.readValue(responseBodyString, CustomerAndAccountResponse.class);
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
    public CompletableFuture<UpdateAccountResponse> updateAccountTier(UpdateAccountTierRequest updateAccountTierRequest,
                                                                      HttpServletRequest servletRequest) throws GenericException, JsonProcessingException {
        isCurrentUserValid(servletRequest);
        CompletableFuture<UpdateAccountResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(
                baseUrl + "/api/Account/UpdateAccountTier/2?authtoken=" + authToken)).newBuilder();
        String body = mapper.writeValueAsString(updateAccountTierRequest);
        RequestBody requestBody = RequestBody.create(body, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        UpdateAccountResponse apiCallResponse = mapper.readValue(responseBodyString, UpdateAccountResponse.class);
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
    public CompletableFuture<UpdateAccountResponse> updateAccountTier2(UpdateAccountTierRequest updateAccountTierRequest,
                                                                       HttpServletRequest servletRequest) throws GenericException, JsonProcessingException {
        isCurrentUserValid(servletRequest);
        CompletableFuture<UpdateAccountResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(
                baseUrl + "/api/Account/UpdateAccountTier2/2?authtoken=" + authToken)).newBuilder();
        String body = mapper.writeValueAsString(updateAccountTierRequest);
        RequestBody requestBody = RequestBody.create(body, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        UpdateAccountResponse apiCallResponse = mapper.readValue(responseBodyString, UpdateAccountResponse.class);
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
    public CompletableFuture<UpdateAccountResponse> updateAccountNotificationPreference(UpdateAccountNotificationPreference updateAccountNotificationPreference,
                                                                                        HttpServletRequest servletRequest) throws GenericException, JsonProcessingException {
        isCurrentUserValid(servletRequest);
        CompletableFuture<UpdateAccountResponse> future = new CompletableFuture<>();
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(
                baseUrl + "/api/Account/UpdateAccountNotificationPreference/2?authtoken=" + authToken)).newBuilder();
        String body = mapper.writeValueAsString(updateAccountNotificationPreference);
        RequestBody requestBody = RequestBody.create(body, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        UpdateAccountResponse apiCallResponse = mapper.readValue(responseBodyString, UpdateAccountResponse.class);
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
    public CompletableFuture<UpdateAccountResponse> updateSupportingDocument(UpdateSupportingDocument updateSupportingDocument,
                                                                             HttpServletRequest servletRequest) throws GenericException, JsonProcessingException {
        isCurrentUserValid(servletRequest);
        CompletableFuture<UpdateAccountResponse> future = new CompletableFuture<>();

        String createAccountUrl = baseUrl + "/api/Account/UploadSupportingDocument/2?authtoken=" + authToken;
        HttpUrl createAccountUrlEndpoint = HttpUrl.parse(createAccountUrl);
        Request.Builder requestBuilder = new Request.Builder()
                .addHeader("Content-Type", "application/json");

        String createAccountRequest = mapper.writeValueAsString(updateSupportingDocument);

        RequestBody body = RequestBody.create(createAccountRequest, MediaType.get("application/json"));

        assert createAccountUrlEndpoint != null;
        HttpUrl url = createAccountUrlEndpoint.newBuilder()
                .build();

        Request request = requestBuilder
                .url(url)
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
                        UpdateAccountResponse apiCallResponse = mapper.readValue(responseBodyString, UpdateAccountResponse.class);
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
    public CompletableFuture<UpdateAccountResponse> getSupportingDocument(String accountNumber, Integer docType,
                                                                          HttpServletRequest servletRequest) throws GenericException {
        isCurrentUserValid(servletRequest);
        CompletableFuture<UpdateAccountResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "/api/Account/GetSupportingDocument" +
                "/2?authtoken=" + authToken + "&accountNumber=" + accountNumber + "&doctype=" + docType)).newBuilder();

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
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
                        UpdateAccountResponse apiCallResponse = mapper.readValue(responseBodyString, UpdateAccountResponse.class);
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
    public CompletableFuture<AccountByTransactionReferenceResponse> getAccountByTransactionTrackingReference(String transactionTrackingRef,
                                                                                                             Boolean computeWithdrawalBalance,
                                                                                                             HttpServletRequest servletRequest) throws GenericException {
        isCurrentUserValid(servletRequest);
        CompletableFuture<AccountByTransactionReferenceResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(
                baseUrl + "/api/Account/GetAccountByTransactionTrackingRef/2?authtoken=" + authToken
                        + "&transactionTrackingRef=" + transactionTrackingRef + "&computewithdrawableBalance="
                        + computeWithdrawalBalance + "&mfbCode=" + mfbCode)).newBuilder();

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .get()
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
                        AccountByTransactionReferenceResponse apiCallResponse = mapper.readValue(responseBodyString, AccountByTransactionReferenceResponse.class);
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
    public CompletableFuture<AccountByTransactionReferenceResponse> getAccountByTransactionTrackingReference2(String transactionTrackingRef,
                                                                                                              Boolean computeWithdrawalBalance,
                                                                                                              HttpServletRequest servletRequest) throws GenericException {
        isCurrentUserValid(servletRequest);
        CompletableFuture<AccountByTransactionReferenceResponse> future = new CompletableFuture<>();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(
                baseUrl + "/api/Account/GetAccountByTransactionTrackingRef2/2?authtoken=" + authToken
                        + "&transactionTrackingRef=" + transactionTrackingRef + "&computewithdrawableBalance="
                        + computeWithdrawalBalance + "&mfbCode=" + mfbCode)).newBuilder();

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
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
                        AccountByTransactionReferenceResponse apiCallResponse = mapper.readValue(responseBodyString, AccountByTransactionReferenceResponse.class);
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
    public CompletableFuture<AccountByTransactionReferenceResponse> getAccountByAccountNumber(String accountNumber,
                                                                                              Boolean computeWithdrawalBalance,
                                                                                              HttpServletRequest servletRequest) throws GenericException {
        isCurrentUserValid(servletRequest);
        CompletableFuture<AccountByTransactionReferenceResponse> future = new CompletableFuture<>();

        String url = baseUrl + "/api/Account/GetAccountByAccountNumber/2?authtoken="
                + authToken + "&accountNumber=" + accountNumber + "&computewithdrawableBalance="
                + computeWithdrawalBalance;
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        Request request = new Request.Builder()
                .header("Content-Type", "application/json")
                .url(urlBuilder.build())
                .get()
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
                        AccountByTransactionReferenceResponse apiCallResponse = mapper.readValue(responseBodyString, AccountByTransactionReferenceResponse.class);
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
    public CompletableFuture<ActiveSavingResponse> getGetActiveSavingsAccountsByCustomerID(String customerId, HttpServletRequest servletRequest) throws GenericException {
        isCurrentUserValid(servletRequest);
        CompletableFuture<ActiveSavingResponse> future = new CompletableFuture<>();

        String url = baseUrl + "/api/Account/GetActiveSavingsAccountsByCustomerID/2?authtoken=" + authToken + "&customerId=" + customerId;

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .get()
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
                        ActiveSavingResponse[] apiCallResponse = mapper.readValue(responseBodyString, ActiveSavingResponse[].class);
                        future.complete(apiCallResponse[0]);
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
    public CompletableFuture<ActiveSavingResponse2> getGetActiveSavingsAccountsByCustomerID2(String customerId, HttpServletRequest servletRequest) throws GenericException {
        isCurrentUserValid(servletRequest);
        CompletableFuture<ActiveSavingResponse2> future = new CompletableFuture<>();

        String url = baseUrl + "/api/Account/GetActiveSavingsAccountsByCustomerID2/2?authtoken=" + authToken + "&customerId=" + customerId;

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .header("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        ActiveSavingResponse2[] apiCallResponse = mapper.readValue(responseBodyString, ActiveSavingResponse2[].class);
                        future.complete(apiCallResponse[0]);
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
    public CompletableFuture<GetAccountsByCustomerIdResponse> getAccountsByCustomerID(String customerId, HttpServletRequest servletRequest) throws GenericException {

        isCurrentUserValid(servletRequest);
        CompletableFuture<GetAccountsByCustomerIdResponse> future = new CompletableFuture<>();
        String url = baseUrl + "/api/Account/GetAccountsByCustomerId/2?authtoken=" + authToken + "&customerId=" + customerId;

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(
                url)).newBuilder();
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
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
                        GetAccountsByCustomerIdResponse apiCallResponse = mapper.readValue(responseBodyString, GetAccountsByCustomerIdResponse.class);
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
    public CompletableFuture<CustomerAndAccountResponse> createQuickAccount(CreateCustomerAndAccount createCustomerAndAccount,
                                                                            HttpServletRequest servletRequest) throws GenericException, JsonProcessingException {
        isCurrentUserValid(servletRequest);
        CompletableFuture<CustomerAndAccountResponse> future = new CompletableFuture<>();
        String createAccountUrl = baseUrl + "/api/Account/CreateAccountQuick/2?authtoken=" + authToken;
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(createAccountUrl)).newBuilder();
        String body = mapper.writeValueAsString(createCustomerAndAccount);
        RequestBody requestBody = RequestBody.create(body, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CustomerAndAccountResponse apiCallResponse = mapper.readValue(responseBodyString, CustomerAndAccountResponse.class);
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
    public CompletableFuture<CustomerAndAccountResponse> onBoardCustomer(OnBoardCustomerRequest onBoardCustomer,
                                                                         HttpServletRequest servletRequest) throws GenericException, JsonProcessingException {
        isCurrentUserValid(servletRequest);
        CompletableFuture<CustomerAndAccountResponse> future = new CompletableFuture<>();

        String url = baseUrl + "/api/Account/OnboardCustomer/2?authtoken=" + authToken;
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        String body = mapper.writeValueAsString(onBoardCustomer);
        RequestBody requestBody = RequestBody.create(body, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CustomerAndAccountResponse apiCallResponse = mapper.readValue(responseBodyString, CustomerAndAccountResponse.class);
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
    public CompletableFuture<CustomerAndAccountResponse> createCustomerAccount(CreateCustomerAndAccount createCustomerAndAccount,
                                                                               HttpServletRequest servletRequest) throws GenericException, JsonProcessingException {

        isCurrentUserValid(servletRequest);
        CompletableFuture<CustomerAndAccountResponse> future = new CompletableFuture<>();
        String url = baseUrl + "/api/Account/CreateCustomerAccount/2?authtoken=" + authToken;
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        String body = mapper.writeValueAsString(createCustomerAndAccount);
        RequestBody requestBody = RequestBody.create(body, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CustomerAndAccountResponse apiCallResponse = mapper.readValue(responseBodyString, CustomerAndAccountResponse.class);
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
    public CompletableFuture<BalanceResponse> getOutstandingBalance(
                                                                    String accountNumber,
                                                                    HttpServletRequest servletRequest) throws GenericException {
        isCurrentUserValid(servletRequest);
        CompletableFuture<BalanceResponse> future = new CompletableFuture<>();

        String url = baseUrl + "/api/Account/GetOutstandingBalance/2?mfbCode=" + mfbCode + "&authtoken="
                + authToken + "&accountNumber=" + accountNumber;
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
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
                        BalanceResponse apiCallResponse = mapper.readValue(responseBodyString, BalanceResponse.class);
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
    public CompletableFuture<CustomerAndAccountResponse> addAccountToCustomer(AddAccountToCustomerRequest addAccountToCustomerRequest,
                                                                              HttpServletRequest servletRequest) throws GenericException, JsonProcessingException {

        isCurrentUserValid(servletRequest);
        CompletableFuture<CustomerAndAccountResponse> future = new CompletableFuture<>();
        String url = baseUrl + "/api/Account/AddAccountToCustomer/2?authtoken=" + authToken + "&mfbCode=" + mfbCode;
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        String body = mapper.writeValueAsString(addAccountToCustomerRequest);
        RequestBody requestBody = RequestBody.create(body, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CustomerAndAccountResponse apiCallResponse = mapper.readValue(responseBodyString, CustomerAndAccountResponse.class);
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
    public CompletableFuture<CustomerAndAccountResponse> createAccount(CreateCustomerAndAccount createCustomerAndAccount,
                                                                       HttpServletRequest servletRequest) throws GenericException, JsonProcessingException {

        isCurrentUserValid(servletRequest);
        CompletableFuture<CustomerAndAccountResponse> future = new CompletableFuture<>();
        String url = baseUrl + "/api/Account/CreateAccount/2?authtoken=" + authToken + "&mfbCode=" + mfbCode;
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        String body = mapper.writeValueAsString(createCustomerAndAccount);
        RequestBody requestBody = RequestBody.create(body, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CustomerAndAccountResponse apiCallResponse = mapper.readValue(responseBodyString, CustomerAndAccountResponse.class);
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
    public CompletableFuture<CustomerAndAccountResponse> retrieveAccountNumber(String acctOpeningTrackingRef,
                                                                               HttpServletRequest servletRequest) throws GenericException {
        isCurrentUserValid(servletRequest);
        CompletableFuture<CustomerAndAccountResponse> future = new CompletableFuture<>();
        String url = baseUrl + "/api/Account/RetrieveAccountNumber/2?authtoken=" + authToken
                + "&acctOpeningTrackingRef=" + acctOpeningTrackingRef + "&mfbCode=" + mfbCode;
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        JSONObject body = new JSONObject();
        body.put("acctOpeningTrackingRef", acctOpeningTrackingRef);
        RequestBody requestBody = RequestBody.create(body.toString(), MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        CustomerAndAccountResponse apiCallResponse = mapper.readValue(responseBodyString, CustomerAndAccountResponse.class);
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

    // Continue from here
    @Override
    public CompletableFuture<NameEnquiryResponse> doNameEnquiry(String accountNumber,
                                                                HttpServletRequest servletRequest) throws GenericException {
        isCurrentUserValid(servletRequest);
        CompletableFuture<NameEnquiryResponse> future = new CompletableFuture<>();
        String url = baseUrl + "/api/Account/DoNameEnquiry/2?authtoken=" + authToken + "&accountNumber=" + accountNumber + "&institutionCode=" + institutionCode;
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        JSONObject body = new JSONObject();
        body.put("AccountNumber", accountNumber);
        body.put("InstitutionCode", institutionCode);
        RequestBody requestBody = RequestBody.create(body.toString(), MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        NameEnquiryResponse apiCallResponse = mapper.readValue(responseBodyString, NameEnquiryResponse.class);
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
    public CompletableFuture<DoBalanceEnquiryResponse> doBalanceEnquiry(EnquiryRequest enquiryRequest,
                                                                        HttpServletRequest servletRequest) throws GenericException, JsonProcessingException {

        isCurrentUserValid(servletRequest);
        CompletableFuture<DoBalanceEnquiryResponse> future = new CompletableFuture<>();

        String url = baseUrl + "/api/Account/DoBalanceEnquiry/2?authtoken=" + authToken;
        enquiryRequest.setInstitutionCode(institutionCode);
        log.info("Institution Code " + institutionCode );
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        String body = mapper.writeValueAsString(enquiryRequest);
        log.info("body " + body );
        RequestBody requestBody = RequestBody.create(body, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        DoBalanceEnquiryResponse apiCallResponse = mapper.readValue(responseBodyString, DoBalanceEnquiryResponse.class);
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
    public CompletableFuture<DoBalanceEnquiryResponse> doFundsTransfer(EnquiryRequest enquiryRequest,
                                                                       HttpServletRequest servletRequest) throws GenericException, JsonProcessingException {
        isCurrentUserValid(servletRequest);
        CompletableFuture<DoBalanceEnquiryResponse> future = new CompletableFuture<>();

        enquiryRequest.setInstitutionCode(institutionCode);
        String url = baseUrl + "/api/Account/DoFundsTransfer/2?authtoken=" + authToken;
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        String body = mapper.writeValueAsString(enquiryRequest);
        RequestBody requestBody = RequestBody.create(body, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        DoBalanceEnquiryResponse apiCallResponse = mapper.readValue(responseBodyString, DoBalanceEnquiryResponse.class);
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
    public CompletableFuture<DoBalanceEnquiryResponse> confirmTransaction(EnquiryRequest enquiryRequest,
                                                                          HttpServletRequest servletRequest) throws GenericException, JsonProcessingException {
        isCurrentUserValid(servletRequest);
        CompletableFuture<DoBalanceEnquiryResponse> future = new CompletableFuture<>();

        enquiryRequest.setInstitutionCode(institutionCode);
        String url = baseUrl + "/api/Account/ConfirmTransaction/2?authtoken=" + authToken;
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        String body = mapper.writeValueAsString(enquiryRequest);
        RequestBody requestBody = RequestBody.create(body, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        DoBalanceEnquiryResponse apiCallResponse = mapper.readValue(responseBodyString, DoBalanceEnquiryResponse.class);
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
    public CompletableFuture<DoBalanceEnquiryResponse> reverseTransaction(ReverseTransactionRequest reverseTransactionRequest,
                                                                          HttpServletRequest servletRequest) throws GenericException, JsonProcessingException {

        isCurrentUserValid(servletRequest);
        CompletableFuture<DoBalanceEnquiryResponse> future = new CompletableFuture<>();
        reverseTransactionRequest.setInstitutionCode(institutionCode);
        String url = baseUrl + "/api/Account/ReverseTransaction/2?authtoken=" + authToken;
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        String body = mapper.writeValueAsString(reverseTransactionRequest);
        RequestBody requestBody = RequestBody.create(body, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    assert responseBody != null;
                    String responseBodyString = responseBody.string();
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        DoBalanceEnquiryResponse apiCallResponse = mapper.readValue(responseBodyString, DoBalanceEnquiryResponse.class);
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

    private void isCurrentUserValid(HttpServletRequest servletRequest) throws GenericException {
        boolean isCurrentUserIpAddressAndAccessKeyValid =
                accessService.isCurrentUserIpAddressAndAccessKeyValid(servletRequest);
        if (!isCurrentUserIpAddressAndAccessKeyValid)
            throw new GenericException("Bad Request, Check Credentials and try again");
    }
}