package consistentHash.practise;

import consistentHash.IHashService;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHash<T> {
    private final IHashService iHashService;
    private final int numOfReplicas;
    private final SortedMap<Long, T> circle = new TreeMap<>();

    public ConsistentHash(IHashService iHashService, int numOfReplicas, Collection<T> nodes) {
        this.iHashService = iHashService;
        this.numOfReplicas = numOfReplicas;
        for (T node : nodes) {
            add(node);
        }
    }


    public void add(T node) {
        for (int i = 0; i < this.numOfReplicas; i++) {
            circle.put(this.iHashService.hash(node.toString() + i), node);
        }
    }

    public void remove(T node) {
        for (int i = 0; i < numOfReplicas; i++) {
            circle.remove(iHashService.hash(node.toString() + i));
        }
    }

    public T get(String key) {
        if (circle.isEmpty()) return null;

        long hash = iHashService.hash(key);

        if (!circle.containsKey(hash)) {
            // tailMap(K fromKey)
            // Returns a view of the portion of this map whose keys are greater than or equal to fromKey
            SortedMap<Long, T> tailMap = circle.tailMap(hash);
            // 如果没有比这个hash大的key，那么落在第一个节点上
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }

}
