package com.quan.demo.algorithm;

/**
 * SegmentTree
 *
 * @author Force-oneself
 * @date 2022-06-10
 */
public class SegmentTree {

    /**
     * arr[]为原序列的信息从0开始，但在arr里是从1开始的
     */
    private final int[] arr;

    /**
     * sum[]模拟线段树维护区间和
     */
    private final int[] sum;
    /**
     * lazy[]为累加懒惰标记
     */
    private final int[] lazy;
    /**
     * change[]为更新的值
     */
    private final int[] change;
    /**
     * update[]为更新慵懒标记
     */
    private final boolean[] update;

    public SegmentTree(int[] origin) {
        int len = origin.length + 1;
        // arr[0] 不用  从1开始使用
        arr = new int[len];
        System.arraycopy(origin, 0, arr, 1, len - 1);
        int treeSize = len << 2;
        // 用来支持脑补概念中，某一个范围的累加和信息
        sum = new int[treeSize];
        // 用来支持脑补概念中，某一个范围沒有往下傳遞的纍加任務
        lazy = new int[treeSize];
        // 用来支持脑补概念中，某一个范围有没有更新操作的任务
        change = new int[treeSize];
        // 用来支持脑补概念中，某一个范围更新任务，更新成了什么
        update = new boolean[treeSize];
    }

    private void pushUp(int rt) {
        // 左右子树的和相加
        sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }

    /**
     * 之前的，所有懒增加，和懒更新，从父范围，发给左右两个子范围
     *
     * @param rootIdx  父节点坐标
     * @param leftNum  表示左子树元素结点个数
     * @param rightNum 表示右子树结点个数
     */
    private void pushDown(int rootIdx, int leftNum, int rightNum) {
        int leftChildIdx = rootIdx << 1;
        int rightChildIdx = leftChildIdx | 1;
        if (update[rootIdx]) {
            update[leftChildIdx] = true;
            update[rightChildIdx] = true;
            change[leftChildIdx] = change[rootIdx];
            change[rightChildIdx] = change[rootIdx];
            lazy[leftChildIdx] = 0;
            lazy[rightChildIdx] = 0;
            sum[leftChildIdx] = change[rootIdx] * leftNum;
            sum[rightChildIdx] = change[rootIdx] * rightNum;
            update[rootIdx] = false;
        }
        if (lazy[rootIdx] != 0) {
            lazy[leftChildIdx] += lazy[rootIdx];
            sum[leftChildIdx] += lazy[rootIdx] * leftNum;
            lazy[rightChildIdx] += lazy[rootIdx];
            sum[rightChildIdx] += lazy[rootIdx] * rightNum;
            lazy[rootIdx] = 0;
        }
    }

    /**
     * 在初始化阶段，先把sum数组，填好
     * 在arr[l~r]范围上，去build，1~N，
     * curIdx :  这个范围在sum中的下标
     *
     * @param l      左边界
     * @param r      右边界
     * @param curIdx 当前节点的左边
     */
    public void build(int l, int r, int curIdx) {
        if (l == r) {
            sum[curIdx] = arr[l];
            return;
        }
        int mid = (l + r) >> 1;
        build(l, mid, curIdx << 1);
        build(mid + 1, r, curIdx << 1 | 1);
        pushUp(curIdx);
    }

    /**
     * ignore
     *
     * @param taskLeft  任务左边范围
     * @param taskRight 任务右边范围
     * @param num       需要执行(add/update)的数
     * @param l         当前节点的左边范围
     * @param r         当前节点的右边范围
     * @param curIdx    当前节点的下标
     */
    public void update(int taskLeft, int taskRight, int num, int l, int r, int curIdx) {
        // 任务的范围包含改节点
        if (taskLeft <= l && r <= taskRight) {
            update[curIdx] = true;
            change[curIdx] = num;
            sum[curIdx] = num * (r - l + 1);
            lazy[curIdx] = 0;
            return;
        }
        // 当前任务躲不掉，无法懒更新，要往下发
        int mid = (l + r) >> 1;
        pushDown(curIdx, mid - l + 1, r - mid);
        if (taskLeft <= mid) {
            update(taskLeft, taskRight, num, l, mid, curIdx << 1);
        }
        if (taskRight > mid) {
            update(taskLeft, taskRight, num, mid + 1, r, curIdx << 1 | 1);
        }
        pushUp(curIdx);
    }

