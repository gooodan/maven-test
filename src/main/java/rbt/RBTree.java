package rbt;

/**
 * ①创建RBTree, 定义颜色
 * ②创建RBNode
 * ③辅助方法定义：parentOf(node), isRed(node), isBlack(node), setRed(node), setBlack(node), inOrderPrint()
 * ④左旋方法定义：leftRotate(node)
 * ⑤右旋方法定义： rightRotate(node)
 * ⑥公开插入接口：insert(K key, V value)
 * ⑦内部插入接口：insert(RBNode node)
 * ⑧修正插入导致红黑树失衡的方法定义：insertFixUp(RBNode node)
 * ⑨测试红黑树正确性
 *
 * @param <K>
 * @param <V>
 */
public class RBTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private RBNode root;

    public RBNode getRoot() {
        return root;
    }

    // 获取父节点
    private RBNode parentOf(RBNode node) {
        if (node != null) {
            return node.parent;
        }
        return null;
    }

    // 是否为红色
    private boolean isRed(RBNode node) {
        if (node != null) {
            return node.color == RED;
        }
        return false;
    }

    // 是否为黑色
    private boolean isBlack(RBNode node) {
        if (node != null) {
            return node.color == BLACK;
        }
        return false;
    }

    // 设置红色
    private void setRed(RBNode node) {
        if (node != null) {
            node.setColor(RED);
        }
    }

    // 设置黑色
    private void setBlack(RBNode node) {
        if (node != null) {
            node.setColor(BLACK);
        }
    }

    public void inOrderPrint() {
        inOrderPrint(root);
    }

    private void inOrderPrint(RBNode root) {
        if (root != null) {
            inOrderPrint(root.left);
            System.out.println("key:" + root.getKey() + ", " + "value:" + root.getValue());
            inOrderPrint(root.right);
        }
    }

    /**
     * 左旋方法
     * 左旋示意图：左旋x节点
     *       p                  p
     *      |                  |
     *      x                  y
     *    |   \              |   \
     *   lx    y            x     ry
     *       |   \        |  \
     *      ly    ry     lx   ly
     *
     * 1.将y的左子节点的父节点更新为x，x的右子节点指向y的左子节点（ly）
     * 2.当x的父节点不为Null时，更新y的父节点为x的父节点，并将y替换x在其父节点的位置
     * 3.将x的父节点更新为y，将y的左子节点指向x
     */
    private void leftRotate(RBNode x) {
        RBNode y = x.right;
        // 1.将y的左子节点的父节点更新为x，x的右子节点指向y的左子节点（ly）
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        // 2.当x的父节点不为Null时，更新y的父节点为x的父节点，并将y替换x在其父节点的位置
        if (x.parent != null) {
            y.parent = x.parent;
            // y替换x的位置
            if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        } else { // 说明x是根节点，需要更新y为根节点
            this.root = y;
            this.root.parent = null;
        }
        // 3.将x的父节点更新为y，将y的左子节点指向x
        x.parent = y;
        y.left = x;
    }

    /**
     * 右旋方法
     * 右旋示意图：右旋y节点
     *            p                  p
     *           |                  |
     *           y                  x
     *         |   \              |   \
     *        x     ry           lx    y
     *      |  \                      |  \
     *     lx   ly                   ly   ry
     *
     * 1.将y的左子节点指向x的右子节点，更新x的右子节点的父节点为y
     * 2.当y的父节点不为Null时，更新x的父节点为y的父节点，并将x替换y在其父节点的位置
     * 3.将y的父节点更新为x，将x的右子节点指向y
     */
    private void rightRotate(RBNode y) {
        RBNode x = y.left;
        // 1.将y的左子节点指向x的右子节点，更新x的右子节点的父节点为y
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        // 2.当y的父节点不为Null时，更新x的父节点为y的父节点，并将x替换y在其父节点的位置
        if (y.parent != null) {
            x.parent = y.parent;
            if (y == y.parent.left) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        } else {
            this.root = x;
            this.root.parent = null;
        }
        // 3.将y的父节点更新为x，将x的右子节点指向y
        y.parent = x;
        x.right = y;
    }

    // 公开插入方法
    public void insert(K key, V value) {
        RBNode node = new RBNode();
        node.setKey(key);
        node.setValue(value);
        // 新节点一定是红色节点！！！
        node.setColor(RED);
        insert(node);
    }

    // 辅助插入方法
    private void insert(RBNode node) {
        // 第一步：查找当前node节点的父节点
        RBNode parent = null;
        RBNode x = this.root;

        while (x != null) {
            parent = x;
            // cmp > 0 说明node.key大于x.key，需要到x的右子树去查找
            // cmp == 0 说明node.key等于x.key，需要进行替换操作
            // cmp < 0 说明node.key小于x.key，需要到x的左子树去查找
            int cmp = node.key.compareTo(x.key);
            if (cmp > 0) {
                x = x.right;
            }
            else if (cmp == 0) {
                x.setValue(node.getValue());
                return;
            }
            else {
                x = x.left;
            }
        }

        node.parent = parent;

        // 判断node与parent的key谁大
        if (parent != null) {
            int cmp = node.key.compareTo(parent.getKey());
            // 没有 cmp == 0 相等 的情况，如果有，上一步找node父节点的时候已经找到了
            if (cmp > 0) { // 比parent Key大，放入parent的右子树
                parent.right = node;
            } else { // 比parent Key小，放入parent的左子树
                parent.left = node;
            }
        } else { // 第一个节点直接作为root节点
            this.root = node;
        }

        // 需要修复红黑树平衡
         insertFixup(node);
    }

    /**
     *  插入后修复红黑树平衡的方法
     *      |---情景1：红黑树为空树，将根节点染色为黑色
     *      |---情景2：插入的节点key已存在，不需要处理
     *      |---情景3：插入节点的父节点为黑色，因为所插入的路径，黑色节点没有变化，红黑树依然平衡，不需要处理
     *
     *      |---情景4：插入父节点为红色
     *          |---情景4.1：叔叔节点存在，且为红色（叔-父双红）
     *                      将叔-父节点均染为黑色，爷节点染为红色，并且以爷节点为当前节点，进行下一轮处理
     *          |---情景4.2：叔叔节点不存在，或者为黑色，且父节点在爷爷节点的左子树
     *              |---情景4.2.1：插入节点为父节点的左子节点（LL情况）
     *                            将父节点染为黑色，爷爷染为红色，然后以爷爷节点右旋，就完成了
     *              |---情景4.2.2：插入节点为父节点的右子节点（LR情况）
     *                            以父节点进行一次左旋，得到LL双红的情况（4.2.1），然后指定父节点为当前节点进行下一轮处理
     *          |---情景4.3：叔叔节点不存在，或者为黑色，且父节点在爷爷节点的右子树
     *              |---情景4.3.1：插入节点为父节点的右子节点（RR情况）
     *                            将父节点染为黑色，爷爷染为红色，然后以爷爷节点左旋，就完成了
     *              |---情景4.3.2：插入节点为父节点的左子节点（RL情况）
     *                            以父节点进行一次右旋，得到RR双红的情况（4.3.1），然后指定父节点为当前节点进行下一轮处理
     */
    private void insertFixup(RBNode node) {
        this.root.setColor(BLACK);
        RBNode parent = parentOf(node);
        RBNode gparent = parentOf(parent);

        // 情景4：插入节点的父节点为红色
        // 如果父节点为红色，爷爷节点一定存在，因为根节点不可能为红
        if (parent != null && isRed(parent)) {

            RBNode uncle = null;

            // 父节点为爷节点的左子树
            if (parent == gparent.left) {
                uncle = gparent.right;
                // 情景4.1：叔叔节点存在，且为红色（叔-父双红）
                if (uncle != null && isRed(uncle)) {
                    // 将叔-父节点均染为黑色，爷节点染为红色，并且以爷节点为当前节点，进行下一轮处理
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    insertFixup(gparent);
                    return;
                }

                // 情景4.2：叔叔节点不存在，或者为黑色，且父节点在爷爷节点的左子树
                if (uncle == null || isBlack(uncle)) {
                    // 情景4.2.1：插入节点为父节点的左子节点（LL情况）
                    if (node == parent.left) {
                        // 将父节点染为黑色，爷爷染为红色，然后以爷爷节点右旋，就完成了
                        setBlack(parent);
                        setRed(gparent);
                        rightRotate(gparent);
                        return;
                    }

                    // 情景4.2.2：插入节点为父节点的右子节点（LR情况）
                    if (node == parent.right) {
                        // 以父节点进行一次左旋，得到LL双红的情况（4.2.1），然后指定父节点为当前节点进行下一轮处理
                        leftRotate(parent);
                        insertFixup(parent);
                        return;
                    }
                }
            }
            // 父节点为爷节点的右子树
            else {
                uncle = gparent.left;
                // 情景4.1：叔叔节点存在，且为红色（叔-父双红）
                if (uncle != null && isRed(uncle)) {
                    // 将叔-父节点均染为黑色，爷节点染为红色，并且以爷节点为当前节点，进行下一轮处理
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    insertFixup(gparent);
                    return;
                }

                // 情景4.3：叔叔节点不存在，或者为黑色，且父节点在爷爷节点的右子树
                if (uncle == null || isBlack(uncle)) {
                    // 情景4.3.1：插入节点为父节点的右子节点（RR情况）
                    if (node == parent.right) {
                        // 将父节点染为黑色，爷爷染为红色，然后以爷爷节点左旋，就完成了
                        setBlack(parent);
                        setRed(gparent);
                        leftRotate(gparent);
                        return;
                    }

                    // 情景4.3.2：插入节点为父节点的左子节点（RL情况）
                    if (node == parent.left) {
                        // 以父节点进行一次右旋，得到RR双红的情况（4.3.1），然后指定父节点为当前节点进行下一轮处理
                        rightRotate(parent);
                        insertFixup(parent);
                        return;
                    }
                }
            }
        }

    }

    static class RBNode <K extends Comparable<K>, V> {

        private RBNode parent;
        private RBNode left;
        private RBNode right;
        private K key;
        private V value;
        private boolean color;

        public RBNode() {
        }

        public RBNode(RBNode parent, RBNode left, RBNode right, K key, V value, boolean color) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.key = key;
            this.value = value;
            this.color = color;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }
    }


}
