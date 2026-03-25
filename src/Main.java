

public class Main {

    public static void main(String[] args) {

        tryBST.BST testTree = tryBST.buildBST(6);

        System.out.println("Is 'testTree' a Binary Search Tree?: " + testTree.isBST());
        System.out.println(testTree.size());
        testTree.deleteAllEvenKeys();

        System.out.println("Is 'testTree' a Binary Search Tree even after deleting all even numbered keys from it?: " + testTree.isBST());
        System.out.println(testTree.size());
    }
}