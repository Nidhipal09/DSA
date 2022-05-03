package HashmapAndHeap;

import java.util.*;

public class PriorityQueueExplained {

    // write priority queue using heap
    public static class CustomPriorityQueue {
        ArrayList<Integer> data;

        CustomPriorityQueue(int[] arr) {
            data = new ArrayList<>();   
            for (int val : arr) {      // nlogn code
                add1(val);
            }

            for(int i=data.size()/2 - 1; i>=0; i--){
                downHeapify(i);
            }
        }

        public void add1(int val) {
            data.add(val);
            if (data.size() > 0)
                upHeapify(data.size() - 1);
        }

        public int remove1() {
            if (this.size1() == 0) {
                System.out.println("Underflow");
                return -1;
            }
            swap(0, data.size() - 1);
            int val = data.remove(data.size() - 1);
            downHeapify(0);
            return val;
        }

        public void upHeapify(int ci) {
            if (ci == 0) {
                return;
            }

            int pi = (ci - 1) / 2;

            if (data.get(ci) < data.get(pi)) {
                swap(pi, ci);
                upHeapify(pi);
            }
        }

        public void downHeapify(int pi) {

            int mi = pi;

            int lci = 2 * pi + 1;
            if (lci < data.size() && data.get(lci) < data.get(mi))
                mi = lci;

            int rci = 2 * pi + 2;
            if (rci < data.size() && data.get(rci) < data.get(mi))
                mi = rci;

            if (mi != pi) {
                swap(pi, mi);
                downHeapify(mi);
            }
        }

        public void swap(int i, int j) {
            int ith = data.get(i);
            int jth = data.get(j);
            data.set(i, jth);
            data.set(j, ith);
        }

        public int peek1() {
            if (data.size() == 0) {
                System.out.println("Underflow");
                return -1;
            }
            return data.get(0);
        }

        public int size1() {
            return data.size();
        }
    }

    public static void main(String[] args) throws Exception {
        int[] arr = { 3, 67, 23, 1, 2, 43, 87, 32, 17 };

        CustomPriorityQueue cpq = new CustomPriorityQueue(arr);
        while (cpq.size1() > 0) {
            System.out.println(cpq.peek1());
            cpq.remove1();
        }
    }
}
