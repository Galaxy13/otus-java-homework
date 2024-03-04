package com.galaxy13.Homework11;

import java.util.LinkedList;
import java.util.List;

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }
}

public class BST implements SearchTree {
    Node root;

    private Node recursiveAdd(Node root, int newValue) {
        if (root == null) {
            return new Node(newValue);
        }
        if (root.value > newValue) {
            root.left = recursiveAdd(root.left, newValue);
        } else if (root.value < newValue) {
            root.right = recursiveAdd(root.right, newValue);
        }
        return root;
    }

    private boolean recursiveFind(Node root, int value) {
        if (root == null) {
            return false;
        }
        if (root.value == value) {
            return true;
        }
        if (root.value > value) {
            return recursiveFind(root.left, value);
        } else {
            return recursiveFind(root.right, value);
        }
    }

    private int rightTreeSmallest(Node root) {
        if (root.left == null) {
            return root.value;
        } else {
            return rightTreeSmallest(root.left);
        }
    }

    private Node recursiveRemove(Node root, int value) throws Exception {
        if (root == null) {
            throw new Exception("Can't remove non-existing value");
        }
        if (root.value == value) {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                int smallestRightSubTreeValue = rightTreeSmallest(root.right);
                root.value = smallestRightSubTreeValue;
                root.right = recursiveRemove(root.right, smallestRightSubTreeValue);
                return root;
            }
        } else {
            if (root.value > value) {
                root.left = recursiveRemove(root.left, value);
            } else {
                root.right = recursiveRemove(root.right, value);
            }
            return root;
        }
    }

    private Node recursiveAddSortedList(List<Integer> sortedList, int start, int end) {
        if (start > end) {
            return null;
        }

        int middle = (start + end) / 2;
        Node newNode = new Node(sortedList.get(middle));
        newNode.left = recursiveAddSortedList(sortedList, start, middle - 1);
        newNode.right = recursiveAddSortedList(sortedList, middle + 1, end);
        return newNode;
    }

    @Override
    public boolean find(int value) {
        return recursiveFind(root, value);
    }

    public void add(int newValue) {
        root = recursiveAdd(root, newValue);
    }

    public void remove(int value) throws Exception {
        root = recursiveRemove(root, value);
        System.out.println("Value removed from BST");
    }

    public void addSortedList(List<Integer> sortedList) {
        root = recursiveAddSortedList(sortedList, 0, sortedList.size() - 1);
    }

    public void printBalancedTree() {
        if (root == null) {
            return;
        }

        LinkedList<Node> levelNodes = new LinkedList<>();
        LinkedList<Integer> nodeValues = new LinkedList<>();
        levelNodes.add(root);

        while (!levelNodes.isEmpty()) {
            Node node = levelNodes.remove();
            nodeValues.add(node.value);
            if (node.left != null) {
                levelNodes.add(node.left);
            }
            if (node.right != null) {
                levelNodes.add(node.right);
            }
        }
        int treeHeight = nodeValues.size();
        int baseLevel = 0;
        int baseIndex = 0;
        int spaceLength = treeHeight;
        for (int i = 0; i < nodeValues.size(); i++) {
            System.out.print(" ".repeat(spaceLength) + nodeValues.get(i));
            if (i == baseIndex) {
                baseLevel += 1;
                baseIndex += 2 * baseLevel;
                System.out.print(" ".repeat(spaceLength) + "\n");
                spaceLength /= 2;
            }
        }
    }
}
