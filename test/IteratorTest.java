import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class IteratorTest {

    @Test
    public void differentCollections() {
        Node start = new Node(1, Arrays.asList(
                new Node(2, Collections.emptyList()),
                new Node(3, Collections.singleton(
                        new Node(4, Collections.emptyNavigableSet()))),
                new Node(5, Collections.emptyNavigableSet()),
                new Node(6, Collections.singleton(new Node(6, Collections.asLifoQueue(new ArrayDeque<>()))))
        ));
        NodeIterator iterator = new NodeIterator(start);
        int[] res = new int[6];
        while (iterator.hasNext()) {
            res[iterator.cursor] = iterator.next().value;
        }
        Assert.assertArrayEquals(res, new int[]{1,2,3,4,5,6});
    }

    @Test
    public void cycle() {
        Node start = new Node(0, new ArrayList<>());
        Node child = new Node(1, new ArrayList<>());
        Node secondChild = new Node(2, new ArrayList<>());
        start.siblings.add(child);
        child.siblings.add(secondChild);
        secondChild.siblings.add(start);
        NodeIterator iterator = new NodeIterator(start);
        int[] res = new int[3];
        while (iterator.hasNext()) {
            res[iterator.cursor] = iterator.next().value;
        }
        Assert.assertArrayEquals(res, new int[]{0,1,2});
    }

    @Test
    public void tree() {
        Node start = new Node(0, new ArrayList<>());
        Node next;
        Node prev = start;
        for (int i = 1; i < 100; i++) {
            next = new Node(i, new LinkedList<>());
            prev.siblings.add(next);
            prev = next;
        }
        NodeIterator iterator = new NodeIterator(start);
        int[] res = new int[100];
        int[] result = new int[100];
        for (int i = 0; i < 100; i++) {
            result[i] = i;
        }
        while (iterator.hasNext()) {
            res[iterator.cursor] = iterator.next().value;
        }
        Assert.assertArrayEquals(res, result);
    }

}
