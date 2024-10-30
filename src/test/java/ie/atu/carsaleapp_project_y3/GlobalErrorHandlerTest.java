package ie.atu.carsaleapp_project_y3;

import ie.atu.carsaleapp_project_y3.error_handler.GlobalErrorHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

public class GlobalErrorHandlerTest {
    @InjectMocks
    private GlobalErrorHandler globalErrorHandler;

    @Mock
    private MethodArgumentNotValidException exception;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testDisplay(){

        FieldError fieldError1 =new FieldError("car","model","must not be blank");
        FieldError fieldError2 =new FieldError("car","make","must not be blank");

        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(Arrays.asList(fieldError1,fieldError2));

        ResponseEntity<Map<String, String>> response = globalErrorHandler.display(exception);

        Map<String,String> expectedErrors = new HashMap<>();
        expectedErrors.put("model", "must not be blank");
        expectedErrors.put("make", "must not be blank");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedErrors, response.getBody());

    }
}
