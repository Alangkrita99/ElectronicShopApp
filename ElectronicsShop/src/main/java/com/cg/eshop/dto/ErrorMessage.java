package com.cg.eshop.dto;

import java.util.List;

public class ErrorMessage {
	private String status;
	private String Message;
	private List<String> messages;

	public ErrorMessage(String status, List<String> messages) {
		super();
		this.status = status;
		this.messages = messages;
	}

	public ErrorMessage(String status, String message) {
		super();
		this.status = status;
		Message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public List<String> getMessages() {
		return messages;
	}

}
