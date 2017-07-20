package org.scripton.core.reqres;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorDto {
	private final List<FieldErrorDto> fieldErrors = new ArrayList<>();
	public void addFieldError(String path, String message){
		fieldErrors.add(new FieldErrorDto(path, message));
	}
}
