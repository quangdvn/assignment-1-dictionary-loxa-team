import java.io.*;
import java.util.*;
import java.lang.*;
class DictionaryManagement  {
    private static Scanner scanIn = new Scanner(System.in);
    protected static String FormatWord(int length) {
        String FormatWord = " ";
        for(int i = 0; i < 18 - length; i++){
            FormatWord += " ";
        }
        return FormatWord;                  // To print out the word in exact format
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
    public static void dictionarySearcher() {
        System.out.print("Input the word u want to search: ");
        String Find = scanIn.next();
        int index = 0;
        for (Word searchWord : Dictionary.listOfWord) {
            if(searchWord.getWord_Target().startsWith(Find) && index == 0) {
                System.out.println(searchWord.toStringfirstTime());
                index ++;
            }
            else if(searchWord.getWord_Target().startsWith(Find) && index != 0){
                System.out.println(searchWord.toString());
                index ++;
            }
        }
        if(index == 0) System.out.print("Cant find it :((");
    }
    public static void dictionaryInsert() {
        try {
            FileOutputStream fout = new FileOutputStream("C:/Users/ASUS/Desktop/OOP/Dictionary_Project/src/dictionaries.txt",true);
            System.out.print("Input the number of words u want to insert: ");
            int totalWords = scanIn.nextInt();
            for(int i = 0; i < totalWords; i++) {
                System.out.print("Input the word target: ");
                String newWordTarget = scanIn.next() + "\r\n";
                System.out.print("Input the word explain: ");
                String newWordExlpain = scanIn.next() + "\r\n";
                fout.write(newWordTarget.getBytes());
                fout.write(newWordExlpain.getBytes());
            }
            fout.flush();
            fout.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void dictionaryUpdate() {
        System.out.print("Input the word u want to update: ");
        String wordUpdate = scanIn.next();
        System.out.print("Input the word target: ");
        String newWordTarget = scanIn.next(); //+ "\r\n";
        System.out.print("Input the word explain: ");
        String newWordExlpain = scanIn.next();// + "\r\n";
        for(Word wordSearch : Dictionary.listOfWord) {
            if(wordSearch.getWord_Target().equals(wordUpdate)) {
                wordSearch.setWord_Target(newWordTarget);
                wordSearch.setWord_Explain(newWordExlpain);
            }
            break;
        }
    }
    public static void dictionaryExport() {
        int index = 0;
        try {
            File file = new File("./src/ExportFile.txt");
            FileOutputStream fout = new FileOutputStream(file);

            fout.write("========Dictionary_CommandLine========\r\n".getBytes());
            fout.write("No\t|English\t\t|Vietnamese\r\n".getBytes());
            for (Word wordExport : Dictionary.listOfWord) {
                index++;
                String wordTargetExport = wordExport.getWord_Target();
                String wordExplainExport = wordExport.getWord_Explain();
                fout.write((String.format("%d\t|%s|%s\r\n", index, wordTargetExport += DictionaryManagement.FormatWord(wordTargetExport.length()), wordExplainExport)).getBytes());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
