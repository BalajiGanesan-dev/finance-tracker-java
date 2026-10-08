package com.finance.tracker.dto;

import com.finance.tracker.entity.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class TransactionDTO {

    @NotBlank(message = "title cannot be empty")
    private String title;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount should be positive")
    private Double amount;

    @NotNull(message = "Category is required")
    private String category;

    @NotNull(message = "Transaction type is Required")
    private TransactionType type;

    @NotNull(message = "Date is required")
    private LocalDate date;

    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
