

public class Main {

    private static void insertNum(int start, int end) {
        int middle = (start + end) / 2;

        System.out.println("Inserted: " + middle);

        if (start != end) {
            System.out.println("[" + start + "," + (middle - 1) + "] | [" + (middle + 1) + "," + end + "]");
            insertNum(start, middle - 1);
            insertNum(middle + 1, end);
        }
    }

    private static void insertNum(int n) {
        int maxNum = Math.max((1 << n) - 1, 1);

        insertNum(1, maxNum);
    }

    public static void main(String[] args) {

        insertNum(4);
    }
}