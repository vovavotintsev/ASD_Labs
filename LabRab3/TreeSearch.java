package LabRab3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TreeSearch {

    /**
     * Находит все значения, которые встречаются более чем в одной вершине.
     */
    public static void findSameValues(Node root) {
        Map<Integer, List<String>> map = new TreeMap<>();

        collect(root, "root", map);

        boolean found = false;

        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            if (entry.getValue().size() > 1) {
                found = true;
                System.out.println(
                        "Значение " + entry.getKey() +
                                " встречается в вершинах: " +
                                String.join(", ", entry.getValue())
                );
            }
        }

        if (!found) {
            System.out.println("Вершин с одинаковыми номерами нет.");
        }
    }

    private static void collect(Node node,
                                String path,
                                Map<Integer, List<String>> map) {

        if (node == null) {
            return;
        }

        map.computeIfAbsent(node.value, key -> new ArrayList<>()).add(path);

        collect(node.left, path + ".left", map);
        collect(node.right, path + ".right", map);
    }
}