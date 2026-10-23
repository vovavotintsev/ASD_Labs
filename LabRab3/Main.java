package LabRab3;

public class Main {

    public static void main(String[] args) {

        /*
         * Пример бинарного дерева:
         *
         *              10
         *            /    \
         *           5      20
         *          / \    /  \
         *         3   5  10   7
         *        /
         *       20
         *
         * Одинаковые номера:
         * 10 — root и root.right.left
         * 5  — root.left и root.left.right
         * 20 — root.right и root.left.left.left
         */

        Node root = new Node(10);

        root.left = new Node(5);
        root.right = new Node(20);

        root.left.left = new Node(3);
        root.left.right = new Node(5);

        root.right.left = new Node(10);
        root.right.right = new Node(7);

        root.left.left.left = new Node(20);

        System.out.println("Вершины с одинаковыми номерами:");
        TreeSearch.findSameValues(root);
    }
}