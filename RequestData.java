package com.model;

public class RequestData {
	private String language;
	private String id;
	private String text;

	public RequestData(String language, String id, String text) {
		this.language = language;
		this.id = id;
		this.text = text;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "RequestData [language=" + language + ", id=" + id + ", text=" + text + "]";
	}

}
