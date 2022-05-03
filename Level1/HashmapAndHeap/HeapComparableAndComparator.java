import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.*;

public class HeapComparableAndComparator {
    static class Student implements Comparable<Student>{
        int rollno;
        int ht;
        int wt;

        Student(int rollno, int ht, int wt){
            this.rollno = rollno;
            this.ht = ht;
            this.wt = wt;
        }

        public int compareTo(Student o){
            // return this.rollno - o.rollno;   // increasing order  
            return o.rollno - this.rollno;      // decreasing order  
            
            // if this > o  returns +ve
            // if this < o  returns -ve
            // if this == o  returns 0

            //how will it check?

            // Comparable parent = (Comparable) data.get(pi)
            // Comparable child = (Comparable) data.get(ci)
            // if(child.compareTo(parent) < 0){  // if child is smaller upheapify(child)
            //   upheapify(child);  
            // } 
        }

        public String toString(){
            return this.rollno+ " " + this.ht + " " + this.wt;
        }
    }

    static class StudentHtComparator implements Comparator<Student>{
        public int compare(Student s1, Student s2) {
            return s1.ht - s2.ht;
        }
    }

    static class StudentWtComparator implements Comparator<Student>{
        public int compare(Student s1, Student s2){
            return s1.wt - s2.wt;
        }
    }

    public static void main(String[] args) {
        
        CustomPriorityQueue<Student> pq = new CustomPriorityQueue<>(new StudentHtComparator());

        // PriorityQueue<Student> pq = new PriorityQueue<>(new StudentWtComparator());

        pq.add(new Student(1,4,1000));
        pq.add(new Student(2,5,600));
        pq.add(new Student(3,6,150));

        while(pq.size()>0){
            System.out.println(pq.remove().toString());  //will throw an exception becoz during pq add fun upheapify is called which compares data value now data is of Student type how will comparison occur?
        }
    
    }

    


    //now change the custom made priority queue 
    public static class CustomPriorityQueue<T> {  // generic
        ArrayList<T> data;
        Comparator comparator;

        public CustomPriorityQueue() {
            data = new ArrayList<>();
            comparator = null;
        }

        public CustomPriorityQueue(Comparator comp){
            data = new ArrayList<>();
            this.comparator = comp;
        }

        private boolean isSmaller(int i, int j){
            if(comparator == null){
                Comparable ith = (Comparable)(data.get(i));
                Comparable jth = (Comparable)(data.get(j));
    
                if(ith.compareTo(jth) < 0) return true;
                return false;
            }

            else{
                T ith = data.get(i);
                T jth = data.get(j);
    
                if(comparator.compare(ith, jth) < 0) return true;
                return false;
            }
            
        }

        public void add(T val) {
            data.add(val);
            if (data.size() > 0)
                upHeapify(data.size() - 1);
        }

        public T remove() {
            if (this.size() == 0) {
                System.out.println("Underflow");
                return null;
            }
            swap(0, data.size() - 1);
            T val = data.remove(data.size() - 1);
            downHeapify(0);
            return val;
        }

        public void upHeapify(int ci) {
            if (ci == 0) {
                return;
            }

            int pi = (ci - 1) / 2;

            if (isSmaller(ci, pi)) {
                swap(pi, ci);
                upHeapify(pi);
            }
        }

        public void downHeapify(int pi) {

            int mi = pi;

            int lci = 2 * pi + 1;
            if (lci < data.size() && isSmaller(lci, mi))
                mi = lci;

            int rci = 2 * pi + 2;
            if (rci < data.size() && isSmaller(rci, mi))
                mi = rci;

            if (mi != pi) {
                swap(pi, mi);
                downHeapify(mi);
            }
        }

        public void swap(int i, int j) {
            T ith = data.get(i);
            T jth = data.get(j);
            data.set(i, jth);
            data.set(j, ith);
        }

        public T peek() {
            if (data.size() == 0) {
                System.out.println("Underflow");
                return null;
            }
            return data.get(0);
        }

        public int size() {
            return data.size();
        }
    }

}
