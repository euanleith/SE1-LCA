import java.util.HashMap;
import java.util.HashSet;

public class DAG<T extends Comparable<T>> {

    private final HashMap<T, HashSet<T>> adj; // adjacency matrix of vertex : parents

    public DAG(T root) {
        adj = new HashMap<>();
        adj.put(root, new HashSet<>());
    }

    /**
     * Adds the given node 'childNode' to the list of 'node's children
     * 'node' added if not already in dag
     * Note: can't add null node
     * @param node parent node
     * @param childNode node to be child of 'node'
     * @return true if childNode not in 'node's list of children, false if either node is null, otherwise false
     */
    public boolean put(T node, T childNode) {
        if (node == null || childNode == null) return false;
        if (!adj.containsKey(node)) adj.put(node, new HashSet<>());
        if (adj.get(childNode).contains(node)) return false;
        adj.get(childNode).add(node);
        return true;
    }

    /**
     * Adds the given node to the dag if not already in it
     * Note: can't add null node
     * @param node node
     * @return true if node not in dag, false if node is null, otherwise false
     */
    public boolean put(T node) {
        if (node == null) return false;
        if (adj.containsKey(node)) return false;
        adj.put(node, new HashSet<>());
        return true;
    }

    /**
     * Returns whether dag contains given node
     * @param node node
     * @return true if dag contains node, otherwise false
     */
    public boolean contains(T node) {
        return adj.containsKey(node);
    }


    /**
     * Finds the lowest common ancestor (lca) of the two given nodes
     * by finding the ancestors of each and their depths from that node,
     * and finding the common node between these ancestors with the highest depth
     * Note: neither given nodes can be null, nor not in the dag
     * @param node1 first node
     * @param node2 second node
     * @return the lowest common ancestor of the two given nodes
     * null if either node given is null, or isn't in the dag
     */
    public T lca(T node1, T node2) {
        if (node1 == null || node2 == null ||
            !adj.containsKey(node1) || !adj.containsKey(node2)) return null;
        HashMap<T, Integer> ancestors1 = getAncestors(node1);
        HashMap<T, Integer> ancestors2 = getAncestors(node2);
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
     * Finds the ancestors of a given node by depth-first search
     * Note: a node is considered one of it's ancestors
     * @param node node to get ancestors of
     * @return map of all ancestors of the given node and their depths
     */
    public HashMap<T, Integer> getAncestors(T node) {
        if (node == null || !adj.containsKey(node)) return null;
        return getAncestors(node, new HashMap<>(), 1);
    }

    /**
     * Finds the ancestors of a given node by depth-first search
     * Note: a node is considered one of it's ancestors
     * @param node node to get ancestors of
     * @param ancestors current map of ancestor : depth
     * @param depth current depth of search
     * @return map of all ancestors of the given node and their depths
     */
    private HashMap<T, Integer> getAncestors(T node, HashMap<T, Integer> ancestors, int depth) {
        // add new direct ancestors
        HashSet<T> directAncestors = adj.get(node);
        boolean foundNewAncestor = false;
        for (T ancestor : directAncestors) {
            if (!ancestors.containsKey(ancestor)) {
                foundNewAncestor = true;
                ancestors.put(ancestor, depth);
            }
        }
        if (!ancestors.containsKey(node)) {
            ancestors.put(node, depth-1);
            foundNewAncestor = true;
        }

        // return if done
        if (!foundNewAncestor) return ancestors;

        // recurse through direct ancestors
        for (T ancestor : directAncestors) {
            getAncestors(ancestor, ancestors, depth+1);
        }

        return ancestors;
    }

    /**
     * @return adjacency matrix of the dag
     * of the form vertex : parents
     */
    HashMap<T, HashSet<T>> getAdj() {
        return adj;
    }
}
