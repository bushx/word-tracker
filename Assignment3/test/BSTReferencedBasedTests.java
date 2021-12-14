import static org.junit.Assert.*;

import org.junit.*;

import exceptions.TreeException;
import referenceBasedTreeImplementation.BSTReferencedBased;
import utilities.Iterator;

/**
 * Test for BSTReferencedBased class
 * @author Bushra Osman, Jihoon Oh, Jonghan Park, Eunji Lee
 * @version Nov 30, 2021
 */
public class BSTReferencedBasedTests {
	private BSTReferencedBased<String> bst;
	private String a;
	private String b;
	private String c;
	private String d;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		bst = new BSTReferencedBased<String>();
		a = "a";
		b = "b";
		c = "c";
		d = "d";
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		bst = null;
		a = null;
		b = null;
		c = null;
		d = null;
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#BSTReferencedBased()}.
	 */
	@Test
	public void testBSTReferencedBased() {
		boolean expected = true;
		boolean actual = (bst != null);
		
		assertEquals("BST was not created", expected, actual);
	}

	/**
	 * Test method for
	 * {@link referenceBasedTreeImplementation.BSTReferencedBased#BSTReferencedBased(java.lang.Comparable)}
	 * @throws TreeException 
	 */
	@Test
	public void testBSTReferencedBasedE() throws TreeException {
		bst = new BSTReferencedBased<String>(a);
		
		boolean expected = true;
		boolean actual = (bst != null && bst.getRoot().getElement().equals(a));
		
		assertEquals("BST was not created correctly", expected, actual);
	}
	
	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#getRoot()}.
	 * @throws TreeException 
	 */
	@Test
	public void testGetRoot() throws TreeException {
		bst = new BSTReferencedBased<String>(a);
		
		String expected = a;
		String actual = bst.getRoot().getElement();
		
		assertEquals("Root was not returned correctly", expected, actual);
	}
	
	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#getRoot()}.
	 * @throws TreeException 
	 */
	@Test
	public void testGetRootForTreeException() {		
		try {
			bst.getRoot();
		} catch(TreeException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#getHeight()}.
	 */
	@Test
	public void testGetHeight() {
		bst.add(b);
		bst.add(a);
		bst.add(c);
		bst.add(d);
		
		int expected = 3;
		int actual = bst.getHeight();
		
		assertEquals("Height was not calculated correctly", expected, actual);
	}
	
	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#getHeight()}.
	 */
	@Test
	public void testGetHeightForEmptyBST() {
		int expected = 0;
		int actual = bst.getHeight();
		
		assertEquals("Height was not calculated correctly", expected, actual);
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#size()}.
	 */
	@Test
	public void testSize() {
		bst.add(b);
		bst.add(a);
		bst.add(c);
		bst.add(d);
		
		int expected = 4;
		int actual = bst.size();
		
		assertEquals("Size was not updated correctly", expected, actual);
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#size()}.
	 */
	@Test
	public void testSizeForEmptyBST() {		
		int expected = 0;
		int actual = bst.size();
		
		assertEquals("Size was not updated correctly", expected, actual);
	}
	
	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#isEmpty()}.
	 */
	@Test
	public void testIsEmptyForTrue() {
		boolean expected = true;
		boolean actual = bst.isEmpty();
		
		assertEquals("True value was not returned correctly", expected, actual);
	}
	
	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#isEmpty()}.
	 */
	@Test
	public void testIsEmptyForFalse() {
		bst.add(b);
		bst.add(a);
		bst.add(c);
		bst.add(d);
		
		boolean expected = false;
		boolean actual = bst.isEmpty();
		
		assertEquals("False value was not returned correctly", expected, actual);
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#clear()}.
	 */
	@Test
	public void testClear() {
		bst.add(b);
		bst.add(a);
		bst.add(c);
		bst.add(d);
		bst.clear();
		
		boolean expected = true;
		boolean actual = bst.isEmpty();
		
		assertEquals("Clear did not work correctly", expected, actual);
	}
	
	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#clear()}.
	 */
	@Test
	public void testClearForEmptyBST() {
		bst.clear();
		
		boolean expected = true;
		boolean actual = bst.isEmpty();
		
		assertEquals("Clear did not work correctly", expected, actual);
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#contains(java.lang.Comparable)}.
	 * @throws NullPointerException 
	 * @throws TreeException 
	 */
	@Test
	public void testContainsForTrue() throws NullPointerException, TreeException {
		bst.add(b);
		bst.add(a);
		bst.add(c);
		bst.add(d);
		
		boolean expected = true;
		boolean actual = bst.contains(a);
		
		assertEquals("True value was not returned correctly", expected, actual);
	}
	
	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#contains(java.lang.Comparable)}.
	 * @throws NullPointerException 
	 * @throws TreeException 
	 */
	@Test
	public void testContainsForFalse() throws NullPointerException, TreeException {
		bst.add(b);
		bst.add(a);
		bst.add(c);
		
		boolean expected = false;
		boolean actual = bst.contains(d);
		
		assertEquals("False value was not returned correctly", expected, actual);
	}
	
	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#contains(java.lang.Comparable)}.
	 * @throws TreeException 
	 */
	@Test
	public void testContainsForNullPointerException() throws TreeException{
		bst.add(b);
		bst.add(a);
		bst.add(c);
		bst.add(d);
		
		try {
			bst.contains(null);
			fail("Contains method failed to throw NullPointerException");
		} catch(NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#contains(java.lang.Comparable)}.
	 */
	@Test
	public void testContainsForTreeException() {
		try {
			bst.contains(a);
		} catch(TreeException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#search(java.lang.Comparable)}.
	 * @throws NullPointerException 
	 * @throws TreeException
	 */
	@Test
	public void testSearch() throws NullPointerException, TreeException {
		bst.add(b);
		bst.add(a);
		bst.add(c);
		bst.add(d);
		
		String expected = a;
		String actual = bst.search(a).getElement();
		
		assertEquals("Node was not returned correctly", expected, actual);
	}
	
	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#search(java.lang.Comparable)}.
	 * @throws NullPointerException 
	 * @throws TreeException
	 */
	@Test
	public void testSearchForNull() throws NullPointerException, TreeException {
		bst.add(b);
		bst.add(a);
		bst.add(c);
		
		boolean expected = true;
		boolean actual = (bst.search(d) == null);
		
		assertEquals("Null value was not returned correctly", expected, actual);
	}
	
	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#search(java.lang.Comparable)}.
	 * @throws NullPointerException 
	 * @throws TreeException
	 */
	@Test
	public void testSearchForNullPointerException() throws TreeException {
		bst.add(b);
		bst.add(a);
		bst.add(c);
		bst.add(d);
		
		try {
			bst.search(null);
			fail("Search method failed to throw NullPointerException");
		} catch(NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#search(java.lang.Comparable)}.
	 * @throws NullPointerException 
	 * @throws TreeException
	 */
	@Test
	public void testSearchForTreeException() {
		try {
			bst.search(a);
		} catch(TreeException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#add(java.lang.Comparable)}.
	 */
	@Test
	public void testAdd() {
		bst.add(b);
		bst.add(a);
		bst.add(c);
		bst.add(d);
		
		int expected = 4;
		int actual = bst.size();
		
		assertEquals("Elements was not added correctly and size was not updated correctly",
				expected, actual);
	}
	
	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#add(java.lang.Comparable)}.
	 */
	@Test
	public void testAddForBooleanReturn() {
		boolean expected = true;
		boolean actual = bst.add(a);
		
		assertEquals("True value was not returned after adding elements", expected, actual);
	}
	
	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#add(java.lang.Comparable)}.
	 */
	@Test
	public void testAddForNullPointerException() {
		try {
			bst.add(null);
			fail("Add method failed to throw NullPointerException");
		} catch(NullPointerException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#inorderIterator()}.
	 */
	@Test
	public void testInorderIterator() {
		bst.add(b);
		bst.add(a);
		bst.add(c);
		bst.add(d);
		
		String expected = "abcd";
		String actual = "";
		
		Iterator<String> iterator = bst.inorderIterator();
		
		while(iterator.hasNext()) {
			actual += iterator.next();
		}
		
		assertEquals("Iterator did not work correctly", expected, actual);
	}
	
	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#inorderIterator()}.
	 */
	@Test
	public void testInorderIteratorForEmptyBST() {
		String expected = "";
		String actual = "";
		
		Iterator<String> iterator = bst.inorderIterator();
		
		while(iterator.hasNext()) {
			actual += iterator.next();
		}
		
		assertEquals("Iterator did not work correctly", expected, actual);
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#preorderIterator()}.
	 */
	@Test
	public void testPreorderIterator() {
		bst.add(b);
		bst.add(a);
		bst.add(c);
		bst.add(d);
		
		String expected = "bacd";
		String actual = "";
		
		Iterator<String> iterator = bst.preorderIterator();
		
		while(iterator.hasNext()) {
			actual += iterator.next();
		}
		
		assertEquals("Iterator did not work correctly", expected, actual);
	}
	
	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#preorderIterator()}.
	 */
	@Test
	public void testPreorderIteratorForEmptyBST() {
		String expected = "";
		String actual = "";
		
		Iterator<String> iterator = bst.inorderIterator();
		
		while(iterator.hasNext()) {
			actual += iterator.next();
		}
		
		assertEquals("Iterator did not work correctly", expected, actual);
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#postorderIterator()}.
	 */
	@Test
	public void testPostorderIterator() {
		bst.add(b);
		bst.add(a);
		bst.add(c);
		bst.add(d);
		
		String expected = "adcb";
		String actual = "";
		
		Iterator<String> iterator = bst.postorderIterator();
		
		while(iterator.hasNext()) {
			actual += iterator.next();
		}
		
		assertEquals("Iterator did not work correctly", expected, actual);
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#postorderIterator()}.
	 */
	@Test
	public void testPostorderIteratorForEmptyBST() {
		String expected = "";
		String actual = "";
		
		Iterator<String> iterator = bst.inorderIterator();
		
		while(iterator.hasNext()) {
			actual += iterator.next();
		}
		
		assertEquals("Iterator did not work correctly", expected, actual);
	}
}