package sample.core.transfer;

import lombok.Data;

@Data
public class EmptyResponseWrapper {

    public static EmptyResponseWrapper create() {
        return new EmptyResponseWrapper();
    }

    private boolean success = true;

    public EmptyResponseWrapper success(boolean success) {
        this.success = success;
        return this;
    }

    private String lang;

    public EmptyResponseWrapper lang(String lang) {
        this.lang = lang;
        return this;
    }

    private String code;

    public EmptyResponseWrapper code(String code) {
        this.code = code;
        return this;
    }

    private String message;

    public EmptyResponseWrapper message(String message) {
        this.message = message;
        return this;
    }
}
