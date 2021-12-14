package referenceBasedTreeImplementation;

import java.util.NoSuchElementException;
import java.util.Stack;

import exceptions.TreeException;
import utilities.*;

 /**
 * <p>
 * The <code>BSTReferencedBased</code> interface is designed to be used as a basis for all
 * the BSTreeADT<E> data structures that will be developed in the CPRG 311 class at 
 * Group Wakka. The implementors of this interface will be required to add all the 
 * functionality.
 * @author Bushra Osman, Jihoon Oh, Jonghan Park, Eunji Lee
 * @version Nov 30, 2021
 * @param <E> The type of elements this list holds.
 */
public class BSTReferencedBased<E extends Comparable<? super E>> implements BSTreeADT<E> {
	private static final long serialVersionUID = -5670466124478071469L;
	private BSTreeNode<E> root;
	
	/**
	 * Constructor builds the BSTReferencedBased
	 */
	public BSTReferencedBased() {
		this.root = null;
	}
	
	/**
	 * Constructor builds the BSTReferencedBased with the supplied parameters 
	 * @param element
	 */
	public BSTReferencedBased(E element) {
		this.root = new BSTreeNode<E>(element, null, null);
	}
	
	@Override
	public BSTreeNode<E> getRoot() throws TreeException {
		if(root == null) {
			throw new TreeException("Root is empty");
		}
		
		return root;
	}

	@Override
	public int getHeight() {
		return getHeight(root);
	}
	
	private int getHeight(BSTreeNode<E> node) {		
		if(node == null) {
			return 0;
		} else {
			return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));	
		}
	}

	@Override
	public int size() {
		int size = 0;
		
		Iterator<E> iterator = inorderIterator();
		
		while(iterator.hasNext()) {
			iterator.next();
			size++;
		}
		
		return size;
	}

	@Override
	public boolean isEmpty() {
		return (root == null);
	}

	@Override
	public void clear() {
		if(!isEmpty()) {
			root = clear(root);
			root = null;
		}
	}
	
	private BSTreeNode<E> clear(BSTreeNode<E> node) {
		if(node.getLeft() == null && node.getRight() == null) {
			return null;
		}
		
		if(node.getLeft() != null) {
			node.setLeft(clear(node.getLeft()));
		}
		
		if(node.getRight() != null) {
			node.setRight(clear(node.getRight()));
		}
		
		return node;
	}

	@Override
	public boolean contains(E entry) throws NullPointerException, TreeException {
		if(entry == null) {
			throw new NullPointerException("This element is null");
		}
		
		if(isEmpty()) {
			throw new TreeException("This BST is null");
		}
		
		BSTreeNode<E> currNode = root;
		
		while(currNode != null) {
			if(entry.compareTo(currNode.getElement()) == 0) {
				return true;
			} else if(entry.compareTo(currNode.getElement()) < 0) {
				currNode = currNode.getLeft();
			} else {
				currNode = currNode.getRight();
			}
		}
		
		return false;
	}

	@Override
	public BSTreeNode<E> search(E entry) throws NullPointerException, TreeException {
		if(entry == null) {
			throw new NullPointerException("This element is null");
		}
		
		if(isEmpty()) {
			throw new TreeException("This BST is null");
		}
		
		BSTreeNode<E> currNode = root;
		
		while(currNode != null) {
			if(entry.compareTo(currNode.getElement()) == 0) {
				return currNode;
			} else if(entry.compareTo(currNode.getElement()) < 0) {
				currNode = currNode.getLeft();
			} else {
				currNode = currNode.getRight();
			}
		}
		
		
		return currNode;
	}

	@Override
	public boolean add(E newEntry) throws NullPointerException {
		if(newEntry == null) {
			throw new NullPointerException("This element is null");
		}
		
		BSTreeNode<E> newNode = new BSTreeNode<>(newEntry, null, null);
		BSTreeNode<E> currNode = root;
		boolean found = false;
		
		if(isEmpty()) {
			root = newNode;
			return true;
		}
		
		while(!found) {
			if(newEntry.compareTo(currNode.getElement()) < 0) {
				if(currNode.getLeft() != null) {
					currNode = currNode.getLeft();
				} else {
					currNode.setLeft(newNode);
					found = true;
				}
			} else if(newEntry.compareTo(currNode.getElement()) > 0) {
				if(currNode.getRight() != null) {
					currNode = currNode.getRight();
				} else {
					currNode.setRight(newNode);
					found = true;
				}
			} else {
				found = true;
			}
		}
		
		return true;
	}

	@Override
	public Iterator<E> inorderIterator() {
		Stack<BSTreeNode<E>> nodeStack = new Stack<>();
		BSTreeNode<E> leftSide = root;
		
		while(leftSide != null) {
			nodeStack.push(leftSide);
			leftSide = leftSide.getLeft();
		}
		
		return new Iterator<E>() {			
			@Override
			public boolean hasNext() {
				return !nodeStack.isEmpty();
			}

			@Override
			public E next() throws NoSuchElementException {
				if(!hasNext()) {
					throw new NoSuchElementException("Current node is null");
				}
				
				BSTreeNode<E> currNode = nodeStack.pop();
				BSTreeNode<E> nextNode = currNode.getRight();
				
				while(nextNode != null) {
					nodeStack.push(nextNode);
					nextNode = nextNode.getLeft();
				}
				
				return currNode.getElement();
			}
		};
	}

	@Override
	public Iterator<E> preorderIterator() {
		Stack<BSTreeNode<E>> nodeStack = new Stack<>();
		if(root != null) {
			nodeStack.push(root);
		}
		
		return new Iterator<E>() {
			@Override
			public boolean hasNext() {
				return !nodeStack.isEmpty();
			}

			@Override
			public E next() throws NoSuchElementException {
				if(!hasNext()) {
					throw new NoSuchElementException("Current node is null");
				}
				
				BSTreeNode<E> currNode = nodeStack.pop();
												
				if(currNode.getRight() != null) {
					nodeStack.push(currNode.getRight());
				}
				
				if(currNode.getLeft() != null) {
					nodeStack.push(currNode.getLeft());
				}
				
				return currNode.getElement();
			}
		};
	}

	@Override
	public Iterator<E> postorderIterator() {
		Stack<BSTreeNode<E>> nodeStack = new Stack<>();
		BSTreeNode<E> sideNode = root;
		
		while(sideNode != null) {
			nodeStack.push(sideNode);
			
			if(sideNode.getLeft() != null) {
				sideNode = sideNode.getLeft();
			} else {
				sideNode = sideNode.getRight();
			}
		}
		
		return new Iterator<E>() {
			@Override
			public boolean hasNext() {
				return !nodeStack.isEmpty();
			}

			@Override
			public E next() throws NoSuchElementException {
				if(!hasNext()) {
					throw new NoSuchElementException("Current node is null");
				}
				
				BSTreeNode<E> currNode = nodeStack.pop();
				
				if(hasNext()) {
					BSTreeNode<E> nextNode = nodeStack.peek();
					
					if(currNode == nextNode.getLeft()) {
						BSTreeNode<E> nextSide = nextNode.getRight();
						
						while(nextSide != null) {
							nodeStack.push(nextSide);
							
							if(nextSide.getLeft() != null) {
								nextSide = nextSide.getLeft();
							} else {
								nextSide = nextSide.getRight();
							}
						}
					}
				}
				
				return currNode.getElement();
			}
		};
	}
}