package com.saatvik.expense_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExpenseRequest {
    @NotBlank(message = "Title is Required")
    private String title;

    @NotNull(message = "Amount is Required")
    @Positive(message = "Amount cannot be Negative")
    private BigDecimal amount;

    @NotNull(message = "Date is required")
    private LocalDateTime createdOn;

    private String note;

    @NotNull(message = "Category Id is required")
    private Long categoryId;
}
