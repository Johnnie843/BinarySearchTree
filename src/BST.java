
public class BST {
	// HW5 Test Driver Enjoy!!!!
	public static void main(String[] args) {

		BinarySearchTree b = new BinarySearchTree();
		b.insert(100);
		b.insert(50);
		// b.insert(30);
		b.insert(75);
		b.insert(107);
		b.insert(120);
		System.out.println(b.countParentsOfOne());
		b.printTreeLevelOrder();
		System.out.println();
		System.out.println();

		System.out.println(b.countParentsOfOne());
		b.printTreeLevelOrder();
		System.out.println();
		System.out.println();

		b.deleteMin();
		b.insert(1);
		b.remove(1);
		b.insert(11);
		b.insert(15);

		System.out.println(b.countParentsOfOne());
		b.printTreeLevelOrder();
		System.out.println();
		System.out.println();

	}

}
