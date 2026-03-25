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

}
