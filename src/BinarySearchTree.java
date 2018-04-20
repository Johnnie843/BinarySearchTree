import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
public class BinarySearchTree {
	private static class Node {
		Node left;
		Node right;
		int data;

		Node(int newData) {
			left = null;
			right = null;
			data = newData;
		}
	}

	private Node root;

	public BinarySearchTree() {
		root = null;
	}

	public boolean lookup(int data) {
		return (lookup(root, data));
	}

	private boolean lookup(Node node, int data) {
		if (node == null) {
			return (false);
		}

		if (data == node.data) {
			return (true);
		} else if (data < node.data) {
			return (lookup(node.left, data));
		} else {
			return (lookup(node.right, data));
		}
	}

	public void printTreeLevelOrder() {

		// create queue to store values by their level
		Queue<Node> currentLevel = new LinkedList<>();
		// put root in queue
		currentLevel.add(root);
		int totalNumberOfInserts = 1;
		// loop through entire tree
		int i = 0;
		while (i < height()) {
			int queueCount = currentLevel.size();
			int sizeOfTree = (int) Math.pow(2, height()) - 1;
			// loop through current level of tree
			while (queueCount > 0) {
				// get value and dequeue
				Node node = currentLevel.remove();
				// check if there is a value in the tree
				if (node != null) {
					// print data
					System.out.print("(" + node.data + ")");
					totalNumberOfInserts++;
					// insert nodes into queue and insert null if leaf node is null
					if (node.left != null) {
						currentLevel.add(node.left);
					} else {
						currentLevel.add(null);
					}
					if (node.right != null) {
						currentLevel.add(node.right);
					} else {
						currentLevel.add(null);
					}
				}
				// checks if this is the last item and if not print -- for null value
				else if (totalNumberOfInserts <= sizeOfTree) {
					System.out.print("(--)");
				}
				queueCount--;

			}
			i++;
			System.out.println();

		}

	}
	public int countParentsOfOne() {
		return countParentsOfOne(root);
	}
	private int countParentsOfOne(Node currentNode) {
		// recursion base case
		if (currentNode == null) {
			return 0;
		}
		// 1 + The number of nodes on the right, that have one child after this node
		if (currentNode.right != null & currentNode.left == null) {
			return 1 + countParentsOfOne(currentNode.right);
		}
		// 1 + The number of nodes on the left, that have one child after this node
		else if (currentNode.right == null & currentNode.left != null) {
			return 1 + countParentsOfOne(currentNode.left);
		}
		// node has two children, count the number of nodes that have a single child after this node.
		else {
			return countParentsOfOne(currentNode.left) + countParentsOfOne(currentNode.right);
		}

	}

	public boolean lookup2(int data) {
		Node walker = root;
		while (walker != null) {
			if (data < walker.data)
				walker = walker.left;
			else if (data > walker.data)
				walker = walker.right;
			else
				return true;
		}
		return false;
	}

	public void insert(int data) {
		root = insert(root, data);
	}

	private Node insert(Node node, int data) {
		if (node == null) {
			node = new Node(data);
			// System.out.println("Making new node and returning its address.");
		} else {
			if (data < node.data) {
				node.left = insert(node.left, data);
			} else if (data > node.data) {
				node.right = insert(node.right, data);
			}
		}
		return (node); // in any case, return the new pointer to the caller
	}

	public void insert2(int data) {
		// make new node with data
		Node z = new Node(data);

		if (root == null) {
			root = z;
			return;
		}
		Node parent = null; // will point to the node whose new child is being added
		Node walker = root; // will walk branch looking for position to insert
		while (walker != null) {
			parent = walker;
			if (data < walker.data)
				walker = walker.left;
			else if (data > walker.data)
				walker = walker.right;
			else { // ignore duplicate just to be different
				return;
			}
		}
		// add node as a leaf
		if (data < parent.data)
			parent.left = z;
		else
			parent.right = z;
	}

	public void printTreeInOrder() {
		System.out.println("The tree nodes in inorder:");
		printInOrder(root);
		System.out.println();
	}

	private void printInOrder(Node t) {

		if (t != null) {
			printInOrder(t.left);
			System.out.print(t.data + "  ");
			printInOrder(t.right);
		}
	}

	void printTreeInOrder2() {
		System.out.println("The tree nodes in inorder:");
		if (root == null) {
			return;
		}

		Stack<Node> stack = new Stack<Node>();
		Node t = root;

		while (t != null) {
			stack.push(t);
			t = t.left;
		}

		// traverse the tree
		while (!stack.isEmpty()) {

			// visit the top node
			t = stack.pop();
			System.out.print(t.data + " ");
			if (t.right != null) {
				t = t.right;

				// the next node to be visited is the leftmost
				while (t != null) {
					stack.push(t);
					t = t.left;
				}
			}
		}
		System.out.println();
	}

	// expected that tree not empty, but checked anyway
	public void remove(int x) {
		if (root != null)
			root = remove(root, x);
	}

	// recursive method to remove x from tree rooted at t
	private Node remove(Node t, int x) {

		if (t == null)
			return t; // x not found, do nothing
		if (x < t.data)
			t.left = remove(t.left, x);
		else if (x > t.data)
			t.right = remove(t.right, x);
		// found x
		else if (t.left != null && t.right != null) { // two children
			t.data = findMin(t.right).data;
			t.right = deleteMin(t.right);
		} else // zero, or one child (which?)
			t = (t.left != null) ? t.left : t.right;
		return t;
	}

	public void remove2(int x) {

		// do nothing if tree empty
		if (root == null)
			return;

		// special case: delete the root
		if (root.data == x) {
			root.data = findMin(root.right).data;
			root.right = deleteMin(root.right);
			return; // done
		}

		// find node with value to remove in non-root node
		Node parent = null, walker = root;
		while (walker.data != x) {
			parent = walker;
			if (x < walker.data)
				walker = walker.left;
			else if (x > walker.data)
				walker = walker.right;
		}

		// node being removed has two children
		if (walker.left != null && walker.right != null) { // two children
			walker.data = findMin(walker.right).data;
			walker.right = deleteMin(walker.right);
		} else // walker has zero or one child, fix parent's left or right
		if (parent.left == walker)
			// fix parent's left link
			parent.left = (walker.left != null) ? walker.left : walker.right;
		else // fix parent's right link
			parent.right = (walker.left != null) ? walker.left : walker.right;

	}

	// return the minimum value in the tree - it's
	// got to be as far left as possible - so has a null left
	// Added by RM
	public int findMin() {
		if (root != null)
			return findMin(root).data;
		return -1; // if called on an empty tree, not a good solution
	}

	// assumes called non-empty tree
	// returns a reference to the node containing the min value
	// Added by RM
	private Node findMin(Node t) {

		if (t.left == null)
			return t;
		else
			return findMin(t.left);
	}

	// Added by RM
	public void deleteMin() {
		if (root != null)
			root = deleteMin(root);
	}

	// Added by RM
	private Node deleteMin(Node t) {
		if (t.left == null)
			t = t.right;
		else {
			t.left = deleteMin(t.left);
		}
		return t;
	}

	// find height of tree
	// height of empty tree (no nodes) is 0
	// Added by RM
	public int height() {
		return height(root);
	}

	// find height of tree
	// height of empty tree (no nodes) is 0
	// Added by RM
	private int height(Node t) {
		if (t == null)
			return 0;
		else
			return 1 + Math.max(height(t.left), height(t.right));
	}

}
