package br.com.caio.apifintrackr.controller;

import br.com.caio.apifintrackr.controller.openapi.OpenAPI;
import br.com.caio.apifintrackr.domain.model.Expense;
import br.com.caio.apifintrackr.service.impl.ExpenseServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/expense")
@AllArgsConstructor
public class ExpenseController implements OpenAPI {

    private final ExpenseServiceImpl service;


    @Override
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        return ResponseEntity.ok(service.listAllExpenses());
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<Expense> getExpense(@PathVariable("id")long id) {
        Expense expense = service.getExpenseById(id);
        return ResponseEntity.ok(expense);
    }

    @Override
    @PostMapping
    public ResponseEntity<Expense> createExpense(@Valid @RequestBody Expense expense, UriComponentsBuilder builder) {
        Expense newExpense = service.saveExpense(expense);
        URI uri = builder.path("/{id}").buildAndExpand(newExpense.getId()).toUri();
        return ResponseEntity.created(uri).body(newExpense);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable("id") long id) {
        service.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable("id") long id,
                                                 @Valid @RequestBody Expense expense) {
        Expense expenseUpdated = service.updateExpense(id,expense);
        return ResponseEntity.ok(expenseUpdated);
    }
}
