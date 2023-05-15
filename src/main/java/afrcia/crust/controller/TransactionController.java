package afrcia.crust.controller;

import afrcia.crust.auth.PrincipalApiUser;
import afrcia.crust.auth.annotation.CurrentApiUser;
import afrcia.crust.data.dtos.response.AccountTransactionResponse;
import afrcia.crust.data.dtos.response.ApiResponse;
import afrcia.crust.exceptions.GenericException;
import afrcia.crust.service.TransactionApiService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/v1/crust/transaction")
@RequiredArgsConstructor
@Tag(name = "Crust Transaction Endpoint", description = "This endpoints contains related Transaction Endpoints")
@SecurityRequirement(name = "SECRET_KEY")
@SecurityRequirement(name = "BearerAuth")
public class TransactionController {
    private final TransactionApiService transactionApiService;

    @GetMapping("/get-customer-transactions-by-reference-number")
    public ResponseEntity<ApiResponse> getCustomerTransactionsByReferenceNumber(@CurrentApiUser PrincipalApiUser currentApiUser,
                                                                                @RequestParam("referenceNumber") String referenceNumber,
                                                                                HttpServletRequest request) {
        try {
            CompletableFuture<AccountTransactionResponse> response = transactionApiService.getCustomerTransactionsByReferenceNumber(currentApiUser, referenceNumber, request);
            return new ResponseEntity<>(new ApiResponse(true, "Successfully",
                    request.getRequestURL().toString(), response.get()), HttpStatus.CREATED);
        } catch (GenericException | InterruptedException | ExecutionException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
