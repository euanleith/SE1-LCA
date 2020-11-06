import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class DAGTest {

    @Test
    public void putTest() {
        // test std
        DAG<Integer> dag = new DAG<>(2);
        assertEquals("{2=[]}",dag.getAdj().toString());
        assertTrue(dag.put(3));
        assertEquals("{2=[], 3=[]}",dag.getAdj().toString());
        assertTrue(dag.put(4, 2));
        assertEquals("{2=[4], 3=[], 4=[]}",dag.getAdj().toString());
        assertTrue(dag.put(4, 3));
        assertEquals("{2=[4], 3=[4], 4=[]}",dag.getAdj().toString());
        assertTrue(dag.put(1, 2));
        assertEquals("{1=[], 2=[1, 4], 3=[4], 4=[]}",dag.getAdj().toString());
        assertTrue(dag.put(1, 3));
        assertEquals("{1=[], 2=[1, 4], 3=[1, 4], 4=[]}",dag.getAdj().toString());
        assertTrue(dag.put(7, 3));
        assertEquals("{1=[], 2=[1, 4], 3=[1, 4, 7], 4=[], 7=[]}",dag.getAdj().toString());
        assertTrue(dag.put(5, 1));
        assertEquals("{1=[5], 2=[1, 4], 3=[1, 4, 7], 4=[], 5=[], 7=[]}",dag.getAdj().toString());
        assertTrue(dag.put(8, 1));
        assertEquals("{1=[5, 8], 2=[1, 4], 3=[1, 4, 7], 4=[], 5=[], 7=[], 8=[]}",dag.getAdj().toString());
        assertTrue(dag.put(8, 7));
        assertEquals("{1=[5, 8], 2=[1, 4], 3=[1, 4, 7], 4=[], 5=[], 7=[8], 8=[]}",dag.getAdj().toString());
        /*
                         5   8
                         |  /|
                         ↓ ↙ ↓
                    4    1   7
                    |\  /|  /
                    ↓ ↙↘ ↓ ↙
                    2    3

         */

        // test values have been added using 'contains'
        assertTrue(dag.contains(2));
        assertTrue(dag.contains(1));
        assertFalse(dag.contains(6));
        assertFalse(dag.contains(null));

        // test where node(s) already exist
        assertFalse(dag.put(4));
        assertEquals("{1=[5, 8], 2=[1, 4], 3=[1, 4, 7], 4=[], 5=[], 7=[8], 8=[]}",dag.getAdj().toString());
        assertFalse(dag.put(5,1));
        assertEquals("{1=[5, 8], 2=[1, 4], 3=[1, 4, 7], 4=[], 5=[], 7=[8], 8=[]}",dag.getAdj().toString());

        // test null input
        assertFalse(dag.put(2, null));
        assertFalse(dag.put(null, 2));
        assertFalse(dag.put(null));
    }

    @Test
    public void lca() {
        //test std1
        DAG<Integer> dag = new DAG<>(2);
        assertNull(dag.lca(2, 3));
        dag.put(3);
        assertNull(dag.lca(2, 3));
        dag.put(4, 2);
        assertNull(dag.lca(2, 3));
        assertEquals(4, (int) dag.lca(2, 4));

        dag.put(4, 3); //       5   8
        dag.put(1, 2); //       |  /|
        dag.put(1, 3); //       ↓ ↙ ↓
        dag.put(7, 3); //  4    1   7
        dag.put(5, 1); //  |\  /|  /
        dag.put(8, 1); //  ↓ ↙↘ ↓ ↙
        dag.put(8, 7); //  2    3

        // test std2
        assertEquals(5, (int) dag.lca(2, 3));
        assertEquals(8, (int) dag.lca(1, 7));
        assertEquals(5, (int) dag.lca(2, 1));

        // test lca is one of the given nodes
        assertEquals(4, (int) dag.lca(2, 4));
        assertEquals(5, (int) dag.lca(2, 5));

        // test lca doesn't exist
        assertNull(dag.lca(5, 8));
        assertNull(dag.lca(4, 8));

        // test node(s) not in dag
        assertNull(dag.lca(6, 2));
        assertNull(dag.lca(2, 6));
        assertNull(dag.lca(6, 9));

        // test null
        assertNull(dag.lca(null, 1));
        assertNull(dag.lca(1, null));
        assertNull(dag.lca(null, null));
    }

    @Test
    public void testGetAncestors() {
        // test std1
        DAG<Integer> dag = new DAG<>(2);
        assertEquals("{2=0}", dag.getAncestors(2).toString());
        dag.put(3);
        assertEquals("{2=0}", dag.getAncestors(2).toString());
        assertEquals("{3=0}", dag.getAncestors(3).toString());
        dag.put(4, 2);
        assertEquals("{2=0, 4=1}", dag.getAncestors(2).toString());

        dag.put(4, 3); //       5   8
        dag.put(1, 2); //       |  /|
        dag.put(1, 3); //       ↓ ↙ ↓
        dag.put(7, 3); //  4    1   7
        dag.put(5, 1); //  |\  /|  /
        dag.put(8, 1); //  ↓ ↙↘ ↓ ↙
        dag.put(8, 7); //  2    3

        // test std2
        assertEquals("{1=1, 2=0, 4=1, 5=2, 8=2}", dag.getAncestors(2).toString());
        assertEquals("{1=0, 5=1, 8=1}", dag.getAncestors(1).toString());
        assertEquals("{1=1, 3=0, 4=1, 5=2, 7=1, 8=2}",dag.getAncestors(3).toString());
        assertEquals("{7=0, 8=1}",dag.getAncestors(7).toString());
        assertEquals("{4=0}",dag.getAncestors(4).toString());
        assertEquals("{8=0}",dag.getAncestors(8).toString());

        // test node with 1 parent
        dag.put(10, 5);
        dag.put(11, 10);
        assertEquals("{1=0, 5=1, 8=1, 10=2, 11=3}",dag.getAncestors(1).toString());

        // test node not in dag
        assertNull(dag.getAncestors(6));

        // test null
        assertNull(dag.getAncestors(null));
    }
}