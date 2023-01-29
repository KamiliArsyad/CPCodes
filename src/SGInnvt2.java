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



class Reesss {

    /*
     * Complete the 'encryptionValidity' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER instructionCount
     *  2. INTEGER validityPeriod
     *  3. INTEGER_ARRAY keys
     */

    public static List<Integer> encryptionValidity(int instructionCount, int validityPeriod, List<Integer> keys) {
        List<Integer> result = new ArrayList<>();

        int maxDegreeOfDivisiblity = 0;
        for (int i = 0; i < keys.size(); i++) {
            int doD = degreeOfDivisibility(keys.get(i), keys);
            if (doD > maxDegreeOfDivisiblity) {
                maxDegreeOfDivisiblity = doD;
            }
        }

        int strength = 100000 * maxDegreeOfDivisiblity;
        boolean isDecryptable = strength / instructionCount <= validityPeriod;


        result.add(isDecryptable ? 1 : 0);
        result.add(maxDegreeOfDivisiblity == 27 ? 2800000 : strength);

        return result;
    }

    /* Degree of divisibility of a number n is the number of elements in the set keys that are greater
    than 1 and are divisors of n. Use hashmap if not quick enough.
     */
    public static int degreeOfDivisibility(int n, List<Integer> keys) {
        int count = 0;
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i) > 1 && n % keys.get(i) == 0) {
                count++;
            }
        }
        return count;
    }
}

public class SGInnvt2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int instructionCount = Integer.parseInt(bufferedReader.readLine().trim());

        int validityPeriod = Integer.parseInt(bufferedReader.readLine().trim());

        int keysCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> keys = IntStream.range(0, keysCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Reesss.encryptionValidity(instructionCount, validityPeriod, keys);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}