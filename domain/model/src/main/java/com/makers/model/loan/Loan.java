package com.makers.model.loan;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Loan {
    private Integer id;
    private Integer userId;
    private Integer amount;
    private Integer state;
    private String paymentDate;
}
