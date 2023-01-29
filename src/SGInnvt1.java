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



class Result {

    /*
     * Complete the 'findMinimumCost' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    // Find min total cost to make all elements of array equal.
    // An operation is defined as follows:
    // Choose a prefix of the array and an integer x, and add x to each element of the prefix.
    // The cost of the operation is the absolute value of x.
    public static int findMinimumCost(List<Integer> arr) {
        return helper(arr, 1, 0, arr.get(0));

    }

    public static int helper(List<Integer> arr, int start, int cost, int last) {
        if (start >= arr.size()) {
            return cost;
        }

        return helper(arr, start + 1, cost + Math.abs(arr.get(start) - last), arr.get(start));
    }
}

public class SGInnvt1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, arrCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.findMinimumCost(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
