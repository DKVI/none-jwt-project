package javaweb.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javaweb.customexception.FieldRequireException;
import javaweb.customexception.NoBodyException;
import javaweb.customresponse.ErrorResponse;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<Object> handleArithmeticException (ArithmeticException ex, WebRequest req) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setCode("Internal Server Error");
		errorResponse.setDetail("Số nguyên k chia đc cho 0");
		return new ResponseEntity<Object>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(FieldRequireException.class)
	public ResponseEntity<Object> handleFieldRequireExceptionEntity (FieldRequireException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setCode("Bad Request");
		errorResponse.setDetail(ex.getMessage());
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
