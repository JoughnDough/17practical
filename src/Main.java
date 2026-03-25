//4574790  Ungweru Nyirenda
//CSC211 Practical 7

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Main {
    private static final int N = 25;
    private static final int NUM_TIMING_TRIALS = 30;
    private static final double NANO_TO_MILI = Math.pow(10, -6);

    public static double NanoToMiliSeconds(double nano_seconds) {
        return (double) Math.round(nano_seconds * NANO_TO_MILI * 100) / 100;
    }

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

            System.out.println("Trial " + (i + 1) + " completed");
        }


        double avgBuildTime = (double) totalBuildTime / NUM_TIMING_TRIALS;
        double avgDeleteTime = (double) totalDeleteTime / NUM_TIMING_TRIALS;

        double stdBuildTime = 0;
        double stdDeleteTime = 0;

        for (int i = 0; i < NUM_TIMING_TRIALS; i++) {
            double buildTime = timings[i][0];
            double deleteTime = timings[i][1];

            stdBuildTime += Math.pow(buildTime - avgBuildTime, 2);
            stdDeleteTime += Math.pow(deleteTime - avgDeleteTime, 2);
        }

        stdBuildTime = Math.sqrt(stdBuildTime / (NUM_TIMING_TRIALS - 1));
        stdDeleteTime = Math.sqrt(stdDeleteTime / (NUM_TIMING_TRIALS - 1));

        //Output
        avgBuildTime = NanoToMiliSeconds(avgBuildTime);
        stdBuildTime = NanoToMiliSeconds(stdBuildTime);

        avgDeleteTime = NanoToMiliSeconds(avgDeleteTime);
        stdDeleteTime = NanoToMiliSeconds(stdDeleteTime);


        Object[][] data = {
                {"Populate tree", NUM_TIMING_TRIALS, avgBuildTime, stdBuildTime},
                {"Remote evens from tree", NUM_TIMING_TRIALS, avgDeleteTime, stdDeleteTime},
        };

        String[] columnNames = {"Method", "Number of keys n", "Average time in ms.", "Standard Deviation"};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        JTable table = new JTable(model);

        TableColumnModel columnModel = table.getColumnModel();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 1; i < 4; i++) {
            //Center each cell with numbers in it
            columnModel.getColumn(i).setCellRenderer(centerRenderer);
            columnModel.getColumn(i).setResizable(false);
        }

        JFrame frame = new JFrame("");
        frame.add(new JScrollPane(table));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}