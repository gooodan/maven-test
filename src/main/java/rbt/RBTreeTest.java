package rbt;

import java.util.Scanner;

public class RBTreeTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RBTree<String, Object> rbTree = new RBTree<>();

        while (true) {
            System.out.println("请输入key: ");
            String key = scanner.next();
            System.out.println();
            rbTree.insert(key, null);
            TreeOperation.show(rbTree.getRoot());
        }
    }
}
