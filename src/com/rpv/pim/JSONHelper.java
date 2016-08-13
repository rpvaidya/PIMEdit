package com.rpv.pim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rpv.pim.swt.Accounts;

public class JSONHelper {

	private final String catURL = "http://localhost:8080/getCategories";
	private final String tagsURL = "http://localhost:8080/getTagsForCategory?category=";
	private final String tagsDetailsURL = "http://localhost:8080/getDetailsForTag?tag=";

	public String[] getCategories()

	{
		String[] cats = null;
		try {

			URL url = new URL(this.catURL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			Gson jsonReader = new GsonBuilder().create();
			cats = jsonReader.fromJson(br, String[].class);
			jsonReader.newJsonReader(br);

			for (int i = 0; i < cats.length; i++) {
				System.out.println(cats[i]);
			}
			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return cats;
	}

	public String[] getTagsForCategory(String cat) {
		String[] tags = null;
		try {

			String tagsURLFinal = this.tagsURL + cat;

			URL url = new URL(tagsURLFinal);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			Gson jsonReader = new GsonBuilder().create();
			tags = jsonReader.fromJson(br, String[].class);
			jsonReader.newJsonReader(br);

			for (int i = 0; i < tags.length; i++) {
				System.out.println(tags[i]);
			}
			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return tags;
	}

	public Accounts getDetailsForTag(String tag) {
		Accounts detailsForTag = null;
		try {

			String tagsURLFinal = this.tagsDetailsURL + tag;

			URL url = new URL(tagsURLFinal);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			Gson jsonReader = new GsonBuilder().create();
			detailsForTag = jsonReader.fromJson(br, Accounts.class);
			jsonReader.newJsonReader(br);

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return detailsForTag;

	}

	public void updateTagDetails(Accounts acct) {
		try {


			String postUrl = "http://localhost:8080/updateTag";// put in your
																// url
			Gson gson = new Gson();
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(postUrl);
			StringEntity postingString = new StringEntity(gson.toJson(acct));
			post.setEntity(postingString);
			post.setHeader("Content-type", "application/json");
			HttpResponse response = httpClient.execute(post);
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