    /**
     * taskLeft..taskRight -> 任务范围 ,所有的值累加上num
     * l,r -> 表达的范围
     * curIdx  去哪找l，r范围上的信息
     *
     * @param taskLeft  任务左边范围
     * @param taskRight 任务右边范围
     * @param num       需要执行(add/update)的数
     * @param l         当前节点的左边范围
     * @param r         当前节点的右边范围
     * @param curIdx    当前节点的下标
     */
    public void add(int taskLeft, int taskRight, int num, int l, int r, int curIdx) {
        // 任务的范围彻底覆盖了，当前表达的范围
        if (taskLeft <= l && r <= taskRight) {
            sum[curIdx] += num * (r - l + 1);
            lazy[curIdx] += num;
            return;
        }
        // 任务并没有把l...r全包住
        // 要把当前任务往下发
        // 任务  taskLeft, taskRight  没有把本身表达范围 l,r 彻底包住
        // l..mid  (curIdx << 1)   mid+1...r(curIdx << 1 | 1)
        int mid = (l + r) >> 1;
        // 下发之前所有攒的懒任务
        pushDown(curIdx, mid - l + 1, r - mid);
        // 左孩子是否需要接到任务
        if (taskLeft <= mid) {
            add(taskLeft, taskRight, num, l, mid, curIdx << 1);
        }
        // 右孩子是否需要接到任务
        if (taskRight > mid) {
            add(taskLeft, taskRight, num, mid + 1, r, curIdx << 1 | 1);
        }
        // 左右孩子做完任务后，我更新我的sum信息
        pushUp(curIdx);
    }

    /**
     * 1~6 累加和是多少？ 1~8   rt
     *
     * @param taskLeft  任务左边范围
     * @param taskRight 任务右边范围
     * @param l         当前节点的左边范围
     * @param r         当前节点的右边范围
     * @param curIdx    当前节点的下标
     * @return /
     */
    public long query(int taskLeft, int taskRight, int l, int r, int curIdx) {
        if (taskLeft <= l && r <= taskRight) {
            return sum[curIdx];
        }
        int mid = (l + r) >> 1;
        pushDown(curIdx, mid - l + 1, r - mid);
        long ans = 0;
        if (taskLeft <= mid) {
            ans += query(taskLeft, taskRight, l, mid, curIdx << 1);
        }
        if (taskRight > mid) {
            ans += query(taskLeft, taskRight, mid + 1, r, curIdx << 1 | 1);
        }
        return ans;
    }


    /**
     * 暴力
     *
     * @author Force-oneself
     * @date 2022-06-11
     */
    public static class Right {

        public int[] arr;

        public Right(int[] origin) {
            arr = new int[origin.length + 1];
            System.arraycopy(origin, 0, arr, 1, origin.length);
        }

        public void update(int l, int r, int num) {
            for (int i = l; i <= r; i++) {
                arr[i] = num;
            }
        }

        public void add(int l, int r, int num) {
            for (int i = l; i <= r; i++) {
                arr[i] += num;
            }
        }

        public long query(int l, int r) {
            long ans = 0;
            for (int i = l; i <= r; i++) {
                ans += arr[i];
            }
            return ans;
        }

    }

    public static int[] generateRandomArray(int len, int max) {
        int size = (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    public static boolean test() {
        int len = 100;
        int max = 1000;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = generateRandomArray(len, max);
            SegmentTree seg = new SegmentTree(origin);
            int S = 1;
            int N = origin.length;
            int root = 1;
            seg.build(S, N, root);
            Right rig = new Right(origin);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                int C = (int) (Math.random() * max) - (int) (Math.random() * max);
                if (Math.random() < 0.5) {
                    seg.add(L, R, C, S, N, root);
                    rig.add(L, R, C);
                } else {
                    seg.update(L, R, C, S, N, root);
                    rig.update(L, R, C);
                }
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                long ans1 = seg.query(L, R, S, N, root);
                long ans2 = rig.query(L, R);
                if (ans1 != ans2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] origin = {2, 1, 1, 2, 3, 4, 5};
        SegmentTree seg = new SegmentTree(origin);
        // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
        int S = 1;
        // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
        int N = origin.length;
        // 整棵树的头节点位置，规定是1，不是0 -> 固定
        int root = 1;
        // 操作区间的开始位置 -> 可变
        int L = 2;
        // 操作区间的结束位置 -> 可变
        int R = 5;
        // 要加的数字或者要更新的数字 -> 可变
        int C = 4;
        // 区间生成，必须在[S,N]整个范围上build
        seg.build(S, N, root);
        // 区间修改，可以改变L、R和C的值，其他值不可改变
        seg.add(L, R, C, S, N, root);
        // 区间更新，可以改变L、R和C的值，其他值不可改变
        seg.update(L, R, C, S, N, root);
        // 区间查询，可以改变L和R的值，其他值不可改变
        long sum = seg.query(L, R, S, N, root);
        System.out.println(sum);

        System.out.println("对数器测试开始...");
        System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

    }
}
