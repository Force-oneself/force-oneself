package com.quan.demo.algorithm.tree;

/**
 * RedBlackTree
 *
 * @author Force-oneself
 * @date 2022-06-19
 */
public class RedBlackTree extends AbstractSelfBalancingBinarySearchTree {

    protected enum ColorEnum {
        /**
         *
         */
        RED,
        BLACK
    }

    protected static final RedBlackNode NIL_NODE = new RedBlackNode(null, null, null, null, ColorEnum.BLACK);

    @Override
    public Node insert(int element) {
        Node newNode = super.insert(element);
        newNode.left = NIL_NODE;
        newNode.right = NIL_NODE;
        root.parent = NIL_NODE;
        insertRBFixup((RedBlackNode) newNode);
        return newNode;
    }

    /**
     * Slightly modified delete routine for red-black tree.
     * <p>
     * {@inheritDoc}
     */
    @Override
    protected Node delete(Node deleteNode) {
        // track node that replaces removedOrMovedNode
        if (deleteNode != null && deleteNode != NIL_NODE) {
            return null;
        }
        Node replaceNode;
        // same as deleteNode if it has only one child, and otherwise it replaces deleteNode
        Node removedOrMovedNode = deleteNode;
        ColorEnum removedOrMovedNodeColor = ((RedBlackNode) removedOrMovedNode).color;

        if (deleteNode.left == NIL_NODE) {
            replaceNode = deleteNode.right;
            this.rbTreeTransplant(deleteNode, deleteNode.right);
        } else if (deleteNode.right == NIL_NODE) {
            replaceNode = deleteNode.left;
            this.rbTreeTransplant(deleteNode, deleteNode.left);
        } else {
            removedOrMovedNode = this.getMinimum(deleteNode.right);
            removedOrMovedNodeColor = ((RedBlackNode) removedOrMovedNode).color;
            replaceNode = removedOrMovedNode.right;
            if (removedOrMovedNode.parent == deleteNode) {
                replaceNode.parent = removedOrMovedNode;
            } else {
                this.rbTreeTransplant(removedOrMovedNode, removedOrMovedNode.right);
                removedOrMovedNode.right = deleteNode.right;
                removedOrMovedNode.right.parent = removedOrMovedNode;
            }
            this.rbTreeTransplant(deleteNode, removedOrMovedNode);
            removedOrMovedNode.left = deleteNode.left;
            removedOrMovedNode.left.parent = removedOrMovedNode;
            ((RedBlackNode) removedOrMovedNode).color = ((RedBlackNode) deleteNode).color;
        }

        size--;
        if (removedOrMovedNodeColor == ColorEnum.BLACK) {
            this.deleteRBFixup((RedBlackNode) replaceNode);
        }

        return replaceNode;
    }

