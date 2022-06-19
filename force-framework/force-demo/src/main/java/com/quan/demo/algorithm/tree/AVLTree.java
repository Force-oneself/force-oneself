package com.quan.demo.algorithm.tree;

/**
 * AVLTree
 *
 * @author Force-oneself
 * @date 2022-06-19
 */
public class AVLTree<K extends Comparable<K>, V> {

    private Node<K, V> root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    private Node<K, V> rightRotate(Node<K, V> cur) {
        Node<K, V> left = cur.l;
        cur.l = left.r;
        left.r = cur;
        cur.h = Math.max((cur.l != null ? cur.l.h : 0), (cur.r != null ? cur.r.h : 0)) + 1;
        left.h = Math.max((left.l != null ? left.l.h : 0), left.r.h) + 1;
        return left;
    }

    private Node<K, V> leftRotate(Node<K, V> cur) {
        Node<K, V> right = cur.r;
        cur.r = right.l;
        right.l = cur;
        cur.h = Math.max((cur.l != null ? cur.l.h : 0), (cur.r != null ? cur.r.h : 0)) + 1;
        right.h = Math.max(right.l.h, (right.r != null ? right.r.h : 0)) + 1;
        return right;
    }

    private Node<K, V> maintain(Node<K, V> cur) {
        if (cur == null) {
            return null;
        }
        int leftHeight = cur.l != null ? cur.l.h : 0;
        int rightHeight = cur.r != null ? cur.r.h : 0;
        if (Math.abs(leftHeight - rightHeight) > 1) {
            if (leftHeight > rightHeight) {
                int leftLeftHeight = cur.l != null && cur.l.l != null ? cur.l.l.h : 0;
                int leftRightHeight = cur.l != null && cur.l.r != null ? cur.l.r.h : 0;
                if (leftLeftHeight < leftRightHeight) {
                    cur.l = leftRotate(cur.l);
                }
                cur = rightRotate(cur);
            } else {
                int rightLeftHeight = cur.r != null && cur.r.l != null ? cur.r.l.h : 0;
                int rightRightHeight = cur.r != null && cur.r.r != null ? cur.r.r.h : 0;
                if (rightRightHeight < rightLeftHeight) {
                    cur.r = rightRotate(cur.r);
                }
                cur = leftRotate(cur);
            }
        }
        return cur;
    }

    private Node<K, V> findLastIndex(K key) {
        Node<K, V> pre = root;
        Node<K, V> cur = root;
        while (cur != null) {
            pre = cur;
            if (key.compareTo(cur.k) == 0) {
                break;
            } else if (key.compareTo(cur.k) < 0) {
                cur = cur.l;
            } else {
                cur = cur.r;
            }
        }
        return pre;
    }

    private Node<K, V> findLastNoSmallIndex(K key) {
        Node<K, V> ans = null;
        Node<K, V> cur = root;
        while (cur != null) {
            if (key.compareTo(cur.k) == 0) {
                ans = cur;
                break;
            } else if (key.compareTo(cur.k) < 0) {
                ans = cur;
                cur = cur.l;
            } else {
                cur = cur.r;
            }
        }
        return ans;
    }

    private Node<K, V> findLastNoBigIndex(K key) {
        Node<K, V> ans = null;
        Node<K, V> cur = root;
        while (cur != null) {
            if (key.compareTo(cur.k) == 0) {
                ans = cur;
                break;
            } else if (key.compareTo(cur.k) < 0) {
                cur = cur.l;
            } else {
                ans = cur;
                cur = cur.r;
            }
        }
        return ans;
    }

    private Node<K, V> add(Node<K, V> cur, K key, V value) {
        if (cur == null) {
            return new Node<K, V>(key, value);
        } else {
            if (key.compareTo(cur.k) < 0) {
                cur.l = add(cur.l, key, value);
            } else {
                cur.r = add(cur.r, key, value);
            }
            cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
            return maintain(cur);
        }
    }

    /**
     * 在cur这棵树上，删掉key所代表的节点
     * 返回cur这棵树的新头部
     *
     * @param cur cur
     * @param key key
     * @return /
     */
    private Node<K, V> delete(Node<K, V> cur, K key) {
        if (key.compareTo(cur.k) > 0) {
            cur.r = delete(cur.r, key);
        } else if (key.compareTo(cur.k) < 0) {
            cur.l = delete(cur.l, key);
        } else {
            if (cur.l == null && cur.r == null) {
                cur = null;
            } else if (cur.l == null) {
                cur = cur.r;
            } else if (cur.r == null) {
                cur = cur.l;
            } else {
                Node<K, V> des = cur.r;
                while (des.l != null) {
                    des = des.l;
                }
                cur.r = delete(cur.r, des.k);
                des.l = cur.l;
                des.r = cur.r;
                cur = des;
            }
        }
        if (cur != null) {
            cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
        }
        return maintain(cur);
    }

    public int size() {
        return size;
    }

    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        Node<K, V> lastNode = findLastIndex(key);
        return lastNode != null && key.compareTo(lastNode.k) == 0;
    }

    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        Node<K, V> lastNode = findLastIndex(key);
        if (lastNode != null && key.compareTo(lastNode.k) == 0) {
            lastNode.v = value;
        } else {
            size++;
            root = add(root, key, value);
        }
    }

    public void remove(K key) {
        if (key == null) {
            return;
        }
        if (containsKey(key)) {
            size--;
            root = delete(root, key);
        }
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }
        Node<K, V> lastNode = findLastIndex(key);
        if (lastNode != null && key.compareTo(lastNode.k) == 0) {
            return lastNode.v;
        }
        return null;
    }

    public K firstKey() {
        if (root == null) {
            return null;
        }
        Node<K, V> cur = root;
        while (cur.l != null) {
            cur = cur.l;
        }
        return cur.k;
    }

    public K lastKey() {
        if (root == null) {
            return null;
        }
        Node<K, V> cur = root;
        while (cur.r != null) {
            cur = cur.r;
        }
        return cur.k;
    }

    public K floorKey(K key) {
        if (key == null) {
            return null;
        }
        Node<K, V> lastNoBigNode = findLastNoBigIndex(key);
        return lastNoBigNode == null ? null : lastNoBigNode.k;
    }

    public K ceilingKey(K key) {
        if (key == null) {
            return null;
        }
        Node<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
        return lastNoSmallNode == null ? null : lastNoSmallNode.k;
    }

    public static class Node<K extends Comparable<K>, V> {
        public K k;
        public V v;
        public Node<K, V> l;
        public Node<K, V> r;
        public int h;

        public Node(K key, V value) {
            k = key;
            v = value;
            h = 1;
        }
    }
}
