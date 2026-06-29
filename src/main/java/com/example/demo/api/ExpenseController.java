package com.example.demo.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Expense;
import com.example.demo.service.ExpenseService;

@RequestMapping("api/v1/expense-tracking")
@RestController
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public void addExpense(@RequestBody Expense expense) {
        expenseService.createExpense(expense);
    }

    @PutMapping("/update-record/{id}")
    public void updateExpenseRecord(
        @PathVariable("id") Long id,
        @RequestParam(name = "description", required = false) String description,
        @RequestParam(name = "amount", required = false) BigDecimal amount,
        @RequestParam(name = "dateOfTransaction", required = false) LocalDate date
    ) {
        expenseService.updateExpense(id, description, amount, date);
    }

    @GetMapping("/search")
    public List<Expense> getExpenseRecords(
        @RequestParam(name = "description", required = false) String description,
        @RequestParam(name = "date", required = false) LocalDate date) {
    
        return expenseService.searchExpenses(description, date);
    }

    @DeleteMapping("/delete")
    public String deleteExpenseRecords(
        @RequestParam("deletionIds") List<Long> ids) {
            if (ids.size() == 1)
                return expenseService.deleteById(ids.get(0));
            else
                return expenseService.deleteById(ids);
    }
}
