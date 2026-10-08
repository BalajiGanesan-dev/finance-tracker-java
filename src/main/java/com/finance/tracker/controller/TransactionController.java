package com.finance.tracker.controller;
import com.finance.tracker.dto.TransactionDTO;
import com.finance.tracker.entity.Transaction;
import com.finance.tracker.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public TransactionDTO saveTransaction(@Valid @RequestBody TransactionDTO dto){
        return transactionService.save(dto);

    }

    @GetMapping
    public List<TransactionDTO> getAllTransaction(){
        return  transactionService.getAllTransaction();
    }

    @GetMapping("{id}")
    public TransactionDTO getTransactionById(@PathVariable Long id){
        return transactionService.getTransactionById(id);
    }

    @DeleteMapping("{id}")
            public String deleteTransaction(@PathVariable Long id){
            transactionService.deleteTransaction(id);
            return ("Transaction deleted successfully");

    }

    @PutMapping("{id}")
    public TransactionDTO updateTransaction(@PathVariable Long id, @RequestBody TransactionDTO transaction){
        return transactionService.updateTransaction(id,transaction);
    }
}
