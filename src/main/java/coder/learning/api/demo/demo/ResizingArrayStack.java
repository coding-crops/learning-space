package coder.learning.api.demo.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2018/7/10.
 */
public class ResizingArrayStack<Item> implements Iterable<Item>{

    private Item[] a;
    private int N;
    public FixedCapacityStack(int cap) {
        // 创建泛型数组在java中是不允许的， 所以使用强制类型转换
        a = (Item[])new Object[cap];
    }
    public int size() {
        return N;
    }

    public boolean isEmpty() {
        ArrayList a = new ArrayList<>();
        List b = new ArrayList<>();

        return N == 0;
    }

    public void push(Item item) {

        if (a.length == N) {
            resize(a.length * 2);
        }
        a[N++] = item;
    }

    public Item pop() {

        Item item = a[--N];
        a[N] = null; // 避免对象游离
        if (N > 0 && N == (a.length / 4)) {
            resize(a.length / 2);
        }
        return item;
    }

    private void resize(int max) {
        Item[] temp = (Item[])new Object[max];
        for (int i = 0;i<N;i++) {
            temp[i] = a[i];
        }
        a = temp;
    }


    @Override
    public Iterator<Item> iterator() {

        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>{

        private int i = N;
        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }
    }
}
