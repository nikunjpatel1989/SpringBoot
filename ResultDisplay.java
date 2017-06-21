package com.model;

import java.util.List;

public class ResultDisplay {
	private List<String> positiveTweets;
	private List<String> negativeTweets;
	private List<String> neutralTweets;

	public List<String> getPositiveTweets() {
		return positiveTweets;
	}

	public void setPositiveTweets(List<String> positiveTweets) {
		this.positiveTweets = positiveTweets;
	}

	public List<String> getNegativeTweets() {
		return negativeTweets;
	}

	public void setNegativeTweets(List<String> negativeTweets) {
		this.negativeTweets = negativeTweets;
	}

	public List<String> getNeutralTweets() {
		return neutralTweets;
	}

	public void setNeutralTweets(List<String> neutralTweets) {
		this.neutralTweets = neutralTweets;
	}

	@Override
	public String toString() {
		return "ResultDisplay [positiveTweets=" + positiveTweets + ", negativeTweets=" + negativeTweets
				+ ", neutralTweets=" + neutralTweets + "]";
	}

}
