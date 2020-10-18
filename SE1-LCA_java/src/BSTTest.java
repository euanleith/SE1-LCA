import org.junit.Test;

import static org.junit.Assert.*;

public class BSTTest {

    @Test
    public void containsTest() {
        BST<Integer> bst = new BST<>();

        assertFalse(bst.contains(2));

        bst.put(2);
        bst.put(4);
        bst.put(1);
        bst.put(3);

        assertTrue(bst.contains(2));
        assertFalse(bst.contains(5));
        assertFalse(bst.contains(null));
    }

    @Test
    public void lca() {
        BST<Integer> bst = new BST<>();

        assertNull(bst.lca(1, 4));

        bst.put(2);
        bst.put(4);
        bst.put(1);
        bst.put(3);
        bst.put(6);

        assertEquals(2, (int) bst.lca(1, 4));
        assertEquals(4, (int) bst.lca(6, 3));
        assertNull(bst.lca(1, 5));
        assertNull(bst.lca(null, 1));
    }
}