package org.scripton.core.reqres;

import lombok.Data;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author archmagece
 * @since 2017-08-25
 */
@Data
public class SBEmptyResponseWrapper {

    public static SBEmptyResponseWrapper create() {
        return new SBEmptyResponseWrapper();
    }

    private long leadTime;
    public SBEmptyResponseWrapper leadTime(long leadTime) {
        this.leadTime = leadTime;
        return this;
    }

    private boolean success = true;
    public SBEmptyResponseWrapper fail() {
        success = false;
        return this;
    }

    private String code;
    public SBEmptyResponseWrapper code(String code) {
        this.code = code;
        return this;
    }

    private String message;
    public SBEmptyResponseWrapper message(String message) {
        this.message = message;
        return this;
    }

    protected Set<Map<String, String>> validationErrorSet = new HashSet<>();
}
