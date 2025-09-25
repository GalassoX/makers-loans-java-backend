package com.makers.model.loan.gateways;

import com.makers.model.loan.Loan;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LoanRepository {
    Flux<Loan> getUserLoans(Integer userId);
    Mono<Loan> saveLoan(Loan loan);
    Mono<Boolean> updateLoan(Loan loan);
}
