package ru.booking.exception;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.UnexpectedTypeException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({BookingException.class})
    private ResponseEntity<ApiErrorResponse> BookingException(BookingException ex, WebRequest request) {
        ApiErrorResponse apiResponse = new ApiErrorResponse
                .ApiErrorResponseBuilder()
                .withDetail("")
                .withMessage(ex.getMessage())
                .withError_code("409")
                .withStatus(HttpStatus.CONFLICT)
                .atTime(LocalDateTime.now(ZoneOffset.UTC))
                .build();
        return new ResponseEntity<ApiErrorResponse>(apiResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({NotFoundException.class})
    private ResponseEntity<ApiErrorResponse> NotFoundException(NotFoundException ex, WebRequest request) {
        ApiErrorResponse apiResponse = new ApiErrorResponse
                .ApiErrorResponseBuilder()
                .withDetail("the object not found")
                .withMessage("not found")
                .withError_code("404")
                .withStatus(HttpStatus.NOT_FOUND)
                .atTime(LocalDateTime.now(ZoneOffset.UTC))
                .build();
        return new ResponseEntity<ApiErrorResponse>(apiResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler({UnexpectedTypeException.class})
    private ResponseEntity<ApiErrorResponse> UnexpectedTypeException(UnexpectedTypeException ex, WebRequest request) {
        ApiErrorResponse apiResponse = new ApiErrorResponse
                .ApiErrorResponseBuilder()
                .withDetail("")
                .withMessage(ex.getMessage())
                .withError_code("418")
                // for fun
                .withStatus(HttpStatus.I_AM_A_TEAPOT)
                .atTime(LocalDateTime.now(ZoneOffset.UTC))
                .build();
        return new ResponseEntity<ApiErrorResponse>(apiResponse, HttpStatus.I_AM_A_TEAPOT);

    }
}