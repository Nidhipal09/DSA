package LinkedLists;

public class LinkedList {
  Node head;
  Node tail;
  int size;

  public static class Node {
    int data;
    Node next;
  }

  void addLast(int val) {
    Node node = new Node();
    node.data = val;
    node.next = null;

    if (size == 0) {
      head = tail = node;
    } else {
      tail.next = node;
      tail = node;
    }
    size++;
  }

  public void display() {
    Node temp = head;

    while (temp != null) {
      System.out.print(temp.data + " ");
      temp = temp.next;
    }
    System.out.println();
  }

  public void removeFirst() {
    if (size == 0) {
      System.out.println("List is empty");
    } else if (size == 1) {
      head = tail = null;
      size = 0;
    } else {
      head = head.next;
      size--;
    }
  }

  public int getFirst() {
    if (size == 0) {
      System.out.println("List is empty");
      return -1;
    } else {
      return head.data;
    }
  }

  public int getLast() {
    if (size == 0) {
      System.out.println("List is empty");
      return -1;
    } else {
      return tail.data;
    }
  }

  public int getAt(int idx) {
    if (size == 0) {
      System.out.println("List is empty");
      return -1;
    } else if (idx < 0 || idx >= size) {
      System.out.println("Invalid arguments");
      return -1;
    }

    Node temp = head;
    for (int i = 1; i <= idx; i++) {
      temp = temp.next;
    }
    return temp.data;
  }

  public void addFirst(int val) {
    Node temp = new Node();
    temp.data = val;

    if (size == 0) {
      head = tail = temp;
    } else {
      temp.next = head;
      head = temp;
    }

    size++;
  }

  public void addAt(int idx, int val) {
    if (idx < 0 || idx > size) {
      System.out.println("Invalid arguments");
      return;
    }
    Node node = new Node();
    node.data = val;

    if (idx == 0) {
      node.next = head;
      head = node;
    } else if (idx == size) {
      tail.next = node;
      tail = node;
    } else {
      Node temp = head;
      for (int i = 1; i < idx; i++) {
        temp = temp.next;
      }
      Node nnode = temp.next; // next node or idx node
      temp.next = node;
      node.next = nnode;
    }
    size++;
  }

  public void removeLast() {
    if (size == 0) {
      System.out.println("List is empty");
    } else if (size == 1) {
      head = tail = null;
      size = 0;
    } else {
      Node temp = head;
      while (temp.next != tail) {
        temp = temp.next;
      }
      temp.next = null;
      tail = temp;
      size--;
    }
  }

  public void removeAt(int idx) {
    if (idx < 0 || idx >= size) {
      System.out.println("Invalid arguments");
    } else if (idx == 0) {
      removeFirst();
    } else if (idx == size - 1) {
      removeLast();
    } else {

      Node temp = head;
      for (int i = 1; i < idx; i++) {
        temp = temp.next;
      }
      Node nnode = temp.next.next;
      temp.next = nnode;
      size--;
    }
  }

  private Node getNodeAt(int idx) {
    Node temp = head;
    for (int i = 1; i <= idx; i++) {
      temp = temp.next;
    }
    return temp;
  }

  public void reverseDI() {
    int lo = 0, hi = size - 1;

    while (lo < hi) {
      Node left = getNodeAt(lo);
      Node right = getNodeAt(hi);

      int temp = left.data;
      left.data = right.data;
      right.data = temp;

      lo++;
      hi--;
    }
  }

  public void reversePI() {
    Node prev = null;
    Node curr = head;

    while (curr != null) {
      Node cnext = curr.next;
      curr.next = prev;

      prev = curr;
      curr = cnext;
    }

    Node temp = head;
    head = tail;
    tail = temp;
  }

  public static class LLToStackAdapter {
    LinkedList<Integer> list;

    public LLToStackAdapter() {
      list = new LinkedList<>();
    }

    int size() {
      return list.size();
    }

    void push(int val) {
      list.addFirst(val);
    }

    int pop() {
      if (list.size() == 0) {
        System.out.println("Stack underflow");
        return -1;
      }
      return list.removeFirst();
    }

    int top() {
      if (list.size() == 0) {
        System.out.println("Stack underflow");
        return -1;
      }
      return list.getFirst();
    }
  }

  public static class LLToQueueAdapter {
    LinkedList<Integer> list;

    public LLToQueueAdapter() {
      list = new LinkedList<>();
    }

    int size() {
      return list.size();
    }

    void add(int val) {
      list.addLast(val);
    }

    int remove() {
      if (list.size() == 0) {
        System.out.println("Queue underflow");
        return -1;
      }
      return list.removeFirst();
    }

    int peek() {
      if (list.size() == 0) {
        System.out.println("Queue underflow");
        return -1;
      }
      return list.getFirst();
    }
  }

  public int kthFromLast(int k) {
    Node slow = head;
    Node fast = head;

    for (int i = 1; i <= k; i++) {
      fast = fast.next;
    }

    while (fast != tail) {
      slow = slow.next;
      fast = fast.next;
    }

    return slow.data;
  }

  public int mid() {
    Node slow = head;
    Node fast = head;

    while (fast != tail && fast.next != tail) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow.data;
  }

  public static LinkedList mergeTwoSortedLists(LinkedList l1, LinkedList l2) {

    LinkedList ans = new LinkedList();
    Node n1 = l1.head;
    Node n2 = l2.head;

    while (n1 != null && n2 != null) {
      if (n1.data <= n2.data) {
        ans.addLast(n1.data);
        n1 = n1.next;
      } else {
        ans.addLast(n2.data);
        n2 = n2.next;
      }
    }

    while (n1 != null) {
      ans.addLast(n1.data);
      n1 = n1.next;
    }

    while (n2 != null) {
      ans.addLast(n2.data);
      n2 = n2.next;
    }

    return ans;
  }

  private static Node midNode(Node low, Node high) {
    Node f = low;
    Node s = low;

    while (f != high && f.next != high) {
      f = f.next.next;
      s = s.next;
    }

    return s;
  }

  public static LinkedList mergeSort(Node head, Node tail) {
    if (head == tail) {
      LinkedList l = new LinkedList();
      l.addLast(head.data);
      return l;
    }

    Node midNode = midNode(head, tail);

    LinkedList l1 = mergeSort(head, midNode);
    LinkedList l2 = mergeSort(midNode.next, tail);

    return mergeTwoSortedLists(l1, l2);
  }

  public void removeDuplicates() {
    LinkedList ans = new LinkedList();

    while (this.size() > 0) {
      int data = this.getFirst();
      this.removeFirst();

      if (ans.tail == null || ans.tail.data != data) {
        ans.addLast(data);
      }
    }

    // not this=ans becoz this is read only, we can change only its properties.
    this.head = ans.head;
    this.tail = ans.tail;
    this.size = ans.size;
  }

  public void removeDuplicates1() {
    Node prev = this.head;
    Node curr = this.head.next;

    while (curr != null) {
      if (curr.data == prev.data) {
        while (curr != null && curr.data == prev.data) {
          curr = curr.next;
          this.size--;
        }
        prev.next = curr;
      } else {
        prev = curr;
        curr = curr.next;
      }
    }

    this.tail = prev;
  }

  public void oddEven() {
    LinkedList odd = new LinkedList();
    LinkedList even = new LinkedList();

    while (this.size() > 0) {
      int data = this.getFirst();
      this.removeFirst();

      if (data % 2 == 0) {
        even.addLast(data);
      } else {
        odd.addLast(data);
      }
    }

    if (odd.size == 0) {
      this.head = even.head;
      this.tail = even.tail;
      this.size = even.size;
      return;
    }
    if (even.size == 0) {
      this.head = odd.head;
      this.tail = odd.tail;
      this.size = odd.size;
      return;
    }

    odd.tail.next = even.head;
    this.head = odd.head;
    this.tail = even.tail;
    this.size = odd.size + even.size;
  }

  public void kReverse(int k) {

    LinkedList prev = new LinkedList();
    LinkedList curr = new LinkedList();

    while (this.size() >= k) {
      int i = 1;

      while (i <= k) {
        int data = this.getFirst();
        this.removeFirst();
        curr.addFirst(data);
        i++;
      }
      if (prev.size() > 0) {
        prev.tail.next = curr.head;
        prev.tail = curr.tail;
        prev.size += k;
      } else {
        prev = curr;
      }

      curr = new LinkedList();
    }

    while (this.size() > 0) {
      int data = this.getFirst();
      this.removeFirst();

      prev.addLast(data);
    }

    this.size = prev.size;
    this.head = prev.head;
    this.tail = prev.tail;
  }

  private void displayReverseHelper(Node node) {
    if (node == null)
      return;

    displayReverseHelper(node.next);
    System.out.print(node.data + " ");
  }

  public void displayReverse() {
    displayReverseHelper(head);
    System.out.println();
  }

  private void reversePRHelper(Node node) {
    if (node == null)
      return;

    reversePRHelper(node.next);

    if (node != tail) {
      node.next.next = node;
    }
  }

  public void reversePR() {
    reversePRHelper(head);

    head.next = null;
    Node temp = head;
    head = tail;
    tail = temp;
  }

  private boolean IsPalindromeHelper(Node last) {
    if (last == null)
      return true;

    boolean rres = IsPalindromeHelper(last.next); // recursion result

    if (rres == false) {
      return false;
    } else if (first.data != last.data) {
      return false;
    } else {
      first = first.next;
      return true;
    }

  }

  Node first; // 1

  public boolean IsPalindrome() {
    first = head; // why initialize here? Try initializing in 1 line- null pointer exception
    return IsPalindromeHelper(head);
  }

  private void foldHelper(Node node, int n) {
    if (node == null)
      return;

    foldHelper(node.next, n + 1);

    if (n > size / 2) {
      Node lnext = left.next;
      left.next = node;
      node.next = lnext;

      left = lnext;
      n++;
    } else if (n == size / 2) {
      tail = node;
      tail.next = null;
    }

  }

  Node left;

  public void fold() {
    left = head;
    foldHelper(head, 0);
  }

  private static int addTwoListsHelper(Node onode, int pv1, Node tnode, int pv2, LinkedList ans) { // returns carry
    if (pv1 == 0 && pv2 == 0) {
      return 0;
    }

    int data = 0, carry = 0;
    if (pv1 < pv2) {
      carry = addTwoListsHelper(onode, pv1, tnode.next, pv2 - 1, ans);
      data = tnode.data + carry;
    } else if (pv2 < pv1) {
      carry = addTwoListsHelper(onode.next, pv1 - 1, tnode, pv2, ans);
      data = onode.data + carry;
    } else {
      carry = addTwoListsHelper(onode.next, pv1 - 1, tnode.next, pv2 - 1, ans);
      data = onode.data + tnode.data + carry;
    }

    carry = data / 10;
    data %= 10;

    ans.addFirst(data);

    return carry;
  }

  public static LinkedList addTwoLists(LinkedList one, LinkedList two) {
    LinkedList ans = new LinkedList();
    int carry = addTwoListsHelper(one.head, one.size, two.head, two.size, ans);

    if (carry != 0)
      ans.addFirst(carry);

    return ans;
  }

  public static int findIntersection(LinkedList one, LinkedList two){
       
    int os = one.size;
    int ts = two.size;
    
    Node onode = one.head;
    Node tnode = two.head;

    if(os>ts){
        int gap = os-ts;
        int i=1;
        while(i<=gap){
           onode = onode.next; 
           i++;
        }
    }
    else if(ts>os){
        int gap = ts-os;
        int i=1;
        while(i<=gap){
          tnode = tnode.next; 
          i++;
        }    
    }

    while(onode.data!=tnode.data){
        onode = onode.next; 
        tnode = tnode.next;    
    }

    return onode.data;
 }

}
