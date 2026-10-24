package LabRab3;

public class TreeSearch {

    private int[] values;
    private String[] paths;
    private int count;

    public void findSameValues(Node root) {
        values = new int[16];
        paths = new String[16];
        count = 0;

        collect(root, "root");
        sortByValue();
        printGroups();
    }

    // Обход в прямом порядке
    private void collect(Node node, String path) {
        if (node == null) {
            return;
        }
        addEntry(node.value, path);
        collect(node.left, path + ".left");
        collect(node.right, path + ".right");
    }

    private void addEntry(int value, String path) {
        if (count == values.length) {
            int newLen = values.length * 2;
            int[] newValues = new int[newLen];
            String[] newPaths = new String[newLen];
            for (int i = 0; i < count; i++) {
                newValues[i] = values[i];
                newPaths[i] = paths[i];
            }
            values = newValues;
            paths = newPaths;
        }
        values[count] = value;
        paths[count] = path;
        count++;
    }
        
    private void sortByValue() {
        for (int i = 1; i < count; i++) {
            int keyValue = values[i];
            String keyPath = paths[i];
            int j = i - 1;
            while (j >= 0 && values[j] > keyValue) {
                values[j + 1] = values[j];
                paths[j + 1] = paths[j];
                j--;
            }
            values[j + 1] = keyValue;
            paths[j + 1] = keyPath;
        }
    }


    private void printGroups() {
        boolean found = false;
        int i = 0;
        while (i < count) {
            int j = i + 1;
            while (j < count && values[j] == values[i]) {
                j++;
            }
            if (j - i > 1) {
                found = true;
                String line = "Значение " + values[i]
                        + " встречается в вершинах: ";
                for (int k = i; k < j; k++) {
                    line = line + paths[k];
                    if (k < j - 1) {
                        line = line + ", ";
                    }
                }
                System.out.println(line);
            }
            i = j;
        }
        if (!found) {
            System.out.println("Вершин с одинаковыми номерами нет.");
        }
    }
}
