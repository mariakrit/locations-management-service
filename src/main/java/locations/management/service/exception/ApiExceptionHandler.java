package locations.management.service.exception;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;
import locations.management.service.models.ErrorDto;


import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

	@ResponseStatus(NOT_FOUND)
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorDto> resourceNotFoundException(RuntimeException e) {
	    
		HttpStatus status = HttpStatus.NOT_FOUND; // 404
		log.error(e.getMessage(), e);
		
	    return new ResponseEntity<ErrorDto>(ErrorDto.builder()
				.message(e.getMessage())
				.statusCode(status.value())
				.build(), 
				status);
	}

	@ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleConstraintViolations(ConstraintViolationException e) {

        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
		log.error(e.getMessage(), e);


		return new ResponseEntity<ErrorDto>(ErrorDto.builder()
				.message(e.getMessage())
				.statusCode(status.value())
				.build(), 
				status);
    }
	
	@ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception e) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500
		log.error(e.getMessage(), e);


		return new ResponseEntity<ErrorDto>(ErrorDto.builder()
				.message(e.getMessage())
				.statusCode(status.value())
				.build(), 
				status);
    }
}