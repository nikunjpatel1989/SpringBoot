package com.model;

import java.util.List;

public class InputRequestData {
	List<RequestData> documents;

	public List<RequestData> getDocuments() {
		return documents;
	}

	public void setDocuments(List<RequestData> documents) {
		this.documents = documents;
	}

	@Override
	public String toString() {
		return "InputRequestData [documents=" + documents + "]";
	}

	
}
