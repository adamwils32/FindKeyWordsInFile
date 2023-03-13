import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FindKeyWordInFile2 {
    private AVLTree<String, Integer> frequency;
    public FindKeyWordInFile2(String file) {
         frequency = new AVLTree<>();
        try {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNext()){
                String word = scanner.next();
                if (frequency.get(word) == null){
                    frequency.put(word, 1);
                } else {
                    frequency.put(word, frequency.get(word)+1);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){

    }
}
