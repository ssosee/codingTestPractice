package backjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 스택_10828 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i++) {
            String command = br.readLine();

            if(command.contains("push")) {
                String x = command.replace("push ", "");
                stack.push(Integer.parseInt(x));
            } else if (command.equals("pop")) {
                if(!stack.isEmpty()) System.out.println(stack.pop());
                else System.out.println(-1);
            } else if (command.equals("size")) {
                System.out.println(stack.size());
            } else if (command.equals("empty")) {
                if(!stack.isEmpty()) System.out.println(0);
                else System.out.println(1);
            } else if(command.equals("top")) {
                if(!stack.isEmpty()) System.out.println(stack.peek());
                else System.out.println(-1);
            }
        }

        br.close();

    }
}
