package wordDomain;

import java.io.Serializable;

/**
 * OccurrenceInfo class represents a file name and line number where a specific word was found
 * @author Bushra Osman, Jihoon Oh, Jonghan Park, Eunji Lee
 * @version Nov 30, 2021
 */
public class OccurrenceInfo implements Serializable {
	private static final long serialVersionUID = 4923722900713469180L;
	private String fileName;
	private int lineNumber;
	
	/**
	 * Constructor builds the OccurrenceInfo
	 */
	public OccurrenceInfo() {
		super();
	}
	
	/**
	 * Constructor builds the OccurrenceInfo with the supplied parameters 
	 * @param fileName a file name the word in
	 * @param lineNumber a line number the word in
	 */
	public OccurrenceInfo(String fileName, int lineNumber) {
		super();
		this.fileName = fileName;
		this.lineNumber = lineNumber;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
}