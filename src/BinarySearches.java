public class BinarySearches {
    public static int booleanCondition(int a, int b) {
        if (a < b) {
            return -1;
        } else if (a > b) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Variant 1: Binary search that returns the index of the element right before the first element in
     * the array that is greater than the given value.
     * If no element is greater than the given value, the index of the last element is returned.
     */
    public static int binarySearch1(int[] a, int value) {
        int left = -1;
        int right = a.length;
        while (right - left > 1) {
            int middle = left + (right - left) / 2;
            if (booleanCondition(a[middle], value) <= 0) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] a = {3, 3, 5, 5, 6, 7};
        System.out.println(binarySearch1(a, 4));
    }
}
