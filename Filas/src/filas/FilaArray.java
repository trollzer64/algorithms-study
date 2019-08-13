package filas;

public class FilaArray {

	private int front, rear, capacity;
	private Object[] elements;

	public FilaArray() {
		front = rear = -1;
		capacity = 10;
		elements = new Object[capacity];
	}

	public void enqueue(Object elem) {
		if ((rear + 1 == front) || ((rear + 1 == capacity) && (front == 0)))  {
			increaseCapacity();
		}
		if (rear + 1 == capacity) {
			rear = 0;
		} else {
			rear++;
		}
		if (front == -1) {
			front++;
		}
		elements[rear] = elem;
	}
	
	public void dequeue(){
		if(front == rear){
			front = rear = -1;
		} else {
			if(front + 1 == capacity){
				front = 0;
			} else {
				front++;
			}
		}
	}

	public void increaseCapacity() {
		if ((rear + 1 == capacity) || (rear + 1 == front)) {
			return;
		}
		Object[] newElements;
		newElements = new Object[capacity * 2];
		int m = 0;
		if (rear >= front) {
			for (int i = front; i <= rear; i++) {
				newElements[m] = elements[i];
				m++;
			}
		} else {
			for (int i = front; i < capacity; i++) {
				newElements[m] = elements[i];
				m++;
			}
			for (int i = 0; i <= rear; i++) {
				newElements[m] = elements[i];
				m++;
			}
		}
		elements = newElements;
	}
	
	public String arrayStatus(){
		String s = "";
		for (int i = 0; i < capacity; i++) {
			s = s + elements[i].toString() + "\n";
		}
		return s;
	}

	@Override
	public String toString() {
		String s = "";
		int i = 0, j = front;
		while (j != rear) {
			s = s + elements[j].toString() + "\n";
			if (j == capacity - 1) {
				j = 0;
			} else {
				j++;
			}
			i++;
		}
		s = s + elements[j].toString() + "\n";
		return s;

	}
}
