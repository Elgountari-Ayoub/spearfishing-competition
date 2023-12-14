package ma.youcode.pm.advice;

import ma.youcode.pm.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error mgader:\n" + ex.getMessage());
    }

    // Hadi for validate the MemberDTO, CompetitionDTO fields
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValid(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();

            fieldErrors.put(fieldName, errorMessage);
        });
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Validation failed");
        result.put("errors", fieldErrors);

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

    }

    // Hadi for Duplicated PK
    @ExceptionHandler({
            DuplicateMemberException.class,
            DuplicateCompetitionException.class,
            CompetitionExistInSameDayException.class})
    public ResponseEntity<?> handleDuplicateMemberException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());

        Map<String, Object> result = new HashMap<>();
        result.put("error", error);

        return new ResponseEntity<>(result, HttpStatus.CONFLICT);
    }

    // Hadi for not found exception
    @ExceptionHandler({
            MemberNotFoundException.class,
            CompetitionNotFoundException.class,
            RegistrationException.class,
            LevelNotFoundException.class
    })
    public ResponseEntity<?> handleMemberNotFoundException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());

        Map<String, Object> result = new HashMap<>();
        result.put("error", error);

        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }
}
