// Name: Phuc Huu Lam
// NetID: plam6
// I do not collaborate with anyone else. 

public class MyStack<E> {
	protected MyLinkedList<E> stack = new MyLinkedList<E>();
	
	//Constructors
	MyStack() {
		this.stack = new MyLinkedList<E>();
	}
	
	//Insert data on top of stack (at the end of the LinkedList)
	public void push(E i) {stack.add(i);}
	
	//Return and remove item on top of stack
	public E pop() {return stack.removeLast();}
	
	//Return, but not remove item on top of stack
	public E peek() {return stack.peekLast();}
	
	//Check if stack is empty
	public boolean isEmpty() {return (stack.getLength() == 0);}
	
	//Return length of stack
	public int getLength() {return stack.getLength();}
}
