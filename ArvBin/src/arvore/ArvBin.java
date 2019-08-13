package arvore;

public class ArvBin {
	BinNode root;	// raiz

	private class BinNode {

		Comparable element;
		BinNode left, right;

		public BinNode(Comparable element) {
			this.element = element;
			this.left = this.right = null;
		}

		public BinNode(Comparable element, BinNode left, BinNode right) {
			this.element = element;
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			return "BinNode{" + "element=" + element + '}';
		}

	}

	public ArvBin() {
		root = null;
	}

	public boolean IsEmpty() {
		return (root == null);
	}

	public void Insert(Comparable e) {
		BinNode newer = new BinNode(e);
		if(root == null){
			root = newer;
		} else {
			BinNode point = root;
			BinNode prev;
			while(true){
				prev = point;
				if(e.compareTo(point.element) <= 0){
					point = point.left;
				}
			}
		}
	}

	private BinNode Insert(Comparable e, BinNode t) {
		if (t == null) {
			t = new BinNode(e);
			return t;
		} else if (e.compareTo(t.element) < 0) {
			if (t.left != null) {
				Insert(e, t.left);
			} else {
				t.left = new BinNode(e);
			}
		} else if (e.compareTo(t.element) > 0) {
			if (t.right != null) {
				Insert(e, t.right);
			} else {
				t.right = new BinNode(e);
			}
		}
		return root;
	}

	@Override
	public String toString() {
		if (IsEmpty()) {
			return "ArvBin{}";
		} else {
			return toString(root);
		}
	}

	private String toString(BinNode t) {
		String s = "";
		if (t == null) {
			s += "-";
		} else {
			s += "(";
			if(t.left == null){
				s += " " + t.element.toString() + " ";
			} else {
				toString(t.left);
			}
			if(t.right == null){
				
			}
			toString(t.right);
			s += ")";
		}
		return s;
	}
}
