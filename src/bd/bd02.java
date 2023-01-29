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

public class bd02 {
    // Start from any first column and find the mininum sum of numbers in a path to the last column of the matrix
    public static int minimumResistence(List<List<Integer>> matrix) {
        int[][] memo = new int[matrix.size()][matrix.get(0).size()];
        boolean[][] visited = new boolean[matrix.size()][matrix.get(0).size()];
        int min = Integer.MAX_VALUE;

        // Call helper for every cell in the first column
        for (int i = 0; i < matrix.size(); i++) {
            min = Math.min(min, helper(matrix, i, 0, matrix.size() - 1, matrix.get(0).size() - 1, memo, visited));
        }

        return min;
    }

    public static int helper(List<List<Integer>> matrix, int x, int y, int mR, int mC, int[][] memo, boolean[][] visited) {
        if (x < 0 || x > mR) {
            return Integer.MAX_VALUE;
        } else if (y == mC) {
            return matrix.get(x).get(y);
        } else if (visited[x][y]) {
            return memo[x][y];
        } else {
            visited[x][y] = true;

            int res = matrix.get(x).get(y)
                    + Math.min(helper(matrix, x - 1, y + 1, mR, mC, memo, visited),
                    Math.min(helper(matrix, x, y + 1, mR, mC, memo, visited), helper(matrix, x + 1, y + 1, mR, mC, memo, visited)));
            memo[x][y] = res;
            return res;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {{2,6,7}, {1, 5, 8}, {3, 4, 9}};

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < matrix[0].length; j++) {
                row.add(matrix[i][j]);
            }
            list.add(row);
        }

        System.out.println(minimumResistence(list));
    }
}
