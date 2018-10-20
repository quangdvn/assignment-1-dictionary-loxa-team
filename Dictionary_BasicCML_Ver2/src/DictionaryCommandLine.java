import java.io.IOException;
import java.util.*;
import java.lang.*;
public class DictionaryCommandLine {
    private static Scanner scanIn = new Scanner(System.in);
    private static int Choice = 0;
    public static void showAllWords() {
        int index = 0;
        System.out.println("========Dictionary_CommandLine========");
        System.out.println("No\t|English\t\t\t|\tVietnamese");
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
        System.out.println("1. To show all the words\n2. To look for a word\n3. To insert new words\n4. To update word\n5. To delete word\n6. To export into new file\n7. To exit");
        System.out.print("Choose what u want to do: ");
        int Request = scanIn.nextInt();
        switch (Request) {
            case 1:
                DictionaryCommandLine.showAllWords();
                System.out.print("Continue....(0 = No, Else = Yes:) ");
                Choice = scanIn.nextInt();
                if(Choice == 0) System.exit(0);
                else {
                    DictionaryCommandLine.dictionaryAdvanced();
                    break;
                }

            case 2:
                DictionaryManagement.dictionarySearcher();
                System.out.print("Continue....(0 = No, Else = Yes): ");
                Choice = scanIn.nextInt();
                if(Choice == 0) System.exit(0);
                else {
                    DictionaryCommandLine.dictionaryAdvanced();
                    break;
                }

            case 3:
                DictionaryManagement.dictionaryInsert();
                System.out.print("Continue....(0 = No, Else = Yes): ");
                Choice = scanIn.nextInt();
                if(Choice == 0) System.exit(0);
                else {
                    DictionaryCommandLine.dictionaryAdvanced();
                    break;
                }

            case 4:
                DictionaryManagement.dictionaryUpdate();
                System.out.print("Continue....(0 = No, Else = Yes): ");
                Choice = scanIn.nextInt();
                if(Choice == 0) System.exit(0);
                else {
                    DictionaryCommandLine.dictionaryAdvanced();
                    break;
                }
            case 5:
                DictionaryManagement.dictionaryDelete();
                System.out.print("Continue....(0 = No, Else = Yes): ");
                Choice = scanIn.nextInt();
                if(Choice == 0) System.exit(0);
                else {
                    DictionaryCommandLine.dictionaryAdvanced();
                    break;
                }
            case 6:
                DictionaryManagement.dictionaryExport();
                System.out.print("Continue....(0 = No, Else = Yes): ");
                Choice = scanIn.nextInt();
                if(Choice == 0) System.exit(0);
                else {
                    DictionaryCommandLine.dictionaryAdvanced();
                    break;
                }
            case 7:
                System.exit(0);
                break;
            default:
                System.out.print("False choice! Please choose again");
                DictionaryCommandLine.dictionaryAdvanced();
                break;
        }
    }
}
