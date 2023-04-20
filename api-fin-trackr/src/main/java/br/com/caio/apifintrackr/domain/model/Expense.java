package br.com.caio.apifintrackr.domain.model;

import br.com.caio.apifintrackr.domain.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Field description not be null!")
    @NotBlank(message = "Field description not be blank!")
    private String description;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Field category not be null!")
    private Category category;
    @NotNull(message = "Field amount not be null!")
    private BigDecimal amount;
    private BigDecimal installmentsAmount;
    @NotNull(message = "Field installments not be null!")
    private int installments;
    private LocalDate purchaseDate;
    private LocalDate dateEndInstallments;
}
