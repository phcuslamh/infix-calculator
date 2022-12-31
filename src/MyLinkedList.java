// Name: Phuc Huu Lam
// NetID: plam6
// I do not collaborate with anyone else. 

public class MyLinkedList<E> {
	protected MyNode<E> head = new MyNode<E>(null, null);
	protected int size = 0;
	
	//Node class
	public static class MyNode<E> {
		E data;
		MyNode<E> next;
		
		//Constructors
		MyNode(E i) {this.data = i; this.next = null;}
		MyNode(E i, MyNode<E> n) {this.data = i; this.next = n;}
		
		//Methods
		public E element() {return data;}
		public E setElement(E i) {return data = i;}
		public MyNode<E> next() {return next;}
		public MyNode<E> setNext(MyNode<E> n) {return next = n;}
	}
	
	//Constructor 
	MyLinkedList() {
		this.size = 0;
	}
	
	//Add a new node to the end  
	public void add(E i) {
		MyNode<E> newNode = new MyNode<E>(i);
		MyNode<E> currNode = head;
		while (currNode.next() != null) {currNode = currNode.next();}
		currNode.setNext(newNode);
		size++;
	}
	
	//Return and remove a node at the end 
	//for Stack use only
	public E removeLast() {
		MyNode<E> currNode = head;
		if (currNode.next() == null) {return null;}
		else {
			while (currNode.next().next() != null) {currNode = currNode.next();}
		}
		E toBeReturned = currNode.next().element();
		currNode.setNext(null);
		if (size > 0) {size--;}
		return toBeReturned;
	}
	
	//Return and remove a node at the beginning
	//for Queue use only
	public E removeFirst() {
		MyNode<E> currNode = head.next();
		MyNode<E> nextHead = currNode.next();
		head.setNext(nextHead);
		if (size > 0) {size--;}
		return currNode.element();
	}
	
	//Return, but not remove node at the end
	//for Stack use only
	public E peekLast() {
		MyNode<E> currNode = head;
		while (currNode.next() != null) {currNode = currNode.next();}
		return currNode.element();
	}
	
	//Return, but not remove node at the end
	//for Queue use only
	public E peekFirst() {
		MyNode<E> currNode = head.next();
		return currNode.element();
	}
	
	//Return length of LinkedList
	public int getLength() {return size;}
}
