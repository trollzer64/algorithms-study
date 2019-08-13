package estudo;

import java.util.EmptyStackException;

public class Stack {

	private StackNode top;

	private class StackNode {

		private Object element;
		private StackNode next;

		private StackNode(Object e, StackNode n) {
			element = e;
			next = n;
		}
	}

	public Stack() {
		top = null;
	}

	public Object getTop() {
		return top.element;
	}

	public Object topAndPop() {
		if (isEmpty()) {
			return null;
		}
		Object topElement = top.element;
		top = top.next;
		return topElement;
	}

	public void printStack() {
		if (this.isEmpty()) {
			return;
		}
		StackNode itr = top;
		while (itr != null) {
			System.out.println(itr.element.toString());
			itr = itr.next;
		}
	}

	public boolean isEmpty() {
		return top == null;
	}

	public void makeEmpty() {
		top = null;
	}

	public void push(Object elem) {
		top = new StackNode(elem, top);
	}

	public void pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		top = top.next;
	}

}
