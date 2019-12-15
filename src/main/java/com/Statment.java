package com;

public class Statment {
	private String line;
	private char type;
	private int lineNumber;

	public Statment() {
		// TODO Auto-generated constructor stub
	}

	public Statment(String l, char t, int n) {
		// TODO Auto-generated constructor stub
		line = l;
		type = t;
		lineNumber = n;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}
}
