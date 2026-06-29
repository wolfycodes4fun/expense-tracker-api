package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ExpenseRepository;
import com.example.demo.model.Expense;

import jakarta.transaction.Transactional;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public void updateExpense(Long id, String description, BigDecimal amount, LocalDate date) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);

        if (!optionalExpense.isPresent()) return;
        Expense existingExpense = optionalExpense.get();

        if (description != null && !description.isBlank())
            existingExpense.setDescription(description);
        if (amount != null)
            existingExpense.setAmount(amount);
        if (date != null)
            existingExpense.setDateOfTransaction(date);

        expenseRepository.save(existingExpense);
    }

    public List<Expense> searchExpenses(String mustIncludeInDescription, LocalDate date) {
        if (mustIncludeInDescription != null && date != null)
            return expenseRepository.findByDescriptionContainingIgnoreCaseAndDateOfTransaction(mustIncludeInDescription, date);
        else if (mustIncludeInDescription != null)
            return expenseRepository.findByDescriptionContainingIgnoreCase(mustIncludeInDescription);
        else if (date != null)
            return expenseRepository.findByDateOfTransaction(date);
        else
            return expenseRepository.findAll();
    }

    @Transactional
    public String deleteById(Long id) {
        if (!expenseRepository.existsById(id))
            return "No expense records with the requested ID " + id + " exists.";
        expenseRepository.deleteById(id);
        
        return "Successfully deleted the expense record with ID " + id; 
    }

    @Transactional
    public String deleteById(List<Long> ids) {
        expenseRepository.deleteAllById(ids);
        return "Successfully deleted the expense records with ID's " + ids;
    }
}