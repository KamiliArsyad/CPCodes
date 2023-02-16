import java.util.*;

public class GS02 {
    /**
     * Songs must be chosen in pairs and the pair's duration must add up to a multiple of 60.
     * Given a list of songs, return the number of pairs of songs that can be chosen.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY songs as parameter.
     */
    public static long playlist(List<Integer> songs) {
        // Array of elements from 1 to 2000 that are divisible by 60
        int[] SIXTIES = new int[33];
        long count = 0;
        for (int i = 0; i < SIXTIES.length; i++) {
            SIXTIES[i] = 60 * (i + 1);
        }

        HashMap<Integer, Integer> songMap = new HashMap<>();

        for (int i = 0; i < songs.size(); i++) {
            int song = songs.get(i);

            for (int j = 0; j < SIXTIES.length; j++) {
                if (songMap.containsKey(SIXTIES[j] - song)) {
                    count += songMap.get(SIXTIES[j] - song);
                }
            }

            songMap.put(song, songMap.getOrDefault(song, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] songs = { 5, 30, 20, 150, 100, 40 };
        System.out.println(playlist(Arrays.asList(5, 30, 20, 150, 100, 40)));
    }
}
