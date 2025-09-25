package com.makers.usecase.loan;

import com.makers.model.exceptions.BusinessException;
import com.makers.model.loan.Loan;
import com.makers.model.loan.gateways.LoanRepository;
import com.makers.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class LoanUseCase {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;

    public Flux<Loan> getUserLoans(Integer userId) {
        return loanRepository.getUserLoans(userId);
    }

    public Mono<Loan> createLoan(Loan newLoan) {
        return userRepository.getUserById(newLoan.getUserId())
                .flatMap(user -> loanRepository.saveLoan(newLoan.toBuilder()
                    .state(1)
                    .build()))
                .switchIfEmpty(Mono.error(new BusinessException(1002, "Ese usuario no existe")));
    }

    public Mono<Boolean> updateLoan(Loan loan) {
        return loanRepository.updateLoan(loan);
    }
}
