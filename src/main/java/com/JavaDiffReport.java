package com;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JavaDiffReport {
	public static List<Jclass> classObj = new ArrayList<Jclass>();

	public static List<MethodChanged> methodChangedList = new ArrayList<MethodChanged>();

	public static void createJavaReport(String versionTwoPath, String versionOnePath) throws FileNotFoundException {

		Jclass newJclass = new Jclass();
		newJclass.setPath(versionTwoPath);

		Scanner versionTwoScanner = new Scanner(new File(versionTwoPath));
		Scanner versionOneScanner = new Scanner(new File(versionOnePath));

		List<String> versionTwo = new ArrayList<String>();
		List<String> versionOne = new ArrayList<String>();

		while (versionOneScanner.hasNext()) {
			versionOne.add(versionOneScanner.nextLine());
		}

		while (versionTwoScanner.hasNext()) {
			versionTwo.add(versionTwoScanner.nextLine());
		}

		List<Statment> statmentsNA = new ArrayList<Statment>();

		int lineNum = -1;
		for (String lineV2 : versionTwo) {
			lineNum++;
			if (!(lineV2.trim().length() > 0)) {
				statmentsNA.add(new Statment(lineV2, 'B', lineNum));
			} else if (!versionOne.contains(lineV2)) {
				statmentsNA.add(new Statment(lineV2, 'A', lineNum));
			} else {
				statmentsNA.add(new Statment(lineV2, 'N', lineNum));
			}
		}

		List<Statment> statmentsD = new ArrayList<Statment>();
		lineNum = -1;
		for (String lineV1 : versionOne) {
			lineNum++;
			if (!versionTwo.contains(lineV1) && lineV1.trim().length() > 0) {
				statmentsD.add(new Statment(lineV1, 'D', lineNum));
			}
		}

		int counter = statmentsNA.size();
		int index = 0;
		int indexD = 0;
		while (index < counter) {
			if (indexD < statmentsD.size()) {
				if (statmentsNA.get(index).getLineNumber() < statmentsD.get(indexD).getLineNumber()) {
					newJclass.Statments.add(statmentsNA.get(index));
					index++;
				} else {
					newJclass.Statments.add(statmentsD.get(indexD));
					indexD++;
				}
			} else {
				/* Deleted finished */
				newJclass.Statments.add(statmentsNA.get(index));
				index++;
			}
		}

		if (indexD < statmentsD.size()) {
			while (indexD <= statmentsD.size()) {
				newJclass.Statments.add(statmentsD.get(indexD));
				indexD++;
			}
		}

		classObj.add(newJclass);
	}

	public static void createJavaReport(String versionTwoPath) throws FileNotFoundException {
		Jclass newJclass = new Jclass();
		newJclass.setPath(versionTwoPath);

		Scanner versionTwoScanner = new Scanner(new File(versionTwoPath));

		List<String> versionTwo = new ArrayList<String>();

		while (versionTwoScanner.hasNext()) {
			versionTwo.add(versionTwoScanner.nextLine());
		}

		List<Statment> statmentsNA = new ArrayList<Statment>();
		int lineNum = -1;
		for (String lineV2 : versionTwo) {
			lineNum++;
			if (!(lineV2.trim().length() > 0)) {
				statmentsNA.add(new Statment(lineV2, 'B', lineNum));
			} else {
				statmentsNA.add(new Statment(lineV2, 'A', lineNum));
			}
		}

		for (Statment statmentNA : statmentsNA) {
			newJclass.Statments.add(statmentNA);
		}

		classObj.add(newJclass);
	}

	public static void addMethodChanged(String className, String methodName, int numAdd, int numDel) {
		methodChangedList.add(new MethodChanged(className, methodName, numAdd, numDel));
	}

	public static void addAllMethodChanged(ClassNode newJavaClass) {
		String className = newJavaClass.getmClassName();
		int numOfAddition = newJavaClass.getmClassMethods().size();

		for (MethodNode methodNode : newJavaClass.getmClassMethods()) {
			methodChangedList.add(new MethodChanged(className, methodNode.getmMethodName(), numOfAddition, 0));
		}
	}

	public static void displayClassReport() {
		for (Jclass tmpJclass : classObj) {
			for (Statment tmpStatment : tmpJclass.Statments)
				System.out.println(tmpStatment.getLineNumber() + " : " + tmpStatment.getLine() + " ("
						+ tmpStatment.getType() + ")");
		}
	}

	public static void displayMethodReport() {
		for (MethodChanged tmpMethodChanged : methodChangedList) {
			System.out.println(tmpMethodChanged.getmMethodName() + " :: Added = " + tmpMethodChanged.getNumOfAddition()
					+ " , Deleted = " + tmpMethodChanged.getNumOfDeletion());
		}
	}

	public static void setRelatedTestCase() throws IOException, SolrServerException {
		int counter = 0;
		for(MethodChanged methodChanged : methodChangedList){

			if(methodChanged.numOfAddition + methodChanged.numOfDeletion != 0){
				methodChangedList.get(counter).mRelatedTestCase = Solrj.search(methodChanged.mMethodName);
			}
			counter++;
		}
	}
}
