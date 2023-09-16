package com.quan.demo.algorithm.demo;

import java.util.TreeMap;

/**
 * GetFolderTree
 * 给你一个字符串类型的数组arr，譬如:
 * String[] arr = { "b\st", "d\", "a\d\e", "a\b\c" };
 * 把这些路径中蕴含的目录结构给打印出来，子目录直接列在父目录下面，并比父目录向右进两格，就像这样:
 * <pre>
 * a
 *   b
 *     c
 *   d
 *     e
 * b
 *   cst
 * d
 * </pre>
 * 同一级的需要按字母顺序排列不能乱
 *
 * @author Force-oneself
 * @date 2022-07-13
 */
public class GetFolderTree {

    public static class Node {
        // 上一个节点是通过哪条路，到我的
        public String path;
        // key : node下级的路   value：node在key这条路上对应的节点是什么
        public TreeMap<String, Node> nextMap;

        public Node(String p) {
            this.path = p;
            nextMap = new TreeMap<>();
        }
    }

    /**
     * folderPaths ->  [   "a\b\c","a\b\s" , "a\d\e" ,"e\f\sty"     ]
     *
     * @param folderPaths folderPaths
     */
    public static void print(String[] folderPaths) {
        if (folderPaths == null || folderPaths.length == 0) {
            return;
        }
        // 根据所有字符串，把前缀树建立好，头节点为head
        Node head = generateFolderTree(folderPaths);

        // 打印
        printProcess(head, 0);
    }

    public static Node generateFolderTree(String[] folderPaths) {
        // 系统根目录, 前缀树头节点
        Node head = new Node("");
        // 拿出每一个绝对路径
        for (String foldPath : folderPaths) {
            // java 特性，用一个"\"做分割的意思
            String[] paths = foldPath.split("\\\\");
            Node cur = head;
            // "a"  , "b"   ,"c"
            for (String path : paths) {
                if (!cur.nextMap.containsKey(path)) {
                    cur.nextMap.put(path, new Node(path));
                }
                cur = cur.nextMap.get(path);
            }
        }
        return head;
    }

    /**
     * head节点，当前在level层
     *
     * @param node  node
     * @param level level
     */
    public static void printProcess(Node node, int level) {
        if (level != 0) {
            // 2 * (level - 1)
            System.out.println(get4nSpace(level) + node.path);
        }
        for (Node next : node.nextMap.values()) {
            printProcess(next, level + 1);
        }
    }

    public static String get4nSpace(int n) {
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < n; i++) {
            res.append("    ");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        //    "a\b\c" '\'  a,b,c
        String test = "a\\b\\cd";

        //  "a\b\c"    "\"    a,b,c
        //    \\\\    \\   \
        String[] arr = test.split("\\\\");
        for (String str : arr) {
            System.out.println(str);
        }
    }

}
