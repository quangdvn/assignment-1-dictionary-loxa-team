import java.util.*;
import java.lang.*;
class DictionaryManagement  {
    public static String FormatWord(int length) {
        String FormatWord = " ";
        for(int i = 0; i < 18 - length; i++){
            FormatWord += " ";
        }
        return FormatWord;                  // To print out the word in exact format
    }
    public static void InsertFromCommandLine() {
        Scanner scanIn = new Scanner(System.in);
        System.out.print("Enter the total number of words you want to insert: ");
        int j = scanIn.nextInt();
        for(int i = 0; i < j; i++){
            System.out.print("Input the word: ");
            String wordTarget = scanIn.next();
            wordTarget += FormatWord(wordTarget.length());
            System.out.print("Input the explaination: ");
            String wordExplain = scanIn.next();
            Word newWord = new Word(wordTarget.toLowerCase(),wordExplain);
            Dictionary.listOfWord.add(newWord);         // Input newWord inside the list
        }

    }
}
