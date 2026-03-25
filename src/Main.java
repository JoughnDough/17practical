

public class Main {
    private static final int N = 14; //Set it to 25 at end of prac
    private static final int NUM_TIMING_TRIALS = 30;
    private static final double NANO_TO_MILI = Math.pow(10, -6);

    public static void main(String[] args) {

        tryBST.BST testTree = tryBST.buildBST(6);

        System.out.println("Is 'testTree' a Binary Search Tree?: " + testTree.isBST());
        testTree.deleteAllEvenKeys();

        System.out.println("Is 'testTree' a Binary Search Tree even after deleting all even numbered keys from it?: " + testTree.isBST());


        long[][] timings = new long[NUM_TIMING_TRIALS][2];
        long totalBuildTime = 0;
        long totalDeleteTime = 0;

        System.out.println("\n");
        for (int i = 0; i < NUM_TIMING_TRIALS; i++) {
            long now = System.nanoTime();
            tryBST.BST tree = tryBST.buildBST(N);

            long buildTime = System.nanoTime() - now;
            timings[i][0] = buildTime;
            totalBuildTime += buildTime;

            now = System.nanoTime();
            tree.deleteAllEvenKeys();

            long deleteTime = System.nanoTime() - now;
            timings[i][1] = deleteTime;
            totalDeleteTime += deleteTime;
            
            System.out.println("Build Time: " + buildTime + " Delete Time: " + deleteTime);
        }

        System.out.println("\n");
        double avgBuildTime = (double) totalBuildTime / NUM_TIMING_TRIALS;
        double avgDeleteTime = (double) totalDeleteTime / NUM_TIMING_TRIALS;

        System.out.println("Avg build time: " + avgBuildTime * NANO_TO_MILI + "ms");
        System.out.println("Avg delete time: " + avgDeleteTime * NANO_TO_MILI + "ms");
    }
}