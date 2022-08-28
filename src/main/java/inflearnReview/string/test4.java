package inflearnReview.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class test4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<String> stringArrayList = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            stringArrayList.add(br.readLine());
        }

        Solution solution = new Solution();

        for(int i = 0; i < n; i++) {
            System.out.println(solution.getRevStrList(stringArrayList).get(i));
        }
    }

    public static class Solution {
        public ArrayList<String> getRevStrList(ArrayList<String> arrayList) {

            ArrayList<String> stringArrayList = new ArrayList<>();

            for(int i = 0; i < arrayList.size(); i++) {
                stringArrayList.add(new StringBuilder(arrayList.get(i)).reverse().toString());
            }

            return stringArrayList;
        }
    }
}
