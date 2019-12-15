package com;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.stmt.Statement;

public class MethodNode {
	String mMethodName;
	List<String> mMethodStatements;

	public MethodNode() {
		mMethodName = "";
		mMethodStatements = new ArrayList<String>();
	}

	public String getmMethodName() {
		return mMethodName;
	}

	public void setmMethodName(String mMethodName) {
		this.mMethodName = mMethodName;
	}

	public void setMethodStatements(NodeList<Statement> statements) {
		for (Statement stmt : statements) {
			mMethodStatements.add(stmt.toString());
		}
	}

	public List<String> getMethodStatements() {
		return this.mMethodStatements;
	}
}
