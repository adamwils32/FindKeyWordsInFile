import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.PriorityQueue;

public class FindKeyWordsInFile {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java FindKeyWordsInFile k file.txt MostFrequentEnglishWords.txt");
            System.exit(1);
        }

        int k = Integer.parseInt(args[0]);
        String inputFileName = args[1];
        String englishWordsFileName = args[2];
        
        AVLTree<String, Integer> wordFrequencies = new AVLTree<>();
        AVLTree<String, Boolean> englishWords = new AVLTree<>();
        AVLTree<String, Integer> keywordFrequencies = new AVLTree<>();
        
        try {
            //Part 1
            // function name => computeWordFrequencies(inputfile)
            computeWordFrequencies(wordFrequencies, inputFileName);

            //System.out.println(wordFrequencies.inOrderTraversal().toString());
            //Part 2
            // function name => findKMostFrequentWords
            ArrayList<String> keys = wordFrequencies.inOrderTraversal();
            PriorityQueue<Entry> pq = findKMostFrequentWords(wordFrequencies, keys);

            //Part 3
            // function name => filterCommonEnglishWords

            keywordFrequencies = filterCommonEnglishWords(wordFrequencies, k, englishWords, englishWordsFileName);

            int wordCount = 0;
            while (!(pq.isEmpty()) && wordCount < k) {
                Entry entry = pq.poll();
                String word = entry.word;
                if (keywordFrequencies.get(word) != null){
                    wordCount++;
                    System.out.println(word + " " + entry.value);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void computeWordFrequencies(AVLTree<String, Integer> wordFrequencies, String inputfile) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(inputfile));
        while (scanner.hasNext()){
            String word = scanner.next().replaceAll("[^a-zA-Z ]", "").toLowerCase();
            if (word.equals("")){
                continue;
            }
            if (wordFrequencies.get(word) == null){
                wordFrequencies.put(word, 1);
            } else {
                wordFrequencies.put(word, wordFrequencies.get(word)+1);
            }
        }
    }

    private static PriorityQueue<Entry> findKMostFrequentWords(AVLTree<String, Integer> wordFrequencies, ArrayList<String> keys){

        Comparator<Entry> comparator = (entry1, entry2) -> {
            Integer value1 = wordFrequencies.get(entry1.word);
            Integer value2 = wordFrequencies.get(entry2.word);
            return Integer.compare(value2, value1);
        };

        PriorityQueue<Entry> priorityQueue = new PriorityQueue<>(comparator);

        for (String word : keys){
            Entry entry = new Entry(word, wordFrequencies.get(word));
            priorityQueue.add(entry);
        }

        return priorityQueue;

    }

    private static AVLTree<String, Integer> filterCommonEnglishWords(AVLTree<String, Integer> wordFrequencies, int k, AVLTree<String, Boolean> englishWords, String englishWordsFileName) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(englishWordsFileName));
        while (scanner.hasNext()){
            String word = scanner.next();
            englishWords.put(word, true);
        }
        AVLTree<String, Integer> filteredWordFrequencies = new AVLTree<>();
        PriorityQueue<Entry> queue = findKMostFrequentWords(wordFrequencies, wordFrequencies.inOrderTraversal());
        int wordCount = 0;
        while (!(queue.isEmpty()) && wordCount < k) {
            Entry entry = queue.poll();
            String word = entry.word;
            if (englishWords.get(word) == null){
                wordCount++;
                filteredWordFrequencies.put(word, wordFrequencies.get(word));
            }
        }
        return filteredWordFrequencies;
    }
    private static class Entry {
        String word;
        Integer value;

        public Entry(String word, Integer value) {
            this.word = word;
            this.value = value;
        }
    }
}


