import java.io.*;
import java.net.SocketTimeoutException;
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
        File file = new File("dictionaries.txt");     // Path to dictionary file
        Scanner scanFromFile = new Scanner(file);
        while(scanFromFile.hasNextLine()) {
            String wordTarget = scanFromFile.next();        // Read the word
            String wordExplain = scanFromFile.nextLine();       // Read the explaination
            Dictionary.listOfWord.add(new Word(wordTarget,wordExplain));                 // Create a word target - explain
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
            FileOutputStream fout = new FileOutputStream("dictionaries.txt",true);
            System.out.print("Input the number of words u want to insert: ");
            int totalWords = scanIn.nextInt();
            for(int i = 0; i < totalWords; i++) {
                System.out.print("Input the word target: ");
                if(i == 0 ) scanIn.nextLine();
                String newWordTarget = scanIn.nextLine();
                System.out.print("Input the word explain: ");
                String newWordExlpain = scanIn.nextLine();
                fout.write(newWordTarget.getBytes());
                //fout.write(("   ").getBytes());
                fout.write(("\r\t").getBytes());
                fout.write((newWordExlpain ).getBytes());
                fout.write(("\r\n").getBytes());
            }
            System.out.println("Succeed");
            fout.flush();
            fout.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void dictionaryUpdate() {
        System.out.print("Please enter the word which you want to change: ");
        String wordUpdate = scanIn.next();
        for (Word wordSearch: Dictionary.listOfWord) {
            if (wordUpdate.equals(wordSearch.getWord_Target())) {
                System.out.print("Word changes to: ");
                scanIn.nextLine();
                wordUpdate = scanIn.nextLine();
                wordSearch.setWord_Target(wordUpdate);
                System.out.print("Explain change to: ");
                wordUpdate = scanIn.nextLine();
                wordSearch.setWord_Explain(wordUpdate);
                System.out.println("Succeed");
                return;
            }
        }
        System.out.println("Sorry! We can't find the word you're looking for!");
    }
    public static void dictionaryDelete() {
        System.out.print("Input the word you want to delete: ");
        String deleteWord = scanIn.nextLine();
        Iterator<Word> iterator = Dictionary.listOfWord.iterator();
        while (iterator.hasNext()){
            if(deleteWord.equals(iterator.next().getWord_Target())){
                iterator.remove();
                System.out.println("Succeed");
                return;
            }
        }
        System.out.println("Sorry! We can't find the word you're looking for!");
    }

    public static void dictionaryExport() {
        try {
            File file = new File("ExportFile.txt");
            FileOutputStream fout = new FileOutputStream(file);
            for (Word wordExport : Dictionary.listOfWord) {
                String wordTargetExport = wordExport.getWord_Target();
                String wordExplainExport = wordExport.getWord_Explain();
                fout.write((wordTargetExport + "\r\t" + wordExplainExport + "\r\n").getBytes());
            }
            System.out.print("Succeed!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
