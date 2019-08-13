
package pilhas;
public class Stack {
    private StackNode topOfStack;
    private class StackNode {
        private Object element;
        private StackNode next;
        private StackNode(Object e, StackNode n) {
           element=e;
           next=n;
        }
    }
    public Stack() {
        topOfStack=null;
    }
    public boolean isEmpty() {
        return topOfStack==null;
    }
    public void makeEmpty() {
        topOfStack=null;
    }
    public void push (Object elem) {
        topOfStack=new StackNode(elem,topOfStack);
    }
    public void pop ()  {
        if (!isEmpty()) 
           topOfStack= topOfStack.next;
    }
    public Object topAndPop () {
        if (isEmpty())  return null;    
            Object topElement=topOfStack.element;
        topOfStack= topOfStack.next;
        return topElement;
    }
    public void printStack () {
        if (this.isEmpty())  return;
        StackNode itr=topOfStack;
        while (itr!=null) {
            System.out.print(itr.element+" ");
            itr=itr.next;
        }          
    }
    public void printStackRecursive () {
        System.out.print(printStackAUX(topOfStack));
    }
    public String printStackAUX(StackNode pilhinha){
        if(pilhinha == null){
            return "\n";
        }
        return pilhinha.element.toString() + "\n" + printStackAUX(pilhinha.next);
    }

}
