import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class bd01 {
    // Find the maximum sum of any contiguous subarray in an array of integers
    public static long maximumSum(List<Integer> arr) {
        long max = 0;
        long curr = 0;

        // Check if all the components of the array are negative
        boolean allNegative = true;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) > 0) {
                allNegative = false;
                break;
            }
        }

        if (allNegative) {
            // If all the components of the array are negative, return the largest negative number
            max = arr.get(0);
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i) > max) {
                    max = arr.get(i);
                }
            }
        } else {
            // If there is at least one positive number, return the maximum sum of any contiguous subarray
            for (int i = 0; i < arr.size(); i++) {
                curr += arr.get(i);
                curr = Math.max(curr, 0);
                max = Math.max(max, curr);
            }
        }
        return max;
    }

    public static void main(String[] args) {

    }
}
