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
public class SBOneResponseWrapper<RESPONSE> {

	public static <RESPONSE> SBOneResponseWrapper<RESPONSE> create(){
		return new SBOneResponseWrapper<RESPONSE>();
	}

	private long leadTime;
	public SBOneResponseWrapper leadTime(long leadTime) {
		this.leadTime = leadTime;
		return this;
	}

	private boolean success = true;
	public SBOneResponseWrapper fail() {
		success = false;
		return this;
	}

	private String code;
	public SBOneResponseWrapper code(String code) {
		this.code = code;
		return this;
	}

	private String message;
	public SBOneResponseWrapper message(String message) {
		this.message = message;
		return this;
	}

	protected Set<Map<String, String>> validationErrorSet = new HashSet<>();

	private RESPONSE data;
	public SBOneResponseWrapper<RESPONSE> data(RESPONSE data){
		this.data = data;
		return this;
	}
}
