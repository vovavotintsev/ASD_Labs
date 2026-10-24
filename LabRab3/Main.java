package LabRab3;

public class Main {

    public static void main(String[] args) {
        Node root = new Node(10);

        root.left = new Node(5);
        root.right = new Node(20);

        root.left.left = new Node(3);
        root.left.right = new Node(5);

        root.right.left = new Node(10);
        root.right.right = new Node(7);

        root.left.left.left = new Node(20);

        System.out.println("Вершины с одинаковыми номерами:");

        TreeSearch search = new TreeSearch();
        search.findSameValues(root);
    }
}
