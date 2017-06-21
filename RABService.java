package com.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.model.InputRequestData;
import com.model.OutputResponseData;
import com.model.RequestData;
import com.model.ResponseData;
import com.model.ResultDisplay;

import twitter4j.Location;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class RABService {

	public ResultDisplay getResultOutput(String qString) throws TwitterException, IOException, URISyntaxException {
		QueryResult queryResult = getTweets(qString);
		ResultDisplay resultDisplay = getSentiments(queryResult);
		System.out.println("Successfull");
		return resultDisplay;
	}

	private ResultDisplay getSentiments(QueryResult queryResult) throws IOException, URISyntaxException {
		int i = 1;
		InputRequestData inpData = new InputRequestData();
		ResultDisplay display = new ResultDisplay();
		List<String> positiveTweets = new ArrayList<>();
		List<String> negativeTweets = new ArrayList<>();
		List<String> neutralTweets = new ArrayList<>();
		HttpClient httpclient = HttpClients.createDefault();
		URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/text/analytics/v2.0/sentiment");

		URI uri = builder.build();
		HttpPost request = new HttpPost(uri);
		request.setHeader("Content-Type", "application/json");
		request.setHeader("Ocp-Apim-Subscription-Key", "a2929f8544fc423dad56b0dca0a5aeed");

		for (Status status : queryResult.getTweets()) {
			List<RequestData> dlist = new ArrayList<RequestData>();
			RequestData data = new RequestData("en", i + "_i", status.getText());
			dlist.add(data);
			inpData.setDocuments(dlist);
			Gson gson = new Gson();
			String iData = gson.toJson(inpData);
			StringEntity reqEntity = new StringEntity(iData);
			request.setEntity(reqEntity);

			OutputResponseData jsonInString = null;

			String output = null;
			try {
				HttpResponse response = httpclient.execute(request);
				HttpEntity entity = response.getEntity();

				if (entity != null) {
					output = EntityUtils.toString(entity);
					jsonInString = gson.fromJson(output, OutputResponseData.class);

					List<ResponseData> rData = jsonInString.getDocuments();
					for (ResponseData responseData2 : rData) {
						if (Float.parseFloat(responseData2.getScore()) >=0 && Float.parseFloat(responseData2.getScore()) <=0.3) {
							negativeTweets.add(status.getText());
						} else if (Float.parseFloat(responseData2.getScore()) >= 0.31 && Float.parseFloat(responseData2.getScore()) <= 0.6) {
							neutralTweets.add(status.getText());
						} else {
							positiveTweets.add(status.getText());							
						}
					}
				}
			} catch (Exception e) {
				continue;
			}
			i++;
		}
		display.setPositiveTweets(positiveTweets);
		display.setNegativeTweets(negativeTweets);
		display.setNeutralTweets(neutralTweets);
		return display;
	}

	private QueryResult getTweets(String qString) throws TwitterException {
		String consumerKey = "VJXo92eZUGfXaet6Eht5gi679";
		String consumerSecret = "nEZUc188DZYed2daWm4nLWG6JM1nOcUZUeHZhvEmZP2zmvnC2t";
		String accessToken = "867069099079327744-Mic87yTYV3FQDOPAlmKPZC3SQ2Is1sK";
		String accessTokenSecret = "jdE5970mVreG5UhwdKAcetSozkxcHCul0d3AG1ePeWsXN";

		TwitterFactory twitterFactory = new TwitterFactory();
		Twitter twitter = twitterFactory.getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
		
		Query query = new Query(qString);
		QueryResult result = twitter.search(query);
		return result;
	}

}
