package com.quan.leetcode.question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Force-oneself
 * @description S_406
 * @date 2022-04-08
 */
public class S_406 {

    public int[][] reconstructQueue(int[][] people) {
        // 按身高降序，k值生序，因为身高相同，k越小意味着比自己高的越少
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            }
        });
        List<int[]> ans = new ArrayList<>();
        // 由身高降序插入，后面的元素插入不会影响前面的元素，即后面的只要按着k的值插入相应的坐标即可
        for (int[] person : people) {
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }

}
