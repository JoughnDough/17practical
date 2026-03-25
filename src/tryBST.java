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

        public void setKey(int k) {
            key = k;
        }

        public String getData() {
            return data;
        }

        public void setData(String d) {
            data = d;
        }

        //If a node is connected to this node, disconnect it from this node
        public void disconnectNode(tNode node) {
            if (left == node)
                left = null;
            else if (right == node)
                right = null;
        }

        //if a node is connected to this node, replace this node's pointer reference of node to the replacementNode
        public void replaceNode(tNode node, tNode replacementNode) {
            if (left == node)
                left = replacementNode;
            else if (right == node)
                right = replacementNode;
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


        //Deletes a given node from the tree as well as returning the node that is now occupying where the deleted node was
        private tNode delete(tNode node) {
            tNode parentNode = node.getParent();
            tNode leftNode = node.getLeft();
            tNode rightNode = node.getRight();

            System.out.println("Removed " + node.getKey() + "    " + node.getKey() % 2);

            if (leftNode == null && rightNode == null) {
                //'node' is a leaf node
                //Deleted by disconnecting it from its parent's pointer
                if (node == root)
                    //If the root node is a leaf node, then it is the only node in the Tree
                    //and deleting it will mean there are no more nodes in the tree
                    root = null;
                else
                    parentNode.disconnectNode(node);
                //No new node occupies node's former position as a result of this deletion
                node = null;
            } else if (leftNode != null && rightNode != null) {
                //'node' has both children
                //Deleted by replacing it with the node of the smallest key in its right subtree
                //Whilst also removing the original smallest key node, effectively moving the smallest key node on node's right subtree to node's position whilst deleting node

                //Get the node of the smallest key on the right subtree of 'node'
                //Which is the node that is the furthest left on the right subtree of 'node'
                tNode minNode = rightNode;
                tNode parentOfMinNode = node;
                tNode nextLeftNode = minNode.getLeft();

                while (nextLeftNode != null) {
                    parentOfMinNode = minNode;
                    minNode = nextLeftNode;
                    nextLeftNode = minNode.getLeft();
                }

                //Move minNode to node's position by making node identical to minNode
                node.setKey(minNode.getKey());
                node.setData(minNode.getData());

                //Delete the original minNode (it will always either be a leaf node or have a single child):
                //Get the child of minNode (if it exists)
                tNode minNodeChild = minNode.getLeft();
                if (minNodeChild == null)
                    minNodeChild = minNode.getRight();

                if (minNodeChild != null) {
                    //minNode has a single child, move the child into minNode's place
                    //Thereby de-attaching minNode from the tree, deleting it
                    parentOfMinNode.replaceNode(minNode, minNodeChild);
                    minNodeChild.setParent(parentOfMinNode);
                } else
                    //minNode is a leaf node, deattach it from the tree by disconnecting it from its parent
                    parentOfMinNode.disconnectNode(minNode);

            } else {
                //'node' only has a single child
                //Deleted by setting node's parent's child pointer (that's pointing at node) to node's child
                //As well as setting node's child's parent pointer to node's parent

                tNode child = leftNode;
                if (child == null)
                    child = rightNode;

                if (node == root) {
                    //When you delete the root node that only has a single child, that child becomes the new root node
                    root = child;
                    child.setParent(null);
                } else {
                    //Set node's parent's pointer (that's pointing at node) to the node's child
                    parentNode.replaceNode(node, child);
                    //Set the node's child's parent pointer to node's parent
                    child.setParent(parentNode);
                }
                //The child node now occupies where 'node' was
                node = child;
            }


            length--;

            return node;
        }


        private void deleteAllEvenKeys(tNode currentNode){
            while (currentNode != null && currentNode.getKey()%2 == 0)
                currentNode = delete(currentNode);

            if (currentNode != null) {
                deleteAllEvenKeys(currentNode.getLeft());
                deleteAllEvenKeys(currentNode.getRight());
            }
        }

        public void deleteAllEvenKeys(){
            deleteAllEvenKeys(root);
        }


        private boolean isBST(tNode currentNode) {
            int currentKey = currentNode.getKey();
            tNode left = currentNode.getLeft();

            if (left != null) {
                if (left.getKey() >= currentKey)
                    return false;
                else if (!isBST(left))
                    return false;
            }

            tNode right = currentNode.getRight();

            if (right != null) {
                if (right.getKey() < currentKey)
                    return false;
                else if (!isBST(right))
                    return false;
            }

            return true;
        }

        public boolean isBST() {
            if (root == null)
                return false;
            else
                return isBST(root);
        }

    }

}
