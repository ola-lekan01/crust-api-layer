package africa.crust.service;

import africa.crust.auth.PrincipalApiUser;
import africa.crust.data.dtos.response.AccountTransactionResponse;
import africa.crust.data.dtos.response.ErrorResponse;
import africa.crust.exceptions.GenericException;
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
public class TransactionApiServiceImpl implements TransactionApiService {

    private final ObjectMapper mapper;

    private final AccessService accessService;
    private final String authToken = System.getenv("AUTHENTICATION_TOKEN");
    private final String baseUrl = System.getenv("BASE_URL");
    private final OkHttpClient client = new OkHttpClient();


    @Override
    public CompletableFuture<AccountTransactionResponse> getCustomerTransactionsByReferenceNumber(PrincipalApiUser currentApiUser,
                                                                                                  String referenceNumber,
                                                                                                  HttpServletRequest servletRequest) throws GenericException {
        CompletableFuture<AccountTransactionResponse> future = new CompletableFuture<>();
        isCurrentUserValid(currentApiUser, servletRequest);
        String url = baseUrl + "/api/Transaction/GetCustomerTransationsByReferenceNumber/2?authtoken=" + authToken + "&referenceNumber=" + referenceNumber;
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse( url)).newBuilder();
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
                        AccountTransactionResponse apiCallResponse = mapper.readValue(responseBodyString, AccountTransactionResponse.class);
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
}