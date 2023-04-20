package br.com.caio.apifintrackr.domain.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
public class ErrorMessage {
    private HttpStatus status;
    private String erro;

    public ErrorMessage(HttpStatus status, String erro) {
        this.status = status;
        this.erro = erro;
    }
}
