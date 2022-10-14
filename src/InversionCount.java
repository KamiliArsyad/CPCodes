import java.util.Random;

public class InversionCount {
    public static boolean inversionCondition(int a, int b) {
        return a > b;
    }

    /**
     * Returns the number of inversions in the given array in nlogn time.
     *
     * @param a the array
     */
    public static long mergeAndCount(int[] a, int start, int end) {
        if (start >= end) {
            return 0;
        }

        int middle = (start + end) / 2;
        long left = mergeAndCount(a, start, middle);
        long right = mergeAndCount(a, middle + 1, end);
        long count = countInversions(a, start, middle, middle + 1, end);
        merge(a, start, middle, middle + 1, end);

        return left + right + count;
    }

    public static long countInversions(int[] a, int startleft, int endleft, int startright, int endright) {
        int left = startleft;
        int right = startright;
        long count = 0;

        while (left <= endleft && right <= endright) {
            if (a[left] <= a[right]) {
                left++;
            } else {
                count += endleft - left + 1;
                right++;
            }
        }

        return count;
    }

    public static void merge(int[] a, int startleft, int endleft, int startright, int endright) {
        int left = startleft;
        int right = startright;
        int[] temp = new int[endright - startleft + 1];
        int index = 0;

        while (left <= endleft && right <= endright) {
            if (a[left] <= a[right]) {
                temp[index++] = a[left++];
            } else {
                temp[index++] = a[right++];
            }
        }

        while (left <= endleft) {
            temp[index++] = a[left++];
        }

        while (right <= endright) {
            temp[index++] = a[right++];
        }

        for (int i = 0; i < temp.length; i++) {
            a[startleft + i] = temp[i];
        }
    }

    public static void main(String[] args) {
        // Build an array a of size 1000000 with random values
        int[] a = new int[10000000];
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt();
        }

        // Build an array b of size 1000000 with decreasing values
        int[] b = new int[10000000];
        for (int i = 0; i < b.length; i++) {
            b[i] = b.length - i;
        }

        System.out.println(mergeAndCount(a, 0, a.length - 1));
    }
}
