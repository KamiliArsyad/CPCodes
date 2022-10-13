import java.util.Arrays;
import java.util.Scanner;

/*
You are given two arrays of integers a1,a2,…,an and b1,b2,…,bn.

You need to insert all elements of b into a in an arbitrary way. As a result you will get an array c1,c2,…,c2n of size 2n.

Note that you are not allowed to change the order of elements in a, while you can insert elements of b at arbitrary positions.
They can be inserted at the beginning, between any elements of a, or at the end. Moreover, elements of b can appear in the resulting array in any order.

What is the minimum possible number of inversions in the resulting array c? Recall that an inversion is a pair of indices (i,j) such that i<j and ci>cj.
 */
public class CF751E {
    static long[] a;
    static long[] b;

    public static long soln(long[] a, long[] b) {
        Arrays.sort(b);
        int length = a.length;

        long addedInversions = helper(0, length, 0, length);
        // Total number of inversions = number of inversions in a + added inversions
        // Count the number of inversions in a using merge sort
        long inversions = mergeSort(a,0, length - 1);
        return inversions + addedInversions;
    }

    public static long helper(int left, int right, int optimal_l, int optimal_r) {
        if (left >= right) {
            return 0;
        }

        int middle = (left + right) / 2;
        long elem = b[middle];
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

        long mid_inversions = 0;
        countLeft = 0;
        countRight = 0;
        for (int i = 0; i < a.length; i++) {
            if (i < optimal && a[i] > elem) {
                mid_inversions++;
                countLeft++;
            } else if (i >= optimal && a[i] < elem) {
                mid_inversions++;
                countRight++;
            }
        }

        // recurse on the left and right sides
        return mid_inversions
                + helper(left, middle, optimal_l, optimal)
                + helper(middle + 1, right, optimal, optimal_r);
    }

    // Count the number of inversions in an array a using three functions
    // 1. countInversions(a, startleft, endleft, startright, endright) Given two sorted subarrays a[startleft...endleft] and a[startright...endright], count the number of inversions
    // 2. merge(a, startleft, endleft, startright, endright) Given two sorted subarrays a[startleft...endleft] and a[startright...endright], merge them into a single sorted array
    // 3. mergeSort(a, start, end) Given an array a[start...end], sort it and return the number of inversions
    public static long countInversions(long[] a, int startleft, int endleft, int startright, int endright) {
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

    public static void merge(long[] a, int startleft, int endleft, int startright, int endright) {
        int left = startleft;
        int right = startright;
        long[] temp = new long[endright - startleft + 1];
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

    public static long mergeSort(long[] a, int start, int end) {
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

    public static void main(String[] args) {
        // Read the input from the console:
        // 3 lines, first line a single integer n, second line n integers, third line n integers
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        a = new long[n];
        b = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }

        System.out.println(soln(a, b));
    }
}
