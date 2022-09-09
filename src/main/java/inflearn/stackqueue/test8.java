package inflearn.stackqueue;

import java.util.*;

public class test8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(new Solution().getAnswer(n, m, arr));
    }

    static class Solution {
        public int getAnswer(int n, int m, int[] arr) {
            Queue<Person> queue = new LinkedList<>();
            for(int i = 0; i < n; i++) {
                Person person = new Person(i, arr[i]);
                queue.offer(person);
            }
            int cnt = 1;
            while (!queue.isEmpty()) {
                Person person = queue.poll();
                for(Person p : queue) {
                    if(p.getRank() > person.getRank()) {
                        queue.offer(person);
                        person = null;
                        break;
                    }
                }
                if(person != null) {
                    if(person.getId() == m) {
                        return cnt;
                    }
                    cnt++;
                }
            }
            return 0;
        }
    }

    static class Person {
        private int id;
        private int rank;

        public Person(int id, int rank) {
            this.id = id;
            this.rank = rank;
        }

        public int getId() {
            return id;
        }

        public int getRank() {
            return rank;
        }
    }
}
