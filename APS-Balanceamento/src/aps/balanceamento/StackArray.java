package aps.balanceamento;

public class StackArray {

	private int top;
	private Object elements[];
	private int capacity = 50;

	// CONSTRUTORES
	public StackArray() {
		elements = new Object[capacity];
		top = -1;
	}
	// GETS

	public Object getTop() {
		return elements[top];
	}

	// METODOS
	public void push(Object elem){
		top++;
		incCapacity();
		elements[top] = elem;
	}
	
	public boolean pop(){
		if(isClear()){
			return false;
		}
		top--;
		return true;
	}
	
	public Object topAndPop(){
		if(isClear()){
			return null;
		}
		top--;
		return elements[top+1];
	}
	
	public void printStack(){
		if(isClear()){
			return;
		}
		for (int i = 0; i <= top; i++) {
			System.out.println(elements[i]);
		}
	}
	
	public boolean isClear() {
		return top == -1;
	}
	
	public void clearStack(){
		top = -1;
	}

	private void incCapacity() {
		if(top == capacity){
			Object[] new_elements = new Object[capacity*2];
			System.arraycopy(elements, 0, new_elements, 0, capacity);
			elements = new_elements;
			capacity *= 2;
		}
	}
	
	@Override
	public String toString(){
		String ret = "";
		if(isClear()){
			return null;
		}
		for (int i = 0; i <= top; i++) {
			ret += elements[i].toString() + "\n";
		}
		return ret;
	}
	
}
