package sample.core.transfer;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleFieldError {
    private String path;
    private String message;
    private String code;
}