    @Override
    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new RedBlackNode(value, parent, left, right, ColorEnum.RED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Node getMinimum(Node node) {
        while (node.left != NIL_NODE) {
            node = node.left;
        }
        return node;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Node getMaximum(Node node) {
        while (node.right != NIL_NODE) {
            node = node.right;
        }
        return node;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Node rotateLeft(Node node) {
        Node temp = node.right;
        temp.parent = node.parent;

        node.right = temp.left;
        if (node.right != NIL_NODE) {
            node.right.parent = node;
        }

        temp.left = node;
        node.parent = temp;

        // temp took over node's place so now its parent should point to temp
        if (temp.parent != NIL_NODE) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }
        return temp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Node rotateRight(Node node) {
        Node temp = node.left;
        temp.parent = node.parent;

        node.left = temp.right;
        if (node.left != NIL_NODE) {
            node.left.parent = node;
        }

        temp.right = node;
        node.parent = temp;

        // temp took over node's place so now its parent should point to temp
        if (temp.parent != NIL_NODE) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }

        return temp;
    }


    /**
     * Similar to original transplant() method in BST but uses nilNode instead of null.
     */
    private Node rbTreeTransplant(Node nodeToReplace, Node newNode) {
        if (nodeToReplace.parent == NIL_NODE) {
            this.root = newNode;
        } else if (nodeToReplace == nodeToReplace.parent.left) {
            nodeToReplace.parent.left = newNode;
        } else {
            nodeToReplace.parent.right = newNode;
        }
        newNode.parent = nodeToReplace.parent;
        return newNode;
    }

    /**
     * Restores Red-Black tree properties after delete if needed.
     */
    private void deleteRBFixup(RedBlackNode x) {
        while (x != root && isBlack(x)) {

            if (x == x.parent.left) {
                RedBlackNode w = (RedBlackNode) x.parent.right;
                // case 1 - sibling is red
                if (isRed(w)) {
                    w.color = ColorEnum.BLACK;
                    ((RedBlackNode) x.parent).color = ColorEnum.RED;
                    rotateLeft(x.parent);
                    // converted to case 2, 3 or 4
                    w = (RedBlackNode) x.parent.right;
                }
                // case 2 sibling is black and both of its children are black
                if (isBlack(w.left) && isBlack(w.right)) {
                    w.color = ColorEnum.RED;
                    x = (RedBlackNode) x.parent;
                } else if (w != NIL_NODE) {
                    // case 3 sibling is black and its left child is red and right child is black
                    if (isBlack(w.right)) {
                        ((RedBlackNode) w.left).color = ColorEnum.BLACK;
                        w.color = ColorEnum.RED;
                        rotateRight(w);
                        w = (RedBlackNode) x.parent.right;
                    }
                    // case 4 sibling is black and right child is red
                    w.color = ((RedBlackNode) x.parent).color;
                    ((RedBlackNode) x.parent).color = ColorEnum.BLACK;
                    ((RedBlackNode) w.right).color = ColorEnum.BLACK;
                    rotateLeft(x.parent);
                    x = (RedBlackNode) root;
                } else {
                    x.color = ColorEnum.BLACK;
                    x = (RedBlackNode) x.parent;
                }
            } else {
                RedBlackNode w = (RedBlackNode) x.parent.left;
                // case 1 - sibling is red
                if (isRed(w)) {
                    w.color = ColorEnum.BLACK;
                    ((RedBlackNode) x.parent).color = ColorEnum.RED;
                    rotateRight(x.parent);
                    // converted to case 2, 3 or 4
                    w = (RedBlackNode) x.parent.left;
                }
                // case 2 sibling is black and both of its children are black
                if (isBlack(w.left) && isBlack(w.right)) {
                    w.color = ColorEnum.RED;
                    x = (RedBlackNode) x.parent;
                } else if (w != NIL_NODE) {
                    // case 3 sibling is black and its right child is red and left child is black
                    if (isBlack(w.left)) {
                        ((RedBlackNode) w.right).color = ColorEnum.BLACK;
                        w.color = ColorEnum.RED;
                        rotateLeft(w);
                        w = (RedBlackNode) x.parent.left;
                    }
                    // case 4 sibling is black and left child is red
                    w.color = ((RedBlackNode) x.parent).color;
                    ((RedBlackNode) x.parent).color = ColorEnum.BLACK;
                    ((RedBlackNode) w.left).color = ColorEnum.BLACK;
                    rotateRight(x.parent);
                    x = (RedBlackNode) root;
                } else {
                    x.color = ColorEnum.BLACK;
                    x = (RedBlackNode) x.parent;
                }
            }

        }
    }

    private boolean isBlack(Node node) {
        return node != null && ((RedBlackNode) node).color == ColorEnum.BLACK;
    }

    private boolean isRed(Node node) {
        return node != null && ((RedBlackNode) node).color == ColorEnum.RED;
    }

    /**
     * Restores Red-Black tree properties after insert if needed. Insert can
     * break only 2 properties: root is red or if node is red then children must
     * be black.
     */
    private void insertRBFixup(RedBlackNode currentNode) {
        // current node is always RED, so if its parent is red it breaks
        // Red-Black property, otherwise no fixup needed and loop can terminate
        while (currentNode.parent != root && ((RedBlackNode) currentNode.parent).color == ColorEnum.RED) {
            RedBlackNode parent = (RedBlackNode) currentNode.parent;
            RedBlackNode grandParent = (RedBlackNode) parent.parent;
            if (parent == grandParent.left) {
                RedBlackNode uncle = (RedBlackNode) grandParent.right;
                // case1 - uncle and parent are both red
                // re color both of them to black
                if (((RedBlackNode) uncle).color == ColorEnum.RED) {
                    parent.color = ColorEnum.BLACK;
                    uncle.color = ColorEnum.BLACK;
                    grandParent.color = ColorEnum.RED;
                    // grandparent was recolored to red, so in next iteration we
                    // check if it does not break Red-Black property
                    currentNode = grandParent;
                }
                // case 2/3 uncle is black - then we perform rotations
                else {
                    // case 2, first rotate left
                    if (currentNode == parent.right) {
                        currentNode = parent;
                        rotateLeft(currentNode);
                    }
                    // do not use parent
                    // case 3
                    parent.color = ColorEnum.BLACK;
                    grandParent.color = ColorEnum.RED;
                    rotateRight(grandParent);
                }
            } else if (parent == grandParent.right) {
                RedBlackNode uncle = (RedBlackNode) grandParent.left;
                // case1 - uncle and parent are both red
                // re color both of them to black
                if (uncle.color == ColorEnum.RED) {
                    parent.color = ColorEnum.BLACK;
                    uncle.color = ColorEnum.BLACK;
                    grandParent.color = ColorEnum.RED;
                    // grandparent was recolored to red, so in next iteration we
                    // check if it does not break Red-Black property
                    currentNode = grandParent;
                }
                // case 2/3 uncle is black - then we perform rotations
                else {
                    // case 2, first rotate right
                    if (currentNode == parent.left) {
                        currentNode = parent;
                        rotateRight(currentNode);
                    }
                    // do not use parent
                    // case 3
                    parent.color = ColorEnum.BLACK;
                    grandParent.color = ColorEnum.RED;
                    rotateLeft(grandParent);
                }
            }

        }
        // ensure root is black in case it was colored red in fixup
        ((RedBlackNode) root).color = ColorEnum.BLACK;
    }

    protected static class RedBlackNode extends Node {
        public ColorEnum color;

        public RedBlackNode(Integer value, Node parent, Node left, Node right, ColorEnum color) {
            super(value, parent, left, right);
            this.color = color;
        }
    }
}
