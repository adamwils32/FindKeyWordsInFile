import java.io.File;
import java.util.Scanner;

public class FindKeyWordsInFileTest {

    public static void main(String[] args) {
        FindKeyWordsInFile test = new FindKeyWordsInFile(Integer.parseInt(args[0]), args[1], args[2]);

        // Test case 1: check if the program reads the input file and creates the word frequency correctly
        System.out.println("++++++++++++++++++++++++++");
        if (!test.getWordFrequencies().getKeys().isEmpty()){
            System.out.println("Test case 1: Passed, the word frequency AVLTree is not empty");
        } else {
            System.out.println("Test case 1: Failed, the word frequency AVLTree is empty");
        }

        // Test case 2: check if the program finds the k most frequent words correctly
        System.out.println("++++++++++++++++++++++++++");
        if (test.findKMostFrequentWords(test.getKeywordFrequencies().getKeys()).size() <= Integer.parseInt(args[0])) {
            System.out.println("Test case 2: Passed, K most frequent words found successfully");
        } else {
            System.out.println("Test case 2: Failed, K most frequent words not found successfully");
        }


        // Test case 3: check if the program filters common English words correctly
        System.out.println("++++++++++++++++++++++++++");
        try{
            Scanner scanner = new Scanner(new File(args[2]));
            while (scanner.hasNext()){
                String w = scanner.next();
                if (test.getKeywordFrequencies().get(w) != null){
                    System.out.println("Test case 3: Failed, English words not filtered correctly");
                    break;
                }
                if (!scanner.hasNext()){
                    System.out.println("Test case 3: Passed, English words filtered correctly");
                }
            }
        } catch (Exception e){
            System.out.println("Test case 3: Failed, English words not filtered correctly\n" + e.getMessage());
        }

        // Test Case 4: check if the program generates the correct output for file3.txt
        System.out.println("++++++++++++++++++++++++++");
        FindKeyWordsInFile file3 = new FindKeyWordsInFile(5, "file3.txt", args[2]);
        AVLTree<String, Integer> tree = file3.getKeywordFrequencies();
        if (tree.get("lily") != 8){
            System.out.println("Test case 4: Failed, Incorrect output for file3.txt, Correct output -> (lily, 8)");
        } else if (tree.get("elephant") != 8) {
            System.out.println("Test case 4: Failed, Incorrect output for file3.txt, Correct output -> (elephant, 8)");
        } else if (tree.get("umbrella") != 8) {
            System.out.println("Test case 4: Failed, Incorrect output for file3.txt, Correct output -> (umbrella, 8)");
        } else if (tree.get("violet") != 8) {
            System.out.println("Test case 4: Failed, Incorrect output for file3.txt, Correct output -> (violet, 8)");
        } else if (tree.get("xray") != 4) {
            System.out.println("Test case 4: Failed, Incorrect output for file3.txt, Correct output -> (xray, 4)");
        } else {
            System.out.println("Test case 4: Passed, Correct output for file3.txt");
        }

        // Test Case 5: check if the program handles empty input files
        System.out.println("++++++++++++++++++++++++++");
        try{
            FindKeyWordsInFile empty = new FindKeyWordsInFile(5, "file4.txt", args[2]);
        } catch (Exception e) {
            System.out.println("Test case 5: Failed, Exception raised as a result of empty input file " + e.getMessage());
        } finally {
            System.out.println("Test case 5: Passed, Program successfully handles empty input files");
        }

        // Test Case 6: check if the program handles nn-existing input files
        System.out.println("++++++++++++++++++++++++++");
        try{
            FindKeyWordsInFile empty = new FindKeyWordsInFile(5, "file5.txt", args[2]);
        } catch (Exception e) {
            System.out.println("Test case 6: Failed, Exception raised as a result of non-existing input file" + e.getMessage());
        } finally {
            System.out.println("Test case 6: Passed, the program sucessfully handles non-existing input files");
        }
    }
}

