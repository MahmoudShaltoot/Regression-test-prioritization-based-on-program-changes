package com;

import java.util.ArrayList;
import java.util.List;

public class ClassNode {

	private String mClassName;
	private String mClassPath;
	public List<MethodNode> mClassMethods;

	public ClassNode(String className, String classPath) {
		this.mClassName = className;
		this.mClassPath = classPath;
		mClassMethods = new ArrayList<MethodNode>();
	}

	public String getmClassName() {
		return mClassName;
	}

	public void setmClassName(String mClassName) {
		this.mClassName = mClassName;
	}

	public String getmClassPath() {
		return mClassPath;
	}

	public void setmClassPath(String mClassPath) {
		this.mClassPath = mClassPath;
	}

	public List<MethodNode> getmClassMethods() {
		return mClassMethods;
	}

	public void setmClassMethods(List<MethodNode> mClassMethods) {

		this.mClassMethods = mClassMethods;
	}
}
