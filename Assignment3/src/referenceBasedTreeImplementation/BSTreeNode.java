package referenceBasedTreeImplementation;

import java.io.Serializable;

/**
 * <p>
 * The <code>BSTreeNode</code> class is designed to be used as a basis for all
 * the nodes in BSTReferencedBased<E> data structures that will be developed
 * in the CPRG 311 class in Group Wakka. The implementors of this class will be
 * required to add all the functionality.
 *
 * @author Bushra Osman, Jihoon Oh, Jonghan Park, Eunji Lee
 * @version Nov 30, 2021
 * @param <E> The type of elements this list holds.
 * @param left Refers to the left Node.
 * @param right Refers to the right Node.
 */
public class BSTreeNode<E> implements Serializable {
	private static final long serialVersionUID = -2404530802093484458L;
	private E element;
	private BSTreeNode<E> left;
	private BSTreeNode<E> right;
	
	/**
	 * Constructor builds the BSTreeNode
	 */
	public BSTreeNode() {
		super();
	}
	
	/**
	 * Constructor builds the BSTreeNode with the supplied parameters 
	 * @param element to store in this node
	 * @param left must refer to its left child node
	 * @param right must refer to its right child node
	 */
	public BSTreeNode(E element, BSTreeNode<E> left, BSTreeNode<E> right) {
		super();
		this.element = element;
		this.left = left;
		this.right = right;
	}

/**
 * Gets the Element
 * @param element -  the element in question
 * @return the element
 */
	public E getElement() {
		return element;
	}

/**
 * Sets the Element
 * @param element -  the element in question
 */
	public void setElement(E element) {
		this.element = element;
	}

/**
 * Gets the Left Node
 * @param left -  the node in question
 * @return the left node
 */
	public BSTreeNode<E> getLeft() {
		return left;
	}

/**
 * Sets the left Node
 * @param left -  the node in question
 */
	public void setLeft(BSTreeNode<E> left) {
		this.left = left;
	}

 /**
 * Gets the Right Node
 * @param right -  the node in question
 * @return the right node
 */
	public BSTreeNode<E> getRight() {
		return right;
	}

/**
 * Sets the Right Node
 * @param right -  the node in question
 */
	public void setRight(BSTreeNode<E> right) {
		this.right = right;
	}
}