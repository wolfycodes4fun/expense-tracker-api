package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ExpenseRepository;
import com.example.demo.model.Expense;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
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

    public String deleteById(Long id) {
        if (!expenseRepository.existsById(id))
            return "No expense records with the requested ID " + id + " exists.";
        expenseRepository.deleteById(id);
        
        return "Successfully deleted the expense record with ID " + id; 
    }
}