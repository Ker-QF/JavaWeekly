package com.it.dataStruct.binarytree;

import java.util.*;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTree {
    private TreeNode root;

    public BinaryTree() {
        root = null;
    }

    // 插入节点（层次插入）
    public void insert(int val){
        TreeNode newNode = new TreeNode(val);

        if (root == null){
            root = newNode;
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            TreeNode current = queue.poll();

            if (current.left == null){
                current.left = newNode;
                return;
            }else{
                queue.add(current.left);
            }

            if (current.right == null){
                current.right = newNode;
                return;
            }else{
                queue.add(current.right);
            }
        }
    }

    //前序遍历
    public List<Integer> preorderTraversal(){
        List<Integer> result = new ArrayList<>();
        preorder(root,result);
        return result;
    }

    private void preorder(TreeNode node,List<Integer> result) {
        if (node == null) return;
        result.add(node.val);
        preorder(node.left,result);
        preorder(node.right,result);
    }

    //中序遍历
    public List<Integer> inorderTraversal(){
        List<Integer> result = new ArrayList<>();
        inorder(root,result);
        return result;
    }

    private void inorder(TreeNode node, List<Integer> result) {
        if (node == null) return;
        inorder(node.left,result);
        result.add(node.val);
        inorder(node.right,result);
    }

    //后序遍历
    public List<Integer> postorderTraversal(){
        List<Integer> result = new ArrayList<>();
        postorder(root,result);
        return result;
    }

    private void postorder(TreeNode node, List<Integer> result) {
        if (node == null) return;
        postorder(node.left,result);
        postorder(node.right,result);
        result.add(node.val);
    }

    //层序遍历
    public List<List<Integer>> levelorderTraversal(){
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i =  0;i < levelSize;i++){
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }

            result.add(currentLevel);
        }
        return result;
    }
}
