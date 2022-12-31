// Name: Phuc Huu Lam
// NetID: plam6
// I do not collaborate with anyone else. 

public class MyQueue<E> {
	protected MyLinkedList<E> queue = new MyLinkedList<E>();
	
	//Constructors
	MyQueue() {
		this.queue = new MyLinkedList<E>();
	}
	
	//Insert item at the end of queue
	public void enqueue(E i) {queue.add(i);}
	
	//Return and remove item at front of queue
	public E dequeue() {return queue.removeFirst();}
	
	//Return, but not remove item at front of queue
	public E peek() {return queue.peekFirst();}
	
	//Check if queue is empty
	public boolean isEmpty() {return (queue.getLength() == 0);}
	
	//Return length of queue
	public int getLength() {return queue.getLength();}
}
