package br.com.caio.apifintrackr.repository;

import br.com.caio.apifintrackr.domain.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
}
