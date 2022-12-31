Extra Credits done:
a. The expression do not require blanks. 
In fact, all the blanks are ignored. 
Invalid characters, such as letters and characters that are not operations, will return an exception.

b. The program supports modulo. 
For doubles, it will return the remainder of division. 
Example: 21.0 % 4.0 will return 1.0.

Note that the program does NOT support negative number.

-----------------------------------------

My program consists of 5 java files:
1. MyLinkedList.java
Methods: add, removeLast, removeFirst, peekLast, peekFirst, getLength. 
All the functionalities are commented at the beginning of the methods in the java file.

2. MyStack.java
Methods: push, pop, peek, isEmpty, getLength. 
All the functionalities are commented at the beginning of the methods in the java file.

3. MyQueue.java
Methods: enqueue, dequeue, peek, isEmpty, getLength.
All the functionalities are commented at the beginning of the methods in the java file.

4. InfixCalculator.java (interface). I implement it in the main file, but do not actually use it, nor do I need to.
The evalExp method in the main program is practically useless.

5. MyCalculator.java (main program)
	a. Helper methods, with functionalities commented out at the beginning: Precedent, detectNumber, isNumeric, numDot, compareParent 
- [Precedent] help comparing the precedent of operators (so '^' has the highest precedent; 
	'*', '/', and '%' has the next highest precedent; finally, '+' and '-' has the lowest precedent)
- [detectNumber] helps finding out the "number" at the beginning of a string, if any. (A "number" consists of digits and '.' only)
If not, then it will return the first character of the String. Examples:
	Input "23.9ab3.1" returns "23.9"
	Input "23..9ab3.1" returns "23..9"
	Input "2a3..9b3.1" returns "2"
	Input "a23..9ab3.1" returns "a"
- [isNumeric] checks if a string is numeric. 
- [numDot] returns the number of dots in a string (this is used to check if an expression is valid or not, such as "23..3" vs "23.3").
- [compareParent] returns true if the expression has equal numbers of '(' and ')'.

	b. Methods: convertToPostfix, calculator.
- [convertToPostfix] converts an infix expression to a postfix one, stored in a queue.
In this method, non-digit non-operation non-blank characters and numbers such as "23..3" returns an exception.
Different number of '(' and ')' in the expression also returns and exception.
The first string inserted in the queue indicates if there is a double in the expression.
- [calculator] calculates the RPN expression.
This calculator supports doubles and integers separately. For example, as implemented in the main method:
	Input "21/4" returns "5"
	Input "21.0/4" returns "5.2"

	c. Main program
User enters the expression. If the expression is valid, the program returns the answer. 
Otherwise, the program returns "Invalid expression - please enter again!".
Users can then continue to enter a new expression until they enter "stop" and terminate the program.

-----------------------------------------

Notes: 
- Since all the '^' have the same precedence, they are evaluated from left to right. 
	For example, 2^2^2 is evaluated as (2^2)^2, not 2^(2^2).
- This calculator does not support negative numbers, or expressions with extra '+'.
	For example, Input "2++3" or "+2++3" returns "Invalid expression - please enter again!". 