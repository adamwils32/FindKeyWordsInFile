import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.PriorityQueue;

public class FindKeyWordsInFile {



    private AVLTree<String, Integer> wordFrequencies;
    private AVLTree<String, Boolean> englishWords;
    public int k;
    private AVLTree<String, Integer> keywordFrequencies;
    public FindKeyWordsInFile(int k, String inputFileName, String englishWordsFileName) {

        try {
            wordFrequencies = new AVLTree<>();
            englishWords = new AVLTree<>();
            this.k = k;
            //Part 1
            // function name => computeWordFrequencies(inputfile)
            this.computeWordFrequencies(inputFileName);

            //Part 2
            // function name => findKMostFrequentWords
            ArrayList<String> keys = wordFrequencies.getKeys();
            PriorityQueue<Entry> pq = findKMostFrequentWords(keys);

            //Part 3
            // function name => filterCommonEnglishWords
            this.keywordFrequencies = filterCommonEnglishWords(englishWordsFileName);

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

    public static void main(String[] args){
        if (args.length != 3) {
            System.err.println("Usage: java FindKeyWordsInFile k file.txt MostFrequentEnglishWords.txt");
            System.exit(1);
        }
        FindKeyWordsInFile findKeyWordsInFile = new FindKeyWordsInFile(Integer.parseInt(args[0]), args[1], args[2]);
    }

    public AVLTree<String, Integer> computeWordFrequencies(String inputfile) throws FileNotFoundException {
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
        return wordFrequencies;
    }

    public PriorityQueue<Entry> findKMostFrequentWords(ArrayList<String> keys){

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

    private AVLTree<String, Integer> filterCommonEnglishWords(String englishWordsFileName) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(englishWordsFileName));
        while (scanner.hasNext()){
            String word = scanner.next();
            englishWords.put(word, true);
        }
        AVLTree<String, Integer> filteredWordFrequencies = new AVLTree<>();
        PriorityQueue<Entry> queue = this.findKMostFrequentWords(wordFrequencies.getKeys());
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


    public static class Entry {
        String word;
        Integer value;

        public Entry(String word, Integer value) {
            this.word = word;
            this.value = value;
        }
    }

    public AVLTree<String, Integer> getWordFrequencies() {
        return wordFrequencies;
    }

    public AVLTree<String, Boolean> getEnglishWords() {
        return englishWords;
    }

    public AVLTree<String, Integer> getKeywordFrequencies() {
        return keywordFrequencies;
    }
}


