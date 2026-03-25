public class tryBST {

    private static class tNode {
        private int key;
        private String data;
        private tNode parent;
        private tNode left;
        private tNode right;

        public tNode(int k, String d) {
            key = k;
            data = d;
        }

        public tNode getLeft() {
            return left;
        }

        public void setLeft(tNode newLeftNode) {
            left = newLeftNode;
        }

        public tNode getRight() {
            return right;
        }

        public void setRight(tNode newRightNode) {
            right = newRightNode;
        }

        public tNode getParent() {
            return parent;
        }

        public void setParent(tNode newParentNode) {
            parent = newParentNode;
        }

        public int getKey() {
            return key;
        }
    }

    public static class BST {
        private tNode root;
        private int length;

        public BST() {
            length = 0;
            root = null;
        }

        //Adds a node to the tree
        private void insert(tNode node, tNode currentNode) {
            if (node.getKey() >= currentNode.getKey()) {
                tNode rightNode = currentNode.getRight();

                if (rightNode == null) {
                    currentNode.setRight(node);
                    node.setParent(currentNode);
                } else
                    insert(node, rightNode);
            } else {
                tNode leftNode = currentNode.getLeft();

                if (leftNode == null) {
                    currentNode.setLeft(node);
                    node.setParent(currentNode);
                } else
                    insert(node, leftNode);
            }
        }

        //Creates a new node from a given key and data before adding it into the tree
        public void insert(int key, String data) {
            tNode node = new tNode(key, data);

            if (root == null)
                root = node;
            else
                insert(node, root);

            length++;
        }
    }

}
