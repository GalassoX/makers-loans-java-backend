package com.makers.api;

import com.makers.api.dto.request.CreateLoan;
import com.makers.api.dto.request.UpdateLoan;
import com.makers.api.util.ResponseBuilder;
import com.makers.model.exceptions.BusinessException;
import com.makers.model.loan.Loan;
import com.makers.usecase.loan.LoanUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class LoanHandler {
    private final LoanUseCase loanUseCase;

    public Mono<ServerResponse> getUserLoans(ServerRequest request) {
        String userId = request.pathVariable("userId");

        return Mono.fromCallable(() -> Integer.parseInt(userId))
                .flatMapMany(loanUseCase::getUserLoans)
                .collectList()
                .flatMap(loanList -> ResponseBuilder.buildSuccessResponse(loanList, 200))
                .onErrorMap(NumberFormatException.class, e -> new BusinessException(1001, "ID de usuario invalida"));
    }

    public Mono<ServerResponse> createLoan(ServerRequest request) {
        return request.bodyToMono(CreateLoan.class)
                .map(createLoan -> Loan.builder()
                        .userId(createLoan.userId())
                        .amount(createLoan.amount())
                        .paymentDate(createLoan.paymentDate())
                        .build())
                .flatMap(loanUseCase::createLoan)
                .flatMap(loan -> ResponseBuilder.buildSuccessResponse(loan, 201));
    }

    public Mono<ServerResponse> updateLoan(ServerRequest request) {
        return request.bodyToMono(UpdateLoan.class)
                .map(updateLoan -> Loan.builder()
                        .id(updateLoan.loanId())
                        .state(updateLoan.newState())
                        .build())
                .flatMap(loanUseCase::updateLoan)
                .flatMap(loan -> ResponseBuilder.buildSuccessResponse(loan, 200));
    }
}
