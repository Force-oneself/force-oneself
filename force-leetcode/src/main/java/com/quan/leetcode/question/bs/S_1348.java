package com.quan.leetcode.question.bs;

import java.util.*;

/**
 * S_1348
 *
 * @author Force-oneself
 * @date 2022-04-24
 */
public class S_1348 {


    // key 用户->推文时间->该时间推文发布数目
    private Map<String, TreeMap<Integer, Integer>> userTweetMap = new HashMap<>();

    public S_1348() {
    }

    // 发布推文
    public void recordTweet(String tweetName, int time) {
        // 当前用户推文集合
        TreeMap<Integer, Integer> tweetMap = userTweetMap.computeIfAbsent(tweetName, k -> new TreeMap<>());
        // 推文时间记录，比之前次数多1
        tweetMap.put(time, tweetMap.getOrDefault(time, 0) + 1);// 推文加入
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> res = new ArrayList<>();
        if (!userTweetMap.containsKey(tweetName)) {
            res.add(0);
            return res;
        }
        int freqTime = 1;
        if ("minute".equals(freq)) {
            freqTime = 60;
        } else if ("hour".equals(freq)) {
            freqTime = 3600;
        } else if ("day".equals(freq)) {
            freqTime = 86400;
        }
        // 用户的全部推文时间集合
        TreeMap<Integer, Integer> tweetMap = userTweetMap.get(tweetName);
        int start = startTime;
        int end = Math.min(start + freqTime, endTime + 1);
        while (start <= endTime) {
            int count = 0;
            // 找到发文时间大于等于start的推文
            Map.Entry<Integer, Integer> entry = tweetMap.ceilingEntry(start);
            while (entry != null && entry.getKey() < end) {
                // 推文数累加
                count += entry.getValue();
                // 找比当前大的推文时间
                entry = tweetMap.higherEntry(entry.getKey());
            }
            res.add(count);
            // 时间后移
            start = end;
            end = Math.min(end + freqTime, endTime + 1);
        }
        return res;
    }
}
