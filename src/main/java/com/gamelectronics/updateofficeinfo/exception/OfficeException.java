package com.gamelectronics.updateofficeinfo.exception;

import com.gamelectronics.updateofficeinfo.dto.failureResponse.MessageDetails;
import com.gamelectronics.updateofficeinfo.dto.failureResponse.FailureResponse;
import com.gamelectronics.updateofficeinfo.dto.failureResponse.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;

@ControllerAdvice
public class OfficeException {
    @ExceptionHandler(value = NotFoundOfficeException.class)
    public ResponseEntity<Object> exception(NotFoundOfficeException exception) {
        MessageDetails failureMessage = new MessageDetails();
        failureMessage.setText(exception.getMessage());
        failureMessage.setCode("404");
        failureMessage.setArguments(null);
        failureMessage.setType("error");
        return new ResponseEntity<>(new FailureResponse
                (null,new Messages(new ArrayList(Arrays.asList(failureMessage))))
                ,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AuthorizationException.class)
    public ResponseEntity<Object> exception(AuthorizationException exception) {
        MessageDetails failureMessage = new MessageDetails();
        failureMessage.setText(exception.getMessage());
        failureMessage.setCode("401");
        failureMessage.setArguments(null);
        failureMessage.setType("error");
        return new ResponseEntity<>(new FailureResponse
                (null,new Messages(new ArrayList(Arrays.asList(failureMessage))))
                ,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = SamanExceptionException.class)
    public ResponseEntity<Object> exception(SamanExceptionException exception) {
        MessageDetails failureMessage = new MessageDetails();
        failureMessage.setText(exception.getMessage());
        failureMessage.setCode("500");
        failureMessage.setArguments(null);
        failureMessage.setType("error");
        return new ResponseEntity<>(new FailureResponse
                (null,new Messages(new ArrayList(Arrays.asList(failureMessage))))
                ,HttpStatus.EXPECTATION_FAILED);
    }
}
