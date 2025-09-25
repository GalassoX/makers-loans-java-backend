package com.makers.jpa.loan;

import com.makers.jpa.loan.entity.LoanEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanJPARepository extends CrudRepository<LoanEntity, Integer>, QueryByExampleExecutor<LoanEntity> {
    List<LoanEntity> findByUserId(Integer userId);

    @Modifying
    @Query("UPDATE LoanEntity l SET l.state = :state WHERE l.id = :id")
    Integer updateState(@Param("id") Integer loanId, @Param("state") Integer state);
}
