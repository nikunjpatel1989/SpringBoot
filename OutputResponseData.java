package com.model;

import java.util.List;

public class OutputResponseData {

	List<ResponseData> documents;

	public List<ResponseData> getDocuments() {
		return documents;
	}

	public void setDocuments(List<ResponseData> documents) {
		this.documents = documents;
	}

	@Override
	public String toString() {
		return "OutputResponseData [documents=" + documents + "]";
	}

}
