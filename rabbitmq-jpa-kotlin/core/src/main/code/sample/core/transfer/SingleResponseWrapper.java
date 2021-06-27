package sample.core.transfer;

import lombok.Data;

@Data
public class SingleResponseWrapper<RESPONSE> {

    public static <RESPONSE0> SingleResponseWrapper<RESPONSE0> create() {
        return new SingleResponseWrapper<>();
    }

    private boolean success = true;

    public SingleResponseWrapper<RESPONSE> success(boolean success) {
        this.success = success;
        return this;
    }

    private String lang;

    public SingleResponseWrapper<RESPONSE> lang(String lang) {
        this.lang = lang;
        return this;
    }

    private String code;

    public SingleResponseWrapper<RESPONSE> code(String code) {
        this.code = code;
        return this;
    }

    private String message;

    public SingleResponseWrapper<RESPONSE> message(String message) {
        this.message = message;
        return this;
    }

    private RESPONSE data;

    public SingleResponseWrapper<RESPONSE> data(RESPONSE data) {
        this.data = data;
        return this;
    }
}
