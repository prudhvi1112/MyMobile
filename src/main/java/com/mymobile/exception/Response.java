package com.mymobile.exception;


public class Response<T> {
    private String data;
    private String status;
	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Response(String data, String status) {
		super();
		this.data = data;
		this.status = status;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Response [data=" + data + ", status=" + status + "]";
	}
	

   
}
