package africa.crust.service;

import africa.crust.auth.PrincipalApiUser;
import africa.crust.data.dtos.response.AccountTransactionResponse;
import africa.crust.exceptions.GenericException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.concurrent.CompletableFuture;

public interface TransactionApiService {
    CompletableFuture<AccountTransactionResponse> getCustomerTransactionsByReferenceNumber(PrincipalApiUser currentApiUser,
                                                                                           String referenceNumber,
                                                                                           HttpServletRequest servletRequest) throws GenericException;
}