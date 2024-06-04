package com.hanson.mvc.controller;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的前序、中序、后序、层序遍历
 *
 * @author hanson
 * @date 2024/3/13 13:56
 */
public class Traversal {

    // 定义树结构
    static class TreeNode {
        String val;
        TreeNode left;
        TreeNode right;

        public TreeNode(String val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    // 前序遍历 根左右
    public static void preorderTraversal(TreeNode root) {
        if (root != null){
            System.out.println(root.val + " ");
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
    }

    // 中序遍历 左根右
    public static void inorderTraversal(TreeNode root) {
        if (root != null){
            inorderTraversal(root.left);
            System.out.println(root.val + " ");
            inorderTraversal(root.right);
        }
    }

    // 后序遍历 左右根
    public static void postorderTraversal(TreeNode root) {
        if (root != null){
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            System.out.println(root.val + " ");
        }
    }

    // 层序遍历
    public static void levelOrderTraversal(TreeNode root){
        if (root == null) return;

        // 我们使用队列来辅助进行二叉树的层序遍历。队列的基本操作有 offer 和 poll：
        //
        //offer(E e) 方法用于将指定的元素插入到队列中，如果队列满了则返回 false。
        //poll() 方法用于从队列中取出并删除头部元素，如果队列为空则返回 null。
        //在二叉树的层序遍历中，我们首先将根节点入队，然后在循环中不断出队并访问节点，同时将其左右子节点入队，直到队列为空为止。这样可以保证按照层次遍历每个节点。
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.println(node.val + " ");

            if (node.left != null){
                queue.offer(node.left);
            }

            if (node.right != null){
                queue.offer(node.right);
            }
        }
    }

    public static void main(String[] args) {
        // 构造一个二叉树
        TreeNode root = new TreeNode("A");
        root.left = new TreeNode("B");
        root.right = new TreeNode("C");
        root.left.left = new TreeNode("D");
        root.left.right = new TreeNode("E");
        root.right.left = new TreeNode("F");
        root.right.right = new TreeNode("G");
        root.left.left.left = new TreeNode("H");
        root.left.left.right = new TreeNode("I");
        root.left.right.right = new TreeNode("J");
        root.right.left.right = new TreeNode("K");

        // 前序遍历结果：
        System.out.println("前序遍历结果：");
        preorderTraversal(root);

        // 中序遍历结果：
        System.out.println("中序遍历结果：");
        inorderTraversal(root);

        // 后序遍历结果：
        System.out.println("后序遍历结果：");
        postorderTraversal(root);

        // 层序遍历结果：
        System.out.println("层序遍历结果：");
        levelOrderTraversal(root);
    }
}
