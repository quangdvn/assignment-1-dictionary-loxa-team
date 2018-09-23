import java.util.*;
import java.lang.*;
public class DictionaryCommandLine {
    public static void showAllWords() {
        int index = 0;
        System.out.println("=======Dictionary_CommandLine=======");
        System.out.println("No\t|English\t\t\t|Vietnamese");
        for(Word wordList : Dictionary.listOfWord) {
            index++;
            String newWord_Target = wordList.getWord_Target();
            String newWord_Explain = wordList.getWord_Explain();
            System.out.println( index + "\t|" + newWord_Target + "|" + newWord_Explain);        //  Display newWords and Explaination
        }
    }
    public static void main (String[] args) {           // Main method
        DictionaryManagement.InsertFromCommandLine();
        DictionaryCommandLine.showAllWords();
    }
}
