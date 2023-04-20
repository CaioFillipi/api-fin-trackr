package br.com.caio.apifintrackr.controller.openapi;

import br.com.caio.apifintrackr.domain.model.Expense;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public interface OpenAPI {

    @Operation(summary = "Get all Expenses", description = "Get all Expenses avaiable")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Return a list of Expenses")
    })
    public ResponseEntity<List<Expense>> getAllExpenses();

    @Operation(summary = "Get Expense", description = "Get Expense based in ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Return Expense"),
            @ApiResponse(responseCode = "404",
                    description = "Expense not found",
                    content = @Content(mediaType = MediaType.ALL_VALUE))
    })
    public ResponseEntity<Expense> getExpense(long id);

    @Operation(summary = "Expense Pacient", description = "Create a new Expense")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",
                    description = "Create new Expense"),
            @ApiResponse(responseCode = "400",
                    description = "Invalid request",
                    content = @Content(mediaType = MediaType.ALL_VALUE))
    })
    public ResponseEntity<Expense> createExpense(Expense expense, UriComponentsBuilder builder);

    @Operation(summary = "Delete Expense",description = "Delete Expense based in ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Expense deleted"),
            @ApiResponse(responseCode = "404",
                    description = "Expense not found",
                    content = @Content(mediaType = MediaType.ALL_VALUE))
    })
    public ResponseEntity<Void> deleteExpense(long id);

    @Operation(summary = "Update Expense", description = "Update Expense")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Create new Expense"),
            @ApiResponse(responseCode = "404",
                    description = "Expense not found",
                    content = @Content(mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "400",
                    description = "Invalid request",
                    content = @Content(mediaType = MediaType.ALL_VALUE))
    })
    public ResponseEntity<Expense> updateExpense(long id,Expense expense);
}
