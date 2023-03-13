import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.PriorityQueue;

public class FindKeyWordsInFile {


    private static ArrayList<String> words = new ArrayList<>();
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java FindKeyWordsInFile k file.txt MostFrequentEnglishWords.txt");
            System.exit(1);
        }

        int k = Integer.parseInt(args[0]);
        String inputFileName = args[1];
        String englishWordsFileName = args[2];
        
        AVLTree<String, Integer> wordFrequencies = new AVLTree<>();
        AVLTree<String, Void> englishWords = new AVLTree<>();
        AVLTree<String, Integer> keywordFrequencies = new AVLTree<>();
        
        try {
            //Part 1
            // function name => computeWordFrequencies(inputfile)
            computeWordFrequencies(wordFrequencies, inputFileName);
            System.out.println(words.toString());
            //Part 2
            // function name => findKMostFrequentWords
            
            //Part 3
            // function name => filterCommonEnglishWords
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void computeWordFrequencies(AVLTree<String, Integer> wordFrequencies, String inputfile) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(inputfile));
        while (scanner.hasNext()){
            String word = scanner.next();
            if (wordFrequencies.get(word) == null){
                wordFrequencies.put(word, 1);
                words.add(word);
            } else {
                wordFrequencies.put(word, wordFrequencies.get(word)+1);
            }
        }
    }

    private static void findKMostFrequentWords(AVLTree<String, Integer> wordFrequencies){

        PriorityQueue<String> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for (String word : words){

        }
        //Collections.reverseOrder()


    }

}
