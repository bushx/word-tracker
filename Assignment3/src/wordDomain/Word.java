package wordDomain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Word class contains a specific word and it's information
 * @author Bushra Osman, Jihoon Oh, Jonghan Park, Eunji Lee
 * @version Nov 30, 2021
 */
public class Word implements Comparable<Word>, Serializable {
	private static final long serialVersionUID = 8667184955479420345L;
	private String word;
	private ArrayList<OccurrenceInfo> info; 
		
	/**
	 * Constructor builds the Word
	 */
	public Word() {
		super();
	}
	
	/**
	 * Constructor builds the Word with the supplied parameters 
	 * @param word a specific word
	 */
	public Word(String word) {
		super();
		this.word = word;
		info = new ArrayList<>();
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public ArrayList<OccurrenceInfo> getInfo() {
		return info;
	}

	public void setInfo(ArrayList<OccurrenceInfo> info) {
		this.info = info;
	}

	@Override
	public int compareTo(Word o) {
		if(this.getWord().compareTo(o.getWord()) > 0) {
			return 1;
		} else if(this.getWord().compareTo(o.getWord()) < 0) {
			return -1;
		} else {
			return 0;
		}
	}
}