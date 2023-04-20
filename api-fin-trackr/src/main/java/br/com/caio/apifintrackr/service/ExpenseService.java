package br.com.caio.apifintrackr.service;

import br.com.caio.apifintrackr.domain.model.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {

    public List<Expense> listAllExpenses();

    public Expense getExpenseById(long id);
    public Expense saveExpense(Expense expense);
    public void deleteExpense(long id);

    public Expense updateExpense(long id, Expense expense);
    public BigDecimal calculateInstallmentsAmount(int installments, BigDecimal amount);
    public LocalDate calculateDateEndInstallments(LocalDate purchaseDate,int installments);
}
