package org.scripton.core.reqres;

import lombok.Data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author archmagece
 * @since 2017-08-25
 */
@Data
public class SBListResponseWrapper<RESPONSE> {

	public static <RESPONSE> SBListResponseWrapper<RESPONSE> create(){
		return new SBListResponseWrapper<RESPONSE>();
	}

	private long leadTime;
	public SBListResponseWrapper leadTime(long leadTime) {
		this.leadTime = leadTime;
		return this;
	}

	private boolean success = true;
	public SBListResponseWrapper fail() {
		success = false;
		return this;
	}

	private String code;
	public SBListResponseWrapper code(String code) {
		this.code = code;
		return this;
	}

	private String message;
	public SBListResponseWrapper message(String message) {
		this.message = message;
		return this;
	}

	protected Set<Map<String, String>> validationErrorSet = new HashSet<>();

	private Collection<RESPONSE> data;
	public SBListResponseWrapper<RESPONSE> data(Collection<RESPONSE> data){
		this.data = data;
		return this;
	}
}
