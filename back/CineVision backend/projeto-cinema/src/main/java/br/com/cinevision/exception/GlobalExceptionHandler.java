package br.com.cinevision.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResposta> tratarNaoEncontrado(RecursoNaoEncontradoException ex) {
        ErroResposta erro = new ErroResposta(404, "Não encontrado", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(DadosInvalidosException.class)
    public ResponseEntity<ErroResposta> tratarDadosInvalidos(DadosInvalidosException ex) {
        ErroResposta erro = new ErroResposta(400, "Dados inválidos", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResposta> tratarJsonInvalido(HttpMessageNotReadableException ex) {
        ErroResposta erro = new ErroResposta(400, "JSON inválido",
                "O corpo da requisição está mal formatado. Confira vírgulas, aspas e se a data está no formato AAAA-MM-DD.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroResposta> tratarIdInvalido(MethodArgumentTypeMismatchException ex) {
        ErroResposta erro = new ErroResposta(400, "Parâmetro inválido",
                "O valor informado em '" + ex.getName() + "' não é válido.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroResposta> tratarConflito(DataIntegrityViolationException ex) {
        ErroResposta erro = new ErroResposta(409, "Conflito",
                "Operação não permitida: esse dado já existe ou está sendo usado em outra tabela (ex.: filme que já tem sessões).");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> tratarErroGeral(Exception ex) {
        ex.printStackTrace();
        ErroResposta erro = new ErroResposta(500, "Erro interno",
                "Ocorreu um erro inesperado. Tente novamente mais tarde.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}
