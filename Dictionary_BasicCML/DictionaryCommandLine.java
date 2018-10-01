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
        DictionaryManagement.InsertFromFile();
        Scanner scanIn = new Scanner(System.in);
        System.out.println("List of to-do: ");
        System.out.println("1. To show all the words\n2. To look for a word\n3. To insert new words\n4. To update word\n5. To export into new file");
        System.out.print("Choose what u want to do: ");
        int Request = scanIn.nextInt();
        switch (Request) {
            case 1:
                DictionaryCommandLine.showAllWords();
                break;
            case 2:
                DictionaryManagement.dictionarySearcher();
                break;
            case 3:
                DictionaryManagement.dictionaryInsert();
                break;
            case 4:
                DictionaryManagement.dictionaryUpdate();
                System.out.print("New list of word: ");
                DictionaryCommandLine.showAllWords();
                break;
            case 5:
                DictionaryManagement.dictionaryExport();
                break;
            default:
                System.out.print("False choice! Please choose again");
                System.exit(1);
        }
    }
}
