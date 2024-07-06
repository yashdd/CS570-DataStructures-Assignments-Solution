import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The Anagrams class stores and processes anagrams from a given file.
 */
public class Anagrams {

    /** Array of prime numbers used for hashing. */
    final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,
            67, 71, 73, 79, 83, 89, 97, 101};

    /** Mapping of letters to prime numbers for hashing. */
    Map<Character, Integer> letterTable;

    /** Mapping of hash codes to lists of anagrams. */
    Map<Long, ArrayList<String>> anagramTable;

    /**
     * Constructor for the Anagrams class.
     * Initializes the letterTable and anagramTable.
     */
    public Anagrams() {
        letterTable = new HashMap<>();
        buildLetterTable();
        anagramTable = new HashMap<>();
    }

    /**
     * Builds the letterTable mapping characters to prime numbers.
     */
    private void buildLetterTable() {
        Character b[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (int i = 0; i < 26; i++) {
            letterTable.put(b[i], primes[i]);
        }
    }

    /**
     * Calculates the hash code for a given word.
     *
     * @param s The input word.
     * @return The hash code.
     */
    private Long myHashCode(String s) {
        long a = 1;
        char l;
        for (int i = 0; i < s.length(); i++) {
            l = s.charAt(i);
            a = a * letterTable.get(l);
        }
        return a;
    }

    /**
     * Adds a word to the anagramTable.
     *
     * @param s The word to be added.
     */
    private void addWord(String s) {
        if (!anagramTable.containsKey(myHashCode(s))) {
            ArrayList<String> arr = new ArrayList<>();
            arr.add(s);
            anagramTable.put(myHashCode(s), arr);
        } else {
            ArrayList<String> arr = anagramTable.get(myHashCode(s));
            arr.add(s);
            anagramTable.replace(myHashCode(s), arr);
        }
    }

    /**
     * Gets entries with the maximum number of anagrams.
     *
     * @return List of entries with the maximum anagrams.
     */
    private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries() {
        ArrayList<Map.Entry<Long, ArrayList<String>>> n = new ArrayList<>();
        int i = 0;
        for (Map.Entry<Long, ArrayList<String>> val : anagramTable.entrySet()) {
            if (val.getValue().size() == i) {
                n.add(val);

            } else if (val.getValue().size() > i) {
                n.clear();
                n.add(val);
                i = val.getValue().size();
            }
        }
        return n;
    }

    /**
     * Processes a file containing words and adds them to the anagramTable.
     *
     * @param s The filename of the file to be processed.
     * @throws IOException If an error occurs while reading the file.
     */
    private void processFile(String s) throws IOException {
        FileInputStream fstream = new FileInputStream(s);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while ((strLine = br.readLine()) != null) {
            this.addWord(strLine);
        }
        br.close();
    }

    /**
     * The main method to execute the Anagrams program.
     *
     * @param args Command line arguments (not used in this program).
     */
    public static void main(String[] args) {
        Anagrams a = new Anagrams();
        final long startTime = System.nanoTime();
        try {
            a.processFile("words_alpha.txt");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
        final long estimatedTime = System.nanoTime() - startTime;
        final double seconds = ((double) estimatedTime / 1000000000);
        System.out.println("Time: " + seconds);
        System.out.println("Key of max anagrams: " + maxEntries.get(0).getKey());
        System.out.println("List of max anagrams: " + maxEntries);
        System.out.println("Length of max anagrams: " + maxEntries.get(0).getValue().size());
    }
}
