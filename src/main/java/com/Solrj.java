package com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.MapSolrParams;

public class Solrj {

	public static List<String> TestCases;

	private Solrj(){
		//Private Constructor
	}

	public static void retrieveTestCases() throws SolrServerException, IOException {

		TestCases = new ArrayList<String>();

		final String url = "http://localhost:8983/solr/firstcore";
		final SolrClient client = new HttpSolrClient.Builder(url).build();

		solrStartedMessage();
		Map<String, String> queryParamMap = new HashMap<String, String>();

		queryParamMap.put("q", "*:*");

		// search in specific field ['name' field]
		queryParamMap.put("fl", "name");

		MapSolrParams queryParams = new MapSolrParams(queryParamMap);

		QueryResponse response = client.query(queryParams);

		Iterator<SolrDocument> resultIterator = response.getResults().iterator();

		while (resultIterator.hasNext()) {
			SolrDocument solrDocument = resultIterator.next();
			TestCases.add(solrDocument.getFieldValue("name").toString());
		}
		client.close();
	}

	public static String search(String quary) throws SolrServerException, IOException {
		if (quary.equals("")) {
			return "";
		}
		final String url = "http://localhost:8983/solr/firstcore";
		final SolrClient client = new HttpSolrClient.Builder(url).build();

		solrStartedMessage();
		Map<String, String> queryParamMap = new HashMap<String, String>();

		// quary = removeCommonKeywords(quary);
		queryParamMap.put("q", "name:*" + quary + "*");

		// search in specific field ['name' field]
		queryParamMap.put("fl", "name");

		MapSolrParams queryParams = new MapSolrParams(queryParamMap);

		QueryResponse response = client.query(queryParams);

		String tmpTestMethod = "";
		Iterator<SolrDocument> resultIterator = response.getResults().iterator();

		if (resultIterator.hasNext()) {
			SolrDocument solrDocument = resultIterator.next();
			tmpTestMethod = solrDocument.getFieldValue("name").toString();
		} else {
			System.out.println("empty");

		}
		client.close();

		if (tmpTestMethod == "") {
			System.out.println("Using Tokeniziation !!");
			searchUsingTokenizing(Solrj.tokenizeQuery(quary));
		}

		return tmpTestMethod;
	}

	public static String searchUsingTokenizing(List<String> tozinizedQuery) {
		String suggestedTestCase = "";
		int currentFrequency = 0;
		int newCurrentFrequency = 0;
		for (String tmpTestCase : TestCases) {
			newCurrentFrequency = 0;
			for (String tmptozinizedQuery : tozinizedQuery) {
				if (tmpTestCase.toLowerCase().contains(tmptozinizedQuery.toLowerCase())) {
					newCurrentFrequency++;
				}
			}
			if (newCurrentFrequency > currentFrequency) {
				currentFrequency = newCurrentFrequency;
				suggestedTestCase = tmpTestCase;
			}
		}
		return suggestedTestCase;
	}

	/* tokenize */
	public static List<String> tokenizeQuery(String query) {
		String tmpString = "";
		ArrayList<String> tokinizedQuery = new ArrayList<String>();
		char[] queryCharacters = query.toCharArray();

		for (char c : queryCharacters) {
			if (Character.isUpperCase(c)) {
				if (tmpString != "") {
					tokinizedQuery.add(tmpString);
					tmpString = "";
				}
			}
			tmpString += c;
		}
		if (tmpString != "")
			tokinizedQuery.add(tmpString);

		return tokinizedQuery;
	}

	public static void indexNewDoc(List<String> testMethods) throws SolrServerException, IOException {
		String url = "http://localhost:8983/solr";
		SolrClient client = new HttpSolrClient.Builder(url).build();

		SolrInputDocument doc;
		for (String tmpMethods : testMethods) {
			doc = new SolrInputDocument();
			doc.addField("name", tmpMethods);
			// @Param "Document name in solr server" , DocumentObject
			client.add("firstcore", doc);

			client.commit("firstcore");
		}
	}

	public static String removeCommonKeywords(String query) {

		/* REMOVE 'Test','Function','Numbers','_' KEYWORDS */
		String newquery = query.replace("test", "");
		newquery = newquery.replace("tst", "");
		newquery = newquery.replace("function", "");
		newquery = newquery.replaceAll("fn", "");
		newquery = newquery.replaceAll("_", "");
		newquery = newquery.replaceAll("\\d", "");

		return newquery;
	}

	public static void solrStartedMessage() {
		System.out.println(
				"----------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t Solr Started");
		System.out.println(
				"----------------------------------------------------------------------------------------------------");
	}
}
