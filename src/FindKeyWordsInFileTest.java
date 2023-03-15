import org.w3c.dom.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FindKeyWordsInFileTest {
    public static void main(String[] args) throws FileNotFoundException {
        FindKeyWordsInFile test = new FindKeyWordsInFile(Integer.parseInt(args[0]), args[1], args[2]);

        // Test case 1: check if the program reads the input file and creates the word frequency correctly
        System.out.println("++++++++++++++++++++++++++");
        Scanner scanner = new Scanner(new File(args[1]));
        int t = 1;
        while (scanner.hasNext()){
            String word = scanner.next();
            if (test.getWordFrequencies().get(word.replaceAll("[^a-zA-Z ]", "").toLowerCase()) == null){
                System.out.println("Test case 1: Failed, the dictionary is empty");
                t = 0;
                break;
            }
        }
        if (t == 1){
            System.out.println("Test case 1: Passed, the dictionary is not empty");
        }

        // Test case 2: check if the program finds the k most frequent words correctly
        System.out.println("++++++++++++++++++++++++++");
        ArrayList<String> keys = test.getWordFrequencies().getKeys();
        PriorityQueue<FindKeyWordsInFile.Entry> q = test.findKMostFrequentWords(keys);
        keys = test.getKeywordFrequencies().getKeys();

        String minFreqInTopK = keys.get(0);
        int minVal = q.peek().value;
        t = 1;
        for (String k : keys) {
            if (test.getWordFrequencies().get(k) < minVal && test.getKeywordFrequencies().get(k) != null){
                minVal = test.getWordFrequencies().get(k);
            }
        }
        int count = 0;
        while (!(q.isEmpty()) && count <= test.k) {
            FindKeyWordsInFile.Entry curr = q.poll();
            if (curr.value < minVal && test.getKeywordFrequencies().get(curr.word) == null){
                System.out.println("Test case 2: Failed, the dictionary doesn't loaded correctly");
                t = 0;
            }
            count++;
        }
        if (t == 1){

        }


        // Test case 3: check if the program filters common English words correctly
        // Test Case 4: check if the program generates the correct output for file3.txt
        // Test Case 5: check if the program handles empty input files
        // Test Case 6: check if the program handles nn-existing input files
    }
}
