package co.edu.udea.dappe.codebreaker.dto;

public class Response {
	private String message;
	private String status;
	
	public Response(String message, String status) {
		this.message = message;
		this.status = status;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public String getStatus(){
		return this.status;
	}
}
