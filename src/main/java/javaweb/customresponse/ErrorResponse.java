package javaweb.customresponse;

public class ErrorResponse {
	private String code;
	private String detail;

	public ErrorResponse() {

	}

	public ErrorResponse(String code, String detail) {
		super();
		this.code = code;
		this.detail = detail;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
