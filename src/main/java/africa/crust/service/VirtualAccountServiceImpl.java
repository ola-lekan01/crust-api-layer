package africa.crust.service;

import africa.crust.data.dtos.request.CreateVirtualAccountRequest;
import africa.crust.data.dtos.request.InitiatePaymentRequest;
import africa.crust.data.dtos.response.ErrorResponse;
import africa.crust.data.dtos.response.VirtualAccountResponse;
import africa.crust.exceptions.GenericException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class VirtualAccountServiceImpl implements VirtualAccountService {

    private final ObjectMapper mapper;

    private final AccessService accessService;
    private final String authToken = System.getenv("AUTHENTICATION_TOKEN");
    private final String baseUrl = System.getenv("BASE_URL");
    private final OkHttpClient client;

    @Override
    public CompletableFuture<VirtualAccountResponse> createVirtualAccount(CreateVirtualAccountRequest accountRequest,
                                                                          HttpServletRequest servletRequest) throws GenericException, JsonProcessingException {
        CompletableFuture<VirtualAccountResponse> future = new CompletableFuture<>();
        isCurrentUserValid(servletRequest);
        String url = baseUrl + "/api/VirtualAccount/CreateVirtualAccountsRequest/2?authtoken=" + authToken;
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        String body = mapper.writeValueAsString(accountRequest);
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
                        VirtualAccountResponse apiCallResponse = mapper.readValue(responseBodyString, VirtualAccountResponse.class);
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
    public CompletableFuture<VirtualAccountResponse> getVirtualAccountCreationStatus(String trackingRef,
                                                                                     HttpServletRequest servletRequest) throws GenericException {
        CompletableFuture<VirtualAccountResponse> future = new CompletableFuture<>();
        isCurrentUserValid(servletRequest);
        String url = baseUrl + "/api/VirtualAccount/CheckVirtualAccountsCreationStatus/2?authtoken=" + authToken + "&trackingRef=" + trackingRef;
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
                        VirtualAccountResponse apiCallResponse = mapper.readValue(responseBodyString, VirtualAccountResponse.class);
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
    public CompletableFuture<VirtualAccountResponse> initiatePayment(InitiatePaymentRequest initiatePayment,
                                                                     HttpServletRequest servletRequest) throws GenericException, JsonProcessingException {
        CompletableFuture<VirtualAccountResponse> future = new CompletableFuture<>();
        isCurrentUserValid(servletRequest);
        String url = baseUrl + "/api/VirtualAccount/InitiatePayment/2?authtoken=" + authToken;
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        String body = mapper.writeValueAsString(initiatePayment);
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
                    log.info(responseBodyString);
                    if (response.isSuccessful()) {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        VirtualAccountResponse apiCallResponse = mapper.readValue(responseBodyString, VirtualAccountResponse.class);
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
    public CompletableFuture<VirtualAccountResponse> verifyTransaction(String uniqueReference,
                                                                       HttpServletRequest servletRequest) throws GenericException {
        CompletableFuture<VirtualAccountResponse> future = new CompletableFuture<>();
        isCurrentUserValid(servletRequest);

        String url = baseUrl + "/api/VirtualAccount/VerifyTransaction/2?authtoken=" + authToken + "&uniqueReference=" + uniqueReference;
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
                        VirtualAccountResponse apiCallResponse = mapper.readValue(responseBodyString, VirtualAccountResponse.class);
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