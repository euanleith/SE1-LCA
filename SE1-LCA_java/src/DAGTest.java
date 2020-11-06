import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class DAGTest {

    @Test
    public void putTest() {
        DAG<Integer> dag = new DAG<>(2);
        assertEquals("{2=[]}",dag.getAdj().toString());

        assertFalse(dag.contains(1));

        dag.put(3);
        assertEquals("{2=[], 3=[]}",dag.getAdj().toString());
        dag.put(4, 2);
        assertEquals("{2=[4], 3=[], 4=[]}",dag.getAdj().toString());
        dag.put(4, 3);
        assertEquals("{2=[4], 3=[4], 4=[]}",dag.getAdj().toString());
        dag.put(1, 2);
        assertEquals("{1=[], 2=[1, 4], 3=[4], 4=[]}",dag.getAdj().toString());
        dag.put(1, 3);
        assertEquals("{1=[], 2=[1, 4], 3=[1, 4], 4=[]}",dag.getAdj().toString());
        dag.put(7, 3);
        assertEquals("{1=[], 2=[1, 4], 3=[1, 4, 7], 4=[], 7=[]}",dag.getAdj().toString());
        dag.put(5, 1);
        assertEquals("{1=[5], 2=[1, 4], 3=[1, 4, 7], 4=[], 5=[], 7=[]}",dag.getAdj().toString());
        dag.put(8, 1);
        assertEquals("{1=[5, 8], 2=[1, 4], 3=[1, 4, 7], 4=[], 5=[], 7=[], 8=[]}",dag.getAdj().toString());
        dag.put(8, 7);
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

        assertTrue(dag.contains(2));
        assertTrue(dag.contains(1));
        assertFalse(dag.contains(6));
        assertFalse(dag.contains(null));

        assertFalse(dag.put(4));
        assertFalse(dag.put(5,1));
        assertEquals("{1=[5, 8], 2=[1, 4], 3=[1, 4, 7], 4=[], 5=[], 7=[8], 8=[]}",dag.getAdj().toString());
    }

    @Test
    public void lca() {
        DAG<Integer> dag = new DAG<>(2);
        dag.put(3);
        dag.put(4, 2); //       5   8
        dag.put(4, 3); //       |  /|
        dag.put(1, 2); //       ↓ ↙ ↓
        dag.put(1, 3); //  4    1   7
        dag.put(7, 3); //  |\  /|  /
        dag.put(5, 1); //  ↓ ↙↘ ↓ ↙
        dag.put(8, 1); //  2    3
        dag.put(8, 7);

        assertEquals(5, (int) dag.lca(2, 3));
        assertEquals(8, (int) dag.lca(1, 7));
        //assertEquals(4, (int) dag.lca(2, 4));//todo
        assertEquals(5, (int) dag.lca(2, 1));
        assertNull(dag.lca(5, 8));
        assertNull(dag.lca(null, 1));
        assertNull(dag.lca(1, null));
    }

    @Test
    public void testGetAncestors() {
        DAG<Integer> dag = new DAG<>(2);
        dag.put(3);
        dag.put(4, 2); //       5   8
        dag.put(4, 3); //       |  /|
        dag.put(1, 2); //       ↓ ↙ ↓
        dag.put(1, 3); //  4    1   7
        dag.put(7, 3); //  |\  /|  /
        dag.put(5, 1); //  ↓ ↙↘ ↓ ↙
        dag.put(8, 1); //  2    3
        dag.put(8, 7);

        assertEquals("{1=0, 4=0, 5=1, 8=1}",
                dag.getAncestors(2, new HashMap<>(), 0).toString());
        assertEquals("{5=0, 8=0}",
                dag.getAncestors(1, new HashMap<>(), 0).toString());
        assertEquals("{1=0, 4=0, 5=1, 7=0, 8=1}",
                dag.getAncestors(3, new HashMap<>(), 0).toString());
        assertEquals("{}",
                dag.getAncestors(4, new HashMap<>(), 0).toString());
        //todo test null etc
    }
}