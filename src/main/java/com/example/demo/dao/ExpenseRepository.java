package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Expense;
import java.time.LocalDate;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByDescriptionContainingIgnoreCase(String description);
    
    List<Expense> findByDateOfTransaction(LocalDate dateOfTransaction);

    List<Expense> findByDescriptionContainingIgnoreCaseAndDateOfTransaction(String description, LocalDate dateOfTransaction);

}
