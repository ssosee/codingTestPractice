package toss_assistant_algorithm_study.seaaon4.week1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class _StringCompression {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.compress(new char[]{'a','a','a','b','b','a','a'}));
    }

    static class Solution {
        public int compress(char[] chars) {
            if(chars.length == 1) return 1;

            char prev = chars[0];
            int count = 0;
            int index = 0;
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < chars.length; i++) {
                char current = chars[i];
                if(prev == current) count++;
                else {
                    if(count > 1) {
                        chars[index++] = prev;
                        String countStr = String.valueOf(count);
                        for(char cnt : countStr.toCharArray()) {
                            chars[index++] = cnt;
                        }
                        sb.append(prev).append(countStr);
                        count = 1;
                    } else {
                        chars[index++] = prev;
                        sb.append(prev);
                        count = 1;
                    }
                }
                prev = current;

                if(i == chars.length - 1) {
                    if(count > 1) {
                        chars[index++] = prev;
                        String countStr = String.valueOf(count);
                        for(char cnt : countStr.toCharArray()) {
                            chars[index++] = cnt;
                        }
                        sb.append(prev).append(countStr);
                    } else {
                        chars[index++] = prev;
                        sb.append(prev);
                    }
                }
            }

            return sb.toString().length();
        }
    }
}
