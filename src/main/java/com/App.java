package com;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import java.util.List;
import java.util.Arrays;

public class App {
    private	static int counter = 1;
	static String VERSIONONEDIRECTORY;
	static String VERSIONTWODIRECTORY;

	public static void Start() throws SolrServerException, IOException, InterruptedException {

		if(counter != 0) {
			Root _1VersionRoot = new Root();

			_1VersionRoot.setRootData(ListFilesUtil.
					listFiles(VERSIONONEDIRECTORY));

			Root _2VersionRoot = new Root();
			_2VersionRoot.setRootData(ListFilesUtil.
					listFiles(VERSIONTWODIRECTORY));

			Root.Comapre_Code_Versions(_2VersionRoot, _1VersionRoot);

			JavaDiffReport.displayClassReport();

			JavaDiffReport.displayMethodReport();

			Solrj.retrieveTestCases();

			counter--;
		}
		
		boolean isEmpty = Solrj.isSolrEmpty();
		if (isEmpty) {
			System.out.println("Adding test cases to Solr!");

			List<String> strings = Arrays.asList(
				"testStartingOfApplication",
				"testPerformTask",
				"IsNotAnrenewVariables",
				"FailToperformMemoryTask",
				"IsNotAnrenewVariables",
				"FailToPerformMemoryTask",
				"doMathIsCorrect",
				"testAddText",
				"TestPerformNumberTask",
				"TestChangePositiveToNegative",
				"TestChangeNegativeToPositive"
			);

			Solrj.indexNewDoc(strings);
		} else {
			System.out.println("Test Cases indexed!");
		}
	}

	public static void setDirectory(String directory){
		if(VERSIONONEDIRECTORY == null){
			VERSIONONEDIRECTORY = directory;
		}else{
			VERSIONTWODIRECTORY = directory;
		}
	}
}
