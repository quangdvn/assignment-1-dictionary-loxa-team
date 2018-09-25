import java.io.IOException;
import java.util.*;
import java.lang.*;
public class DictionaryCommandLine {
    public static void showAllWords() {
        int index = 0;
        System.out.println("=======Dictionary_CommandLine=======");
        System.out.println("No\t|English\t\t\t|Vietnamese");
        for (Word wordList : Dictionary.listOfWord) {
            index++;
            String newWord_Target = wordList.getWord_Target();
            String newWord_Explain = wordList.getWord_Explain();
            System.out.println(index + "\t|" + (newWord_Target += DictionaryManagement.FormatWord(newWord_Target.length())) + "|" + newWord_Explain);        //  Display newWords and Explaination
        }
    }

    public static void dictionaryAdvanced() throws IOException {
        Scanner scanIn = new Scanner(System.in);
        System.out.println("List of to-do: ");
        System.out.println("1. To show all the words\n2. To look for a word");
        System.out.print("Choose what u want to do: ");
        int Request = scanIn.nextInt();
        switch (Request) {
            case 1:
                DictionaryManagement.InsertFromFile();
                DictionaryCommandLine.showAllWords();
                break;
            case 2:
                DictionaryManagement.dictionaryLookup();
                break;
            default:
                System.out.print("False choice! Please choose again");
                System.exit(1);
        }
    }
}
