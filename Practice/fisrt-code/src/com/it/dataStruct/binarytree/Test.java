package com.it.dataStruct.binarytree;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        for (int val : new int[]{1,2,3,4,5,6,7}){
            tree.insert(val);
        }

        //前序遍历
        for (int num : tree.preorderTraversal()){
            System.out.print(num + " ");
        }
        System.out.println();

        //中序遍历
        for (int num : tree.inorderTraversal()){
            System.out.print(num + " ");
        }
        System.out.println();

        //后序遍历
        for (int num : tree.postorderTraversal()){
            System.out.print(num + " ");
        }
        System.out.println();

        //层序遍历
        for (List<Integer> level : tree.levelorderTraversal()){
            for (int num : level){
                System.out.print(num + " ");
            }
        }
        System.out.println();
    }
}
