package com;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Root {

	public List<ClassNode> mClassNodeList;

	public Root() {
		mClassNodeList = new ArrayList<ClassNode>();
	}

	public void setRootData(List<ClassNode> classNodeList) {
		this.mClassNodeList = classNodeList;
	}

	public void getTreeData(Root root) {

		for (ClassNode tmpClassNode : root.mClassNodeList) {
			System.out.println("------------------------------------------------------------------------"
					+ "------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t\t  [INFO] Class Name = " + tmpClassNode.getmClassName());
			System.out.println("------------------------------------------------------------------------"
					+ "------------------------------------------------------------------------");

			for (MethodNode methodNode : tmpClassNode.mClassMethods) {
				System.out.println("[INFO METHOD] " + methodNode.getmMethodName());
				for (String methodStatement : methodNode.mMethodStatements) {
					System.out.println(methodStatement.toString());
				}
				System.out.println("------------------------------------------------------------------------");
			}
		}
	}

	/* Compare Two Methods matched by their names */
	public static void compareMethods(ClassNode versionTwoClass, ClassNode versionOneClass) {

		Iterator<MethodNode> V2_MethodsIterator = versionTwoClass.mClassMethods.iterator();

		Iterator<MethodNode> V1_MethodsIterator;

		while (V2_MethodsIterator.hasNext()) {
			V1_MethodsIterator = versionOneClass.mClassMethods.iterator();

			MethodNode methodNode2 = V2_MethodsIterator.next();
			MethodNode methodNode1;
			while (V1_MethodsIterator.hasNext()) {
				methodNode1 = V1_MethodsIterator.next();
				if (methodNode2.getmMethodName().equals(methodNode1.getmMethodName())) {
					CalcDiff.calcNumOfAdd_Del(methodNode2.mMethodStatements, methodNode1.mMethodStatements);

					JavaDiffReport.addMethodChanged(versionTwoClass.getmClassName(), methodNode2.getmMethodName(),
							CalcDiff.getNumOfAddition(), CalcDiff.getNumOfDeletion());

					}
			}
		}
	}

	/* Return Classes Matched by their Names */
	public static ClassNode compareClasses(ClassNode currentClassNode, Root versionOne) {

		Iterator<ClassNode> V1_ClassesIt = versionOne.mClassNodeList.iterator();

		while (V1_ClassesIt.hasNext()) {
			ClassNode tmpClassNode = V1_ClassesIt.next();
			if (currentClassNode.getmClassName().equals(tmpClassNode.getmClassName())) {
				return tmpClassNode;
			}
		}
		return currentClassNode;
	}

	/* Iterate Over Classes To Get Changes Between Two Versions */
	public static void Comapre_Code_Versions(Root versionTwo, Root versionOne) throws FileNotFoundException {

		Iterator<ClassNode> V2_ClassesIt = versionTwo.mClassNodeList.iterator();
		while (V2_ClassesIt.hasNext()) {
			ClassNode currentClassNode = V2_ClassesIt.next();
			ClassNode versionOneClassNode = compareClasses(currentClassNode, versionOne);
			if (!versionOneClassNode.getmClassPath().equals(currentClassNode.getmClassPath())) {
				compareMethods(currentClassNode, versionOneClassNode);
				JavaDiffReport.createJavaReport(currentClassNode.getmClassPath(), versionOneClassNode.getmClassPath());
				System.out.println(currentClassNode.getmClassName() + " : Exist");
			} else {
				JavaDiffReport.createJavaReport(currentClassNode.getmClassPath());
				JavaDiffReport.addAllMethodChanged(currentClassNode);
			}
		}
	}
}
