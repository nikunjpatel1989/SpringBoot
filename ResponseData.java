package com.model;

public class ResponseData {
	String score;
	String id;

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ResponseData [score=" + score + ", id=" + id + "]";
	}

}
