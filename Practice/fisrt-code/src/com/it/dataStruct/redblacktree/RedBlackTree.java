import java.util.ArrayList;
import java.util.List;

// 红黑树节点
class RBNode {
    int key;
    RBNode left, right, parent;
    boolean isRed; // true为红色，false为黑色

    public RBNode(int key) {
        this.key = key;
        this.isRed = true; // 新节点默认红色
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}

public class RedBlackTree {
    private RBNode root;
    private static final RBNode NIL = new RBNode(0); // 哨兵节点（所有叶子的父节点）

    public RedBlackTree() {
        NIL.isRed = false; // 哨兵节点为黑色
        root = NIL;
    }

    // 左旋操作
    private void rotateLeft(RBNode x) {
        RBNode y = x.right;
        x.right = y.left;

        if (y.left != NIL) {
            y.left.parent = x;
        }
        y.parent = x.parent;

        if (x.parent == NIL) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    // 右旋操作
    private void rotateRight(RBNode y) {
        RBNode x = y.left;
        y.left = x.right;

        if (x.right != NIL) {
            x.right.parent = y;
        }
        x.parent = y.parent;

        if (y.parent == NIL) {
            root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }
        x.right = y;
        y.parent = x;
    }

    // 插入后修复红黑树性质
    private void fixInsert(RBNode z) {
        while (z.parent.isRed) { // 父节点为红色才需要修复
            if (z.parent == z.parent.parent.left) {
                RBNode y = z.parent.parent.right; // 叔叔节点

                // 情况1：叔叔为红色，只需重新着色
                if (y.isRed) {
                    z.parent.isRed = false;
                    y.isRed = false;
                    z.parent.parent.isRed = true;
                    z = z.parent.parent;
                } else {
                    // 情况2：叔叔为黑色，且z是右孩子（先转为情况3）
                    if (z == z.parent.right) {
                        z = z.parent;
                        rotateLeft(z);
                    }
                    // 情况3：叔叔为黑色，且z是左孩子（旋转+着色）
                    z.parent.isRed = false;
                    z.parent.parent.isRed = true;
                    rotateRight(z.parent.parent);
                }
            } else {
                // 镜像情况（父节点是右孩子）
                RBNode y = z.parent.parent.left;

                if (y.isRed) {
                    z.parent.isRed = false;
                    y.isRed = false;
                    z.parent.parent.isRed = true;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rotateRight(z);
                    }
                    z.parent.isRed = false;
                    z.parent.parent.isRed = true;
                    rotateLeft(z.parent.parent);
                }
            }
            if (z == root) break;
        }
        root.isRed = false; // 根节点始终为黑色
    }

    // 插入节点
    public void insert(int key) {
        RBNode z = new RBNode(key);
        RBNode y = NIL;
        RBNode x = root;

        // 找到插入位置（类似普通BST插入）
        while (x != NIL) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;

        if (y == NIL) {
            root = z; // 树为空，直接作为根节点
        } else if (z.key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }

        z.left = NIL;
        z.right = NIL;
        z.isRed = true; // 新节点默认红色

        fixInsert(z); // 修复红黑树性质
    }

    // 中序遍历（验证是否为BST）
    public List<Integer> inorderTraversal() {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(RBNode node, List<Integer> result) {
        if (node != NIL) {
            inorder(node.left, result);
            result.add(node.key);
            inorder(node.right, result);
        }
    }

    // 测试
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        // 插入测试数据
        int[] keys = {7, 3, 18, 10, 22, 8, 11, 26};
        for (int key : keys) {
            tree.insert(key);
        }

        // 中序遍历应为升序（验证BST性质）
        System.out.println("中序遍历结果: " + tree.inorderTraversal());
    }
}
    