package com.finance.tracker.service;

import com.finance.tracker.dto.TransactionDTO;
import com.finance.tracker.exception.TransactionNotFoundException;
import org.springframework.stereotype.Service;
import com.finance.tracker.entity.Transaction;
import com.finance.tracker.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }
    public TransactionDTO save(TransactionDTO dto) {

        Transaction transaction = new Transaction();

        transaction.setTitle(dto.getTitle());
        transaction.setAmount(dto.getAmount());
        transaction.setCategory(dto.getCategory());
        transaction.setType(dto.getType());
        transaction.setDate(dto.getDate());
        transaction.setDescription(dto.getDescription());

        transactionRepository.save(transaction);

        return dto;
    }

    public List<TransactionDTO> getAllTransaction() {

        List<Transaction> transactions =
                transactionRepository.findAll();

        List<TransactionDTO> dtoList = new ArrayList<>();

        for (Transaction transaction : transactions) {

            TransactionDTO dto = new TransactionDTO();

            dto.setTitle(transaction.getTitle());
            dto.setAmount(transaction.getAmount());
            dto.setCategory(transaction.getCategory());
            dto.setType(transaction.getType());
            dto.setDate(transaction.getDate());
            dto.setDescription(transaction.getDescription());

            dtoList.add(dto);
        }

        return dtoList;
    }

    public TransactionDTO getTransactionById(Long id) {

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() ->
                        new TransactionNotFoundException(
                                "Transaction with id " + id + " not found"
                        )
                );

        TransactionDTO dto = new TransactionDTO();

        dto.setTitle(transaction.getTitle());
        dto.setAmount(transaction.getAmount());
        dto.setCategory(transaction.getCategory());
        dto.setType(transaction.getType());
        dto.setDate(transaction.getDate());
        dto.setDescription(transaction.getDescription());

        return dto;
    }

    public void deleteTransaction(Long id){
        transactionRepository.deleteById(id);
    }

    public TransactionDTO updateTransaction(Long id, TransactionDTO dto) {

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() ->
                        new TransactionNotFoundException(
                                "Transaction with id " + id + " not found"
                        )
                );

        // DTO → Entity
        transaction.setTitle(dto.getTitle());
        transaction.setAmount(dto.getAmount());
        transaction.setCategory(dto.getCategory());
        transaction.setType(dto.getType());
        transaction.setDate(dto.getDate());
        transaction.setDescription(dto.getDescription());

        // Save updated Entity
        Transaction updatedTransaction =
                transactionRepository.save(transaction);

        // Entity → DTO
        TransactionDTO response = new TransactionDTO();

        response.setTitle(updatedTransaction.getTitle());
        response.setAmount(updatedTransaction.getAmount());
        response.setCategory(updatedTransaction.getCategory());
        response.setType(updatedTransaction.getType());
        response.setDate(updatedTransaction.getDate());
        response.setDescription(updatedTransaction.getDescription());

        return response;
    }
}
