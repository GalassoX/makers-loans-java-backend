package com.makers.jpa.loan;

import com.makers.jpa.loan.entity.LoanEntity;
import com.makers.jpa.loan.helper.AdapterOperations;
import com.makers.model.loan.Loan;
import com.makers.model.loan.gateways.LoanRepository;
import jakarta.transaction.Transactional;
import org.reactivecommons.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class LoanJPARepositoryAdapter extends AdapterOperations<Loan, LoanEntity, Integer, LoanJPARepository>
        implements LoanRepository {

    private static final Logger log = LoggerFactory.getLogger(LoanJPARepositoryAdapter.class);

    public LoanJPARepositoryAdapter(LoanJPARepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Loan.class));
    }

    @Override
    public Flux<Loan> getUserLoans(Integer userId) {
        return Flux.fromIterable(repository.findByUserId(userId))
                .map(loanEntity -> mapper.map(loanEntity, Loan.class));
    }

    @Override
    public Mono<Loan> saveLoan(Loan loan) {
        LoanEntity newLoan = repository.save(mapper.map(loan, LoanEntity.class));

        return Mono.just(mapper.map(newLoan, Loan.class));
    }

    @Transactional
    @Override
    public Mono<Boolean> updateLoan(Loan loan) {
        Integer newLoan = repository.updateState(loan.getId(), loan.getState());
        log.info("Loan updated state: {}", newLoan);

        return Mono.just(true);
    }
}
