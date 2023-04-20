package br.com.caio.apifintrackr.service.impl;

import br.com.caio.apifintrackr.domain.exception.ExpenseNotFoundException;
import br.com.caio.apifintrackr.domain.model.Expense;
import br.com.caio.apifintrackr.repository.ExpenseRepository;
import br.com.caio.apifintrackr.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository repository;


    @Override
    public List<Expense> listAllExpenses() {
        return repository.findAll();
    }

    @Override
    public Expense getExpenseById(long id) {
        try{
            Optional<Expense> expense = repository.findById(id);
            return expense.get();
        }catch (Exception ex){
            throw new ExpenseNotFoundException(id);
        }
    }

    @Override
    public Expense saveExpense(Expense expense) {
        expense.setInstallmentsAmount(
                calculateInstallmentsAmount(expense.getInstallments(),expense.getAmount()));
        expense.setDateEndInstallments(calculateDateEndInstallments(
                expense.getPurchaseDate(),expense.getInstallments()
        ));
        return repository.save(expense);
    }

    @Override
    public void deleteExpense(long id) {
        Expense expense = getExpenseById(id);
        repository.delete(expense);
    }

    @Override
    public Expense updateExpense(long id, Expense expense) {
        Expense expenseUpdated = getExpenseById(id);
        expenseUpdated.setDescription(expense.getDescription());
        expenseUpdated.setAmount(expense.getAmount());
        expenseUpdated.setInstallments(expense.getInstallments());
        expenseUpdated.setPurchaseDate(expense.getPurchaseDate());
        expenseUpdated.setCategory(expense.getCategory());
        expenseUpdated.setDateEndInstallments(
                calculateDateEndInstallments(expenseUpdated.getPurchaseDate(),
                        expenseUpdated.getInstallments()));
        expenseUpdated.setInstallmentsAmount(calculateInstallmentsAmount(expenseUpdated.getInstallments(),
                expenseUpdated.getAmount()));
        return repository.save(expenseUpdated);
    }

    @Override
    public BigDecimal calculateInstallmentsAmount(int installments, BigDecimal amount) {
        BigDecimal installmentsAmount = amount
                .divide(new BigDecimal(installments), RoundingMode.HALF_EVEN).setScale(2);
        System.out.println(installmentsAmount);
        return installmentsAmount;
    }

    @Override
    public LocalDate calculateDateEndInstallments(LocalDate purchaseDate, int installments) {
        LocalDate dateEndInstallments = purchaseDate.plusMonths(installments);
        return dateEndInstallments;
    }

}
