package javaweb.customresponse;

public class CustomResponse {
	private String code;
	private Object data;

	public CustomResponse(String code, Object data) {
		super();
		this.code = code;
		this.data = data;
	}

	public CustomResponse() {

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
