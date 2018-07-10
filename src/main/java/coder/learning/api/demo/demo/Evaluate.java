package coder.learning.api.demo.demo;

import sun.misc.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Administrator on 2018/7/10.
 */
public class Evaluate {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        Queue<String> queue = new Queue<>();
        while(scanner.hasNext()) {
            String in = scanner.next();
            if ("(".equals(in)) {

            } else if ("+".equals(in)) {
                ops.push(in);
            } else if ("-".equals(in)) {
                ops.push(in);
            } else if ("*".equals(in)) {
                ops.push(in);
            } else if ("/".equals(in)) {
                ops.push(in);
            } else if (")".equals(in)) {
                String op = ops.pop();
                double val = vals.pop();
                if ("+".equals(op)) {
                    vals.push(vals.pop() + val);
                } else if ("-".equals(op)) {
                    vals.push(vals.pop() - val);
                } else if ("*".equals(op)) {
                    vals.push(vals.pop() * val);
                } else if ("/".equals(op)) {
                    vals.push(vals.pop() / val);
                }
            } else if ("=".equals(in)) {
                break;
            } else {
                vals.push(Double.parseDouble(in));
            }
            queue.enqueue(in);
        }
        try {
            while (!queue.isEmpty()) {
                System.out.print(queue.dequeue());
            }
            System.out.print("=" + vals.pop());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
