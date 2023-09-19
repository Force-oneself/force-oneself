package com.quan.leetcode.question.string;

import java.util.HashMap;
import java.util.Map;

/**
 * S_535
 *
 * @author Force-oneself
 * @date 2022-05-28
 */
public class S_535 {

    public class Codec {
        Map<Integer, String> map = new HashMap<>();

        public String encode(String longUrl) {
            map.put(longUrl.hashCode(), longUrl);
            return "http://tinyurl.com/" + longUrl.hashCode();
        }

        public String decode(String shortUrl) {
            return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
        }
    }

}
