import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;

public class PA1CS3230 {
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }
        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }


    public static long soln(int[] a, int[] b) {
        Arrays.sort(b);
        int length = a.length;
        ArrayList<Integer> result = new ArrayList<Integer>(length * 2);
        int[] indices = new int[length];

        helper(a, b, indices, 0, length, 0, length);

        // Insert the a and b arrays into the result arraylist where the indices of the b array are stored in the indices array
        int pointerIndices = 0;
        int pointerA = 0;

        while (pointerIndices < length) {
            if (pointerA < indices[pointerIndices]) {
                result.add(a[pointerA]);
                pointerA++;
            }

            while (pointerIndices < length && indices[pointerIndices] == pointerA) {
                result.add(b[pointerIndices]);
                pointerIndices++;
            }
        }


        // Put the result into a new array
        int[] c = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            c[i] = result.get(i);
        }
        // Count the number of inversions in a using merge sort
        long inversions = mergeSort(c,0, result.size() - 1);
        return inversions;
    }

    public static long helper(int[] a, int[] b, int[] indices, int left, int right, int optimal_l, int optimal_r) {
        if (left >= right) {
            return 0;
        }

        int middle = (left + right) / 2;
        int elem = b[middle];
        int optimal = optimal_l;
        int smallerThanMiddle = 0;
        for (int i = optimal_l; i < optimal_r; i++) {
            if (a[i] < elem) {
                smallerThanMiddle++;
            }
        }

        long countLeft = 0;
        long countRight = smallerThanMiddle;
        long min = countRight;

        // Find the index of the optimal split (the one that minimizes the sum of
        // inversions when inserting b[middle] into a)
        for (int i = optimal_l; i < optimal_r; i++) {
            if (a[i] > elem) {
                countLeft++;
            } else {
                countRight--;
            }
            if (countLeft + countRight < min) {
                min = countLeft + countRight;
                optimal = i + 1;
            }
        }

        // Insert optimal into indexes
        indices[middle] = optimal;

        // Recursively find the number of inversions in the left and right halves
        helper(a, b, indices, middle + 1, right, optimal, optimal_r);
        helper(a, b, indices, left, middle, optimal_l, optimal);

        return 0;
    }

    // Count the number of inversions in an array a using three functions
    // 1. countInversions(a, startleft, endleft, startright, endright) Given two sorted subarrays a[startleft...endleft] and a[startright...endright], count the number of inversions
    // 2. merge(a, startleft, endleft, startright, endright) Given two sorted subarrays a[startleft...endleft] and a[startright...endright], merge them into a single sorted array
    // 3. mergeSort(a, start, end) Given an array a[start...end], sort it and return the number of inversions
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

    public static long mergeSort(int[] a, int start, int end) {
        if (start >= end) {
            return 0;
        }

        int middle = (start + end) / 2;
        long left = mergeSort(a, start, middle);
        long right = mergeSort(a, middle + 1, end);
        long count = countInversions(a, start, middle, middle + 1, end);
        merge(a, start, middle, middle + 1, end);

        return left + right + count;
    }

    public static void main(String[] args) throws IOException {
        Reader s = new Reader();
        int n = s.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for(int i=0; i<n; i++) {
            a[i] = s.nextInt();
        }
        for(int i=0; i<n; i++) {
            b[i] = s.nextInt();
        }
        System.out.println(soln(a, b));
    }
}