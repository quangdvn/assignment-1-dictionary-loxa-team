import java.lang.*;
class Word {
    // Variables
    private String word_Target;
    private String word_Explain;

    // Constructor
    public Word(String _word_Target, String _word_Explain) {
        this.word_Target = _word_Target;
        this.word_Explain = _word_Explain;
    }
    public Word() {
        this.word_Explain = "Hello";
        this.word_Target = "Xin chao";
    }

    // Methods
    public String getWord_Target() {
        return this.word_Target;
    }
    public String getWord_Explain() {
        return this.word_Explain;
    }
    public void setWord_Explain(String word_Explain) {
        this.word_Explain = word_Explain;
    }
    public void setWord_Target(String word_Target) {
        this.word_Target = word_Target;
    }
    public Word getWord() {
        return this;
    }
    public void setWord(String _word_Target, String _word_Explain) {
        this.word_Target = _word_Target;
        this.word_Explain = _word_Explain;
    }
    public String toStringfirstTime() {
        this.word_Target += DictionaryManagement.FormatWord(this.word_Target.length());
        return("\t|English \t\t\t|\tVietnamese\n" + "\t|" + this.word_Target +"|" + this.word_Explain);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Word)) {
            return false;
        }
        Word word = (Word)obj;
        return (this.word_Target.equals(word.getWord_Target()) && this.word_Explain.equals(word.getWord_Explain()));
    }

    @Override
    public String toString() {
        this.word_Target += DictionaryManagement.FormatWord(this.word_Target.length());
        return( "\t|" + this.word_Target +"|" + this.word_Explain);
    }

    @Override
    public int hashCode() {
        int  result = 17;
        result = 31*result + this.word_Target.hashCode();
        result = 31*result + this.word_Explain.hashCode();
        return result;
    }


}
