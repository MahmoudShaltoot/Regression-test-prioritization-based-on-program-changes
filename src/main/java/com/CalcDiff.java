package com;

import java.util.ArrayList;
import java.util.List;

public class CalcDiff {

	private static List<String> VersionTwoStatments;
	private static List<String> VersionOneStatments;
	private static int numOfAddition;
	private static int numOfDeletion;

	public static int getNumOfAddition() {
		return numOfAddition;
	}

	public static int getNumOfDeletion() {
		return numOfDeletion;
	}

	public static void calcNumOfAddition(List<String> V2methodStatments, List<String> V1methodStatments) {
		VersionTwoStatments = new ArrayList<String>(V2methodStatments);
		VersionOneStatments = new ArrayList<String>(V1methodStatments);

		VersionTwoStatments.removeAll(VersionOneStatments);
		numOfAddition = VersionTwoStatments.size();
	}

	public static void calcNumOfDeletion(List<String> V2methodStatments, List<String> V1methodStatments) {
		VersionTwoStatments = new ArrayList<String>(V2methodStatments);
		VersionOneStatments = new ArrayList<String>(V1methodStatments);

		VersionOneStatments.removeAll(VersionTwoStatments);
		numOfDeletion = VersionOneStatments.size();
	}

	public static void calcNumOfAdd_Del(List<String> V2methodStatments, List<String> V1methodStatments) {
		calcNumOfAddition(V2methodStatments, V1methodStatments);
		calcNumOfDeletion(V2methodStatments, V1methodStatments);
	}
}
