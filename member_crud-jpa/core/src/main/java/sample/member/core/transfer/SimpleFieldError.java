package sample.member.core.transfer;


import lombok.Data;

@Data
public class SimpleFieldError {
	private String path;
	private String message;
	private String code;

	public SimpleFieldError(String path, String message, String code) {
		this.path = path;
		this.message = message;
		this.code = code;
	}
}
