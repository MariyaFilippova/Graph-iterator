import java.util.*;

class Node {
    int value;
    Collection<Node> siblings;

    Node(int value, Collection<Node> siblings) {
        this.value = value;
        this.siblings = siblings;
    }
}

class NodeIterator implements Iterator<Node> {
    final ArrayList<Node> nodes;
    final Node root;
    int cursor = 0;

    NodeIterator(Node start) {
        nodes = new ArrayList<>();
        root = start;
        dfs(start, new HashSet<>());
    }

    private void dfs(Node root, Set<Integer> visited) {
        if (root == null) {
            return;
        }
        if (visited.contains(root.value)) {
            return;
        }
        nodes.add(root);
        visited.add(root.value);
        for (Node child : root.siblings) {
            dfs(child, visited);
        }
    }

    @Override
    public boolean hasNext() {
        return cursor < nodes.size();
    }

    @Override
    public Node next() {
        return nodes.get(cursor++);
    }

    public static void main(String[] args) {
        Node start = new Node(1, Arrays.asList(
                new Node(2, Collections.emptyList()),
                new Node(3, Collections.singleton(
                        new Node(4, Collections.emptyList()))
                )
        ));
        NodeIterator iterator = new NodeIterator(start);
        while (iterator.hasNext()) {
            System.out.println(iterator.next().value);
        }
    }
}
