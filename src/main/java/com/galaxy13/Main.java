package com.galaxy13;

import com.galaxy13.Homework11.BST;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BST bst = new BST();
        bst.add(2);
        bst.add(5);
        bst.add(10);
        bst.add(1);

        System.out.println(bst.find(1));
        System.out.println(bst.find(3));

        BST bstForSortedList = new BST();
        bstForSortedList.addSortedList(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        bstForSortedList.find(3);
        bstForSortedList.remove(10);
        System.out.println(bstForSortedList.find(10));

        bstForSortedList.printBalancedTree();
    }
}