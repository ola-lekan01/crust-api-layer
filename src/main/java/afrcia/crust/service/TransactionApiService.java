package afrcia.crust.service;

import afrcia.crust.auth.PrincipalApiUser;
import afrcia.crust.data.dtos.response.AccountTransactionResponse;
import afrcia.crust.exceptions.GenericException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.concurrent.CompletableFuture;

public interface TransactionApiService {
    CompletableFuture<AccountTransactionResponse> getCustomerTransactionsByReferenceNumber(PrincipalApiUser currentApiUser,
                                                                                           String referenceNumber,
                                                                                           HttpServletRequest servletRequest) throws GenericException;
}