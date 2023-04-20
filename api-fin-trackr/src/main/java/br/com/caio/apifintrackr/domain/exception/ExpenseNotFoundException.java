package br.com.caio.apifintrackr.domain.exception;

public class ExpenseNotFoundException extends RuntimeException{
    public ExpenseNotFoundException(long id){
        super(String.format("Expense with ID %s not found!",id));
    }
}
