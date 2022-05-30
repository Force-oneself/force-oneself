package com.quan.leetcode.question.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * S_609
 *
 * @author Force-oneself
 * @date 2022-05-30
 */
public class S_609 {

    public List<List<String>> findDuplicate(String[] paths) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] values = path.split(" ");
            // 取文件
            for (int i = 1; i < values.length; i++) {
                // 文件内容
                String[] nameCont = values[i].split("\\(");
                nameCont[1] = nameCont[1].replace(")", "");
                List<String> list = map.getOrDefault(nameCont[1], new ArrayList<>());
                list.add(values[0] + "/" + nameCont[0]);
                map.put(nameCont[1], list);
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key).size() > 1)
                res.add(map.get(key));
        }
        return res;
    }
}
