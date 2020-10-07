import org.junit.Test;

import static org.junit.Assert.*;

public class BSTTest {

    @Test
    public void put() {
        BST<Integer> bst = new BST<>();

        bst.put(2);
        bst.put(4);
        bst.put(1);
        bst.put(3);
    }

    @Test
    public void get() {
        BST<Integer> bst = new BST<>();

        bst.put(2);
        bst.put(4);
        bst.put(1);
        bst.put(3);

        assertTrue(bst.get(2));
        assertFalse(bst.get(5));
    }

    @Test
    public void LCA() {
        BST<Integer> bst = new BST<>();

        bst.put(2);
        bst.put(4);
        bst.put(1);
        bst.put(3);
        bst.put(6);

        assertEquals(2, (int) bst.LCA(1, 4));
        assertEquals(4, (int) bst.LCA(3, 6));
        assertNull(bst.LCA(1, 5));
    }
}