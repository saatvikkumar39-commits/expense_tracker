package com.saatvik.expense_tracker.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExpenseResponse {
    private Long id;
    private String title;
    private String note;
    private BigDecimal amount;
    private LocalDateTime createdOn;
    private String category;
}
