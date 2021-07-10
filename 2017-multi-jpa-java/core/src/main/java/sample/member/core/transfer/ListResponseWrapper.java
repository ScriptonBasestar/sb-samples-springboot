package sample.member.core.transfer;

import lombok.Data;

import java.util.List;

@Data
public class ListResponseWrapper<RESPONSE> {

	public static <RESPONSE0> ListResponseWrapper<RESPONSE0> create() {
		return new ListResponseWrapper<>();
	}

	private boolean success = true;

	public ListResponseWrapper<RESPONSE> success(boolean success) {
		this.success = success;
		return this;
	}

	private String lang;

	public ListResponseWrapper<RESPONSE> lang(String lang) {
		this.lang = lang;
		return this;
	}

	private String code;

	public ListResponseWrapper<RESPONSE> code(String code) {
		this.code = code;
		return this;
	}

	private String message;

	public ListResponseWrapper<RESPONSE> message(String message) {
		this.message = message;
		return this;
	}

	private List<RESPONSE> data;

	public ListResponseWrapper<RESPONSE> data(List<RESPONSE> data) {
		this.data = data;
		return this;
	}
}
