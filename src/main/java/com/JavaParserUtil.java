package com;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.vaadin.ui.Notification;

public class JavaParserUtil {

	// private static List<ClassNode> classNode = new ArrayList<ClassNode>();

	static List<MethodNode> methodList;

	public static void ExetractAllMethods(String path) throws FileNotFoundException, InterruptedException {

		methodList = new ArrayList<MethodNode>();
		try {
			// creates an input stream for the file to be parsed
			FileInputStream javaFile = new FileInputStream(path);

			// parse it
			CompilationUnit javaCC = JavaParser.parse(javaFile);

			// visit and print the methods names
			javaCC.accept(new MethodVisitor(), null);

		} catch (ParseProblemException e) {
			Notification.show("Uploaded Code has some bugs");
			System.out.println("File have some Problems");
			TimeUnit.MINUTES.sleep(5);

			System.exit(0);
		}
	}

	private static class MethodVisitor extends VoidVisitorAdapter<Void> {

		@Override
		public void visit(MethodDeclaration method, Void arg) {
			/*
			 * here you can access the attributes of the method. this method will be called
			 * for all methods in this CompilationUnit, including inner class methods
			 */
			MethodNode methodNode = new MethodNode();

			// System.out.println("[MethodINFO] " + method.getName());
			methodNode.setmMethodName(method.getName().toString());

			Optional<BlockStmt> block = method.getBody();

			NodeList<Statement> statements = block.get().getStatements();

			for (Statement tmp : statements) {
				tmp.removeComment();
				methodNode.mMethodStatements.add(tmp.toString());
				// System.out.println("\t" + tmp.toString());
			}

			methodList.add(methodNode);

			super.visit(method, arg);
		}
	}

	public static List<MethodNode> getMethodsList() {
		return methodList;
	}
}
