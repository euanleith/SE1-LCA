import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DAG<T extends Comparable<T>> {
    private HashMap<T, HashSet<T>> adj; // vertex : parents

    DAG(T root) {
        adj = new HashMap<>();
        adj.put(root, new HashSet<>());
    }

    //todo allow addition of just val?
    /**
     *
     * @param val
     * @param childVal
     * @return true if childVal not in val's list of children, otherwise false
     */
    boolean put(T val, T childVal) {
        if (!adj.containsKey(val)) adj.put(val, new HashSet<>());
        if (adj.get(childVal).contains(val)) return false;
        adj.get(childVal).add(val);
        return true;
    }

    boolean put(T val) {
        if (adj.containsKey(val)) return false;
        adj.put(val, new HashSet<>());
        return true;
    }

    boolean contains(T val) {
        return adj.containsKey(val);
    }


    T lca(T val1, T val2) {
        if (val1 == null || val2 == null) return null; //todo
        //todo bfs through val1 into list; then same for val2; and find deepest common between lists
        HashMap<T, Integer> ancestors1 = getAncestors(val1, new HashMap<>(), 0);//todo init size?
        HashMap<T, Integer> ancestors2 = getAncestors(val2, new HashMap<>(), 0);
        HashMap.Entry<T, Integer> lca = null;
        for (HashMap.Entry<T, Integer> e : ancestors1.entrySet()) {
            if (ancestors2.containsKey(e.getKey())) {
                if (lca == null) lca = e;
                else if (e.getValue() > lca.getValue()) lca = e;
            }
        }
        return lca == null ? null : lca.getKey();
    }

    /**
     *
     * @param val node to get ancestors of
     * @param ancestors current map of ancestor : depth
     * @param depth current depth of search
     * @return map of all ancestors of the given node and their depths
     */
    HashMap<T, Integer> getAncestors(T val, HashMap<T, Integer> ancestors, int depth) {

        // add new direct ancestors
        HashSet<T> directAncestors = adj.get(val);
        boolean foundNewAncestor = false;
        for (T ancestor : directAncestors) {
            if (!ancestors.containsKey(ancestor)) {
                foundNewAncestor = true;
                ancestors.put(ancestor, depth);
            }
        }

        // return if done
        if (!foundNewAncestor) return ancestors;

        // recurse through direct ancestors
        for (T ancestor : directAncestors) {
            getAncestors(ancestor, ancestors, depth+1);
        }

        return ancestors;
    }

    HashMap<T, HashSet<T>> getAdj() {
        return adj;
    }
}
