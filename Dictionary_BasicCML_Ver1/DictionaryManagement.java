import java.io.*;
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
    public static void dictionaryLookup() {
        System.out.print("Input the word u want to search: ");
        Scanner scanIn = new Scanner(System.in);
        String Find = scanIn.next();
        for(Word searchWord : Dictionary.listOfWord) {
            if(Find.equals(searchWord.getWord_Target())) {
                System.out.print(searchWord.toString());
                return;
            }
        }
        System.out.print("Cant find it :((");
    }
    public static void InsertFromFile() throws IOException {
        File file = new File("C:/Users/ASUS/Desktop/OOP/Dictionary_Project/src/dictionaries.txt");     // Path to dictionary file
        Scanner scanFromFile = new Scanner(file);
        while(scanFromFile.hasNextLine()) {
            String wordTarget = scanFromFile.nextLine();        // Read the word

            String wordExplain = scanFromFile.nextLine();       // Read the explaination
            Dictionary.listOfWord.add(new Word(wordTarget,wordExplain));                 // Create a word-explain
        }
    }
}
