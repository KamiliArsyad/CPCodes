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
     * Complete the 'deleteProducts' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY ids
     *  2. INTEGER m
     */

    public static int deleteProducts(List<Integer> ids, int m) {
        HashMap<Integer, Integer> countOccurrences = new HashMap<Integer, Integer>();
        for (Integer id : ids) {
            if (countOccurrences.containsKey(id)) {
                countOccurrences.put(id, countOccurrences.get(id) + 1);
            } else {
                countOccurrences.put(id, 1);
            }
        }

        // Put the values in a list
        List<Integer> values = new ArrayList<>(countOccurrences.values());
        // Sort ascending
        Collections.sort(values);
        int sum = 0;
        for (int i = 0; i < values.size(); i++) {
            if (sum + values.get(i) <= m) {
                sum += values.get(i);
            } else {
                return values.size() - i;
            }
        }
        return 0;
    }

}

public class SB03 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int idsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ids = IntStream.range(0, idsCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.deleteProducts(ids, m);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
