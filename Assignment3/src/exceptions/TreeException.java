package exceptions;

/**
 * TreeException can catch when a root node or a tree is empty
 * @author Bushra Osman, Jihoon Oh, Jonghan Park, Eunji Lee
 * @version Nov 30, 2021
 */
public class TreeException extends Exception {
	public TreeException() {
		super();
	};
	
	public TreeException(String message) {
		super(message);
	}
}