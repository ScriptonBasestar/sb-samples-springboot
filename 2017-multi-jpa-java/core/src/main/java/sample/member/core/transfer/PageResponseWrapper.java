package sample.member.core.transfer;

import lombok.Data;

import java.util.List;

@Data
public class PageResponseWrapper<RESPONSE> {

	public static <RESPONSE0> PageResponseWrapper<RESPONSE0> create() {
		return new PageResponseWrapper<>();
	}

	private boolean success = true;

	public PageResponseWrapper<RESPONSE> success(boolean success) {
		this.success = success;
		return this;
	}

	private String lang;

	public PageResponseWrapper<RESPONSE> lang(String lang) {
		this.lang = lang;
		return this;
	}

	private String code;

	public PageResponseWrapper<RESPONSE> code(String code) {
		this.code = code;
		return this;
	}

	private String message;

	public PageResponseWrapper<RESPONSE> message(String message) {
		this.message = message;
		return this;
	}

	private int totalPages;

	public PageResponseWrapper<RESPONSE> totalPages(int totalPages) {
		this.totalPages = totalPages;
		return this;
	}

	private long totalElements;

	public PageResponseWrapper<RESPONSE> totalElements(long totalElements) {
		this.totalElements = totalElements;
		return this;
	}

	private List<RESPONSE> data;

	public PageResponseWrapper<RESPONSE> data(List<RESPONSE> data) {
		this.data = data;
		return this;
	}
}
