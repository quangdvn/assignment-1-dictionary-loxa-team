package myPackage;

public class Word {
	private String word_target;
	private String word_explain;
	
	public Word(){
		this.word_target = null;
		this.word_explain = null;
	}
	public Word(String word_target, String word_explain){
		this.word_explain = word_explain;
		this.word_target = word_target;
	}
	/**
	 * @param word_target and word_explain
	 * @return void
	 * 
	 */
	public void setValue(String word_target, String word_explain) {
		this.word_explain = word_explain;
		this.word_target = word_target;
	}
	
	public void setWordTarget(String word_target) {
		this.word_target = word_target;
	}
	public String getWordTarget() {
		return this.word_target;
	}
	
	public void setWordExplain(String word_explain) {
		this.word_explain = word_explain;
	}
	public String getWordExplain() {
		return this.word_explain;
	}
	
	public Word get() { // return this object
		return this;
	}
	
	@Override
	public String toString() {
		return ("\nword_target : " + this.word_target
				+ "\nword_explain : " + this.word_explain);
	}
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Word)) { //Check the type of obj : is Word -> break. else return false
			return false;
		}
		Word temp = (Word)obj;
		return (this.word_target.equals(temp.getWordTarget()) 
				&& this.word_explain.equals(temp.getWordExplain()));
	}
	@Override
	public int hashCode() {
		int resulf = 17;
		resulf = 31*resulf + this.word_target.hashCode();
		resulf = 31*resulf + this.word_explain.hashCode();
		return resulf;
	}
	
	
	
	
	
}


























