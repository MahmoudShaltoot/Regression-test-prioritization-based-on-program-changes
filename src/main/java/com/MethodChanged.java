package com;

public class MethodChanged {
	String mClassName;
	String mMethodName;
	int numOfAddition;
	int numOfDeletion;
	String mRelatedTestCase;

	public MethodChanged(String className, String methodName, int numAdd, int numDel) {
		mClassName = className;
		mMethodName = methodName;
		numOfAddition = numAdd;
		numOfDeletion = numDel;
		mRelatedTestCase = "-";
	}

	public String getmClassName() {
		return mClassName;
	}

	public void setmClassName(String mClassName) {
		this.mClassName = mClassName;
	}

	public String getmMethodName() {
		return mMethodName;
	}

	public void setmMethodName(String mMethodName) {
		this.mMethodName = mMethodName;
	}

	public int getNumOfAddition() {
		return numOfAddition;
	}

	public void setNumOfAddition(int numOfAddition) {
		this.numOfAddition = numOfAddition;
	}

	public int getNumOfDeletion() {
		return numOfDeletion;
	}

	public void setNumOfDeletion(int numOfDeletion) {
		this.numOfDeletion = numOfDeletion;
	}

	public String getmRelatedTestCase(){
		return mRelatedTestCase;
	}
}
