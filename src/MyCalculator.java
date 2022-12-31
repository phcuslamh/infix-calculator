// Name: Phuc Huu Lam
// NetID: plam6
// I do not collaborate with anyone else. 

import java.util.*;

public class MyCalculator implements InfixCalculator {

	//Compare precedent of operators
	public static int Precedent(char c) {
		if ((c == '+') || (c == '-')) {return 1;}
		else if ((c == '*') || (c == '/') || (c == '%')) {return 2;}
		else if (c == '^') {return 3;}
		else return -1;
	}
	
	//Find the end of the number from the beginning of a string, if any
	public static String detectNumber(String str) {
		char c = str.charAt(0);
		String toReturn = "";
		toReturn += c;
		if (!Character.isDigit(c)) {
			return toReturn;
		}
		else {
			int i = 1;
			while (i < str.length()) {
				c = str.charAt(i);
				if ((Character.isDigit(c)) || (c == '.')) {
					i += 1;
					toReturn += c;
				}
				else {
					return str.substring(0, i);
				}
			}
			return toReturn;
		}
	}
	
	//Check if a string is numeric
	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {return false;}
	}
	
	//Check number of '.' in a string
	public static int numDot(String str) {
		int num = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '.') {num++;}
		}
		return num;
	}
	
	public static boolean compareParent(String str) {
		int numLeft = 0;
		int numRight = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {numLeft++;}
		}
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ')') {numRight++;}
		}
		return (numLeft == numRight);
	}
		
	//MAIN: CONVERT AN INFIX EXPRESSION TO POSTFIX (stored in a queue)
	public static MyQueue<String> convertToPostfix(String exp) {
		String str = exp;
		MyStack<String> stack = new MyStack<String>();
		MyQueue<String> queue = new MyQueue<String>();
		
		//Check if the number of left and right parentheses are the same
		if (!compareParent(exp)) {throw new ArithmeticException();} 
		
		//Check if there is a double in the expression (to take modulo later)
		String indi = (str.contains(".")) ? "Y" : "N";
		queue.enqueue(indi);
		
		while (str.length() > 0) {
			String c = detectNumber(str);
			if (numDot(c) >= 2) {throw new ArithmeticException();}
			//1. If the token is an operand
			if (isNumeric(c)) {queue.enqueue(c);}
			//2. If the token is an open parenthesis
			else if (c.equals("(")) {
				stack.push(c);
			}
			//2. If the token is a closed parenthesis
			else if (c.equals(")")) {
				while ((!stack.isEmpty()) && (!stack.peek().equals("("))) {queue.enqueue(stack.pop());}
				stack.pop();
			}
			///3. If the token is an operator
			else if ((c.equals("+")) || (c.equals("-")) || (c.equals("*")) || (c.equals("/")) || (c.equals("%"))
					|| (c.equals("^"))) {
				Character ch = c.charAt(0);
				while ((!stack.isEmpty()) && (Precedent(ch) <= Precedent(stack.peek().charAt(0)))) {
					queue.enqueue(stack.pop());;
				}
				stack.push(c);
			}
			else if (c.equals(" ")) {}
			else {throw new ArithmeticException();}
			int i = c.length();
			str = str.substring(i); 
		}
		//4. Pop all the remaining stack symbols
		while (!stack.isEmpty()) {
			if (stack.peek().equals("(")) {throw new ArithmeticException();}
			else {queue.enqueue(stack.pop());}
		}
		return queue;
	}
	
	//MAIN: CALCULATE THE RPN EXPRESSION
	public static String calculator(MyQueue<String> queue) {
		String indi = queue.dequeue();
		boolean containsDoub = (indi.equals("Y"));
		if (containsDoub) {
			MyStack<Double> Dstack = new MyStack<Double>();
			while (!queue.isEmpty()) {
				String str = queue.dequeue();
				if (isNumeric(str)) {Dstack.push(Double.parseDouble(str));}
				else {
					if (Dstack.isEmpty()) {throw new ArithmeticException();}
					double Dval1 = Dstack.pop();
					if (Dstack.isEmpty()) {throw new ArithmeticException();}
					double Dval2 = Dstack.pop();
					if (str.equals("+")) {Dstack.push(Dval2 + Dval1);}
					else if (str.equals("-")) {Dstack.push(Dval2 - Dval1);}
					else if (str.equals("*")) {Dstack.push(Dval2 * Dval1);}
					else if (str.equals("/")) {
						if (Dval1 == 0) {throw new ArithmeticException();}
						else {Dstack.push(Dval2 / Dval1);}
					}
					else if (str.equals("^")) {
						if (Dval2 < 0) {throw new ArithmeticException();}
						else {Dstack.push(Math.pow(Dval2, Dval1));}
					}
					else if (str.equals("%")) {
						if (Dval1 == 0) {throw new ArithmeticException();}
						else {Dstack.push(Dval2 % Dval1);}
					}
				}
			}
			if (Dstack.getLength() != 1) {throw new ArithmeticException();}
			return (String.valueOf(Dstack.pop()));
		}
		else {
			MyStack<Integer> Istack = new MyStack<Integer>();
			while (!(queue.isEmpty())) {
				String str = queue.dequeue();
				if (isNumeric(str)) {Istack.push(Integer.parseInt(str));}
				else {
					if (Istack.isEmpty()) {throw new ArithmeticException();}
					int Ival1 = Istack.pop();
					if (Istack.isEmpty()) {throw new ArithmeticException();}
					int Ival2 = Istack.pop();
					if (str.equals("+")) {Istack.push(Ival2 + Ival1);}
					else if (str.equals("-")) {Istack.push(Ival2 - Ival1);}
					else if (str.equals("*")) {Istack.push(Ival2 * Ival1);}
					else if (str.equals("/")) {
						if (Ival1 == 0) {throw new ArithmeticException();}
						else {Istack.push(Ival2 / Ival1);}
					}
					else if (str.equals("^")) {
						if ((Ival2 == 0) && (Ival1 <= 0)) {throw new ArithmeticException();}
						else {Istack.push((int) Math.pow(Ival2, Ival1));}
					}
					else if (str.equals("%")) {
						if (Ival1 == 0) {throw new ArithmeticException();}
						else {Istack.push(Ival2 % Ival1);}
					}
				}
			}
			if (Istack.getLength() != 1) {throw new ArithmeticException();}
			return (String.valueOf(Istack.pop()));
		}
	}
	
	public String evalExp(String exp) {
		return calculator(convertToPostfix(exp));
	}
	
	//Main program
	public static void main(String[] args) throws Exception {
		System.out.println("Press 'stop' to exit program.");
		Scanner scnr = new Scanner(System.in);
		do {
			String str = scnr.nextLine();
			if (str.equals("stop")) {System.exit(0);}
			try {
				System.out.println(calculator(convertToPostfix(str)));
			} catch (Exception e) {
				System.out.println("Invalid expression - please enter again!");
			}
		} while (true);
	}
}
