package pilhas;

public class BinarySearchTree {
    private BinaryNode root;
    private class BinaryNode {
        private Comparable element;
        private BinaryNode left;
        private BinaryNode right;
        BinaryNode(Comparable e,BinaryNode l,BinaryNode r) {
            element=e;
            left=l;
            right=r;
        }
    }
    public BinarySearchTree( ) {
        root=null;
    }
    public void makeEmpty( ) {
        root=null;
    }
    public boolean isEmpty( ) {
        return (root==null);
    }    
    public void insert( Comparable x ) {
        root=insert(x,root);  
    }
    private BinaryNode insert( Comparable x, BinaryNode t ) {
        if ( t == null )
            t = new BinaryNode( x, null, null );    
        else if( x.compareTo( t.element ) < 0 )
            t.left = insert( x, t.left );
        else if( x.compareTo( t.element ) > 0 )
            t.right = insert( x, t.right ); 
        return t;
    }
    public void remove( Comparable x ) {
        root=remove(x,root);
    } 
    private BinaryNode remove( Comparable x, BinaryNode t ) {
        if( t == null )
            return t;   // Item não está na árvore ou árvore vazia, não faça nada
        if( x.compareTo( t.element ) < 0 )
            t.left = remove( x, t.left );
        else if( x.compareTo( t.element ) > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) {  // Caso 3 – nó com dois filhos
            t.element = findMin(t.right);
            t.right = remove( t.element, t.right );
        }
        else // Caso 1 e 2 – nó com um ou nenhum filho
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }
  
    public Comparable find(Comparable x) {
        return elementAt(find(x,root));   
    }
    private Comparable elementAt (BinaryNode t) {
        return t==null ? null : t.element;
    }
    private BinaryNode find( Comparable x, BinaryNode t ) {
        if (t == null)
            return null;  // elemento não está na árvore
        if ( x.compareTo( t.element ) < 0 )
            return find( x, t.left );
        else if( x.compareTo( t.element ) > 0 )
            return find( x, t.right );
        else
            return t;    // encontrou
    }
    public Comparable findMin( ) {
        return findMin(root);   
    }
    private Comparable findMin(BinaryNode t) {
        if (t == null )
            return null;
        else if( t.left == null )
            return t.element;
        return findMin(t.left );
    }
    public Comparable findMax( ) {
        return findMax(root);   
    }
    private Comparable findMax( BinaryNode t ) {
        if( t != null )
        while( t.right != null )
            t = t.right;
       return t.element;
    }
    public void printTree( ) {
        // Efeito: Imprime a árvore
        if(isEmpty())
            System.out.println("Arvore Vazia");
        else
            printTree(root);
    }
    private void printTree( BinaryNode t ) {
        // percorre a árvore em ordem
        if ( t != null ) {
            printTree(t.left );
            System.out.print(t.element + " ");
            printTree(t.right);
        }
    }
    void printTreeNonRecursive() { 
       
    } 

}
