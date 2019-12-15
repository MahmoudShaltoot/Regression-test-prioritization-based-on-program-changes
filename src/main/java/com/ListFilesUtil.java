package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ListFilesUtil {

	public static List<ClassNode> root;

	public static List<ClassNode> listFiles(String directoryName) throws FileNotFoundException, InterruptedException {

		root = new ArrayList<ClassNode>();

		File directoryFile = new File(directoryName);
		File[] fList = directoryFile.listFiles();
		for (File file : fList) {
			if (file.isFile()) {

				ClassNode classNode = new ClassNode(file.getName(), file.getPath());

				JavaParserUtil.ExetractAllMethods(file.getPath().toString());

				classNode.setmClassMethods(JavaParserUtil.getMethodsList());

				root.add(classNode);
			}
		}
		return root;
	}
}