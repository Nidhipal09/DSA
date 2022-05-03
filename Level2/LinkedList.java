public class LinkedList {

    public static ListNode midNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static ListNode revList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;
        ListNode forward = null;

        while (curr != null) {
            forward = curr.next;

            curr.next = prev;
            prev = curr;
            curr = forward;
        }

        return prev;
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode mid = midNode(head);
        ListNode newHead = mid.next;
        mid.next = null;

        newHead = revList(newHead);

        ListNode i = head;
        ListNode j = newHead;

        boolean res = true;
        while (j != null) {
            if (i.val != j.val) {
                res = false;
                break;
            }

            i = i.next;
            j = j.next;
        }

        newHead = revList(newHead);
        mid.next = newHead;

        return res;
    }

    public static void fold(ListNode head) {

        ListNode mid = midNode(head);
        ListNode newHead = mid.next;
        mid.next = null;

        newHead = revList(newHead);

        ListNode i = head;
        ListNode j = newHead;
        ListNode inext = null;
        ListNode jnext = null;

        while (j != null) {
            inext = i.next;
            jnext = j.next;

            i.next = j;
            j.next = inext;

            i = inext;
            j = jnext;
        }

    }

    public static void unfold(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode fp = head;
        ListNode sp = head.next;
        ListNode sh = head.next;

        ListNode forward = null;

        while (sp != null && sp.next != null) {
            forward = sp.next;

            fp.next = forward;
            sp.next = forward.next;

            fp = forward;
            sp = forward.next;
        }

        fp.next = null;

        sh = revList(sh);
        fp.next = sh;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;

        ListNode c1 = l1;
        ListNode c2 = l2;

        while (c1 != null && c2 != null) {
            if (c1.val <= c2.val) {
                prev.next = c1;
                c1 = c1.next;
            } else if (c2.val < c1.val) {
                prev.next = c2;
                c2 = c2.next;
            }
            prev = prev.next;
        }

        if (c1 == null) {
            prev.next = c2;
        } else if (c2 == null) {
            prev.next = c1;
        }

        return dummy.next;
    }

    public static ListNode mergeKListsHelper(ListNode[] lists, int si, int ei) {
        if (si > ei)
            return null;

        if (si == ei)
            return lists[si];
        int mid = (si + ei) / 2;

        ListNode l1 = mergeKListsHelper(lists, si, mid);
        ListNode l2 = mergeKListsHelper(lists, mid + 1, ei);
        return mergeTwoLists(l1, l2);
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        if (n == 0)
            return null;
        return mergeKListsHelper(lists, 0, n - 1);
    }

    public static ListNode mergeKListsUsingPQ(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> { // lambda func

            return a.val - b.val; // this-other
        });

        for (ListNode l : lists) {
            if (l != null) {
                pq.add(l);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (pq.size() > 0) {
            ListNode node = pq.remove();

            prev.next = node;
            prev = prev.next;
            node = node.next;

            if (node != null)
                pq.add(node);
        }

        return dummy.next;
    }

    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode mid = midNode(head);
        ListNode nNode = mid.next;
        mid.next = null;

        ListNode l1 = mergeSort(head);
        ListNode l2 = mergeSort(nNode);

        return mergeTwoLists(l1, l2);
    }

    public static ListNode segregateEvenOdd(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode even = new ListNode(-1);
        ListNode odd = new ListNode(-1);

        ListNode evenTail = even, oddTail = odd, curr = head;
        while (curr != null) {
            if (curr.val % 2 == 0) {
                evenTail.next = curr;
                evenTail = curr;
            } else {
                oddTail.next = curr;
                oddTail = curr;
            }
            curr = curr.next;
        }

        evenTail.next = odd.next;
        oddTail.next = null;

        return even.next;
    }

    public static ListNode copyRandomList(ListNode head) {
        HashMap<ListNode, ListNode> map = new HashMap<>();
        ListNode curr = head;

        ListNode nHead = new ListNode(-1);
        ListNode prev = nHead;

        while (curr != null) {
            ListNode node = new ListNode(curr.val);
            prev.next = node;
            prev = node;
            map.put(curr, node);
            curr = curr.next;
        }

        nHead = nHead.next;

        ListNode c1 = head;
        ListNode c2 = nHead;

        while (c1 != null) {
            c2.random = (c1.random != null) ? map.get(c1.random) : null;

            c1 = c1.next;
            c2 = c2.next;
        }

        return nHead;
    }

    public static void copyList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            ListNode forward = curr.next;

            ListNode node = new ListNode(curr.val);
            curr.next = node;
            node.next = forward;

            curr = forward;
        }
    }

    public static void copyRandomPointers(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            ListNode random = curr.random;
            curr.next.random = (random != null) ? random.next : null;

            curr = curr.next.next;
        }
    }

    public static ListNode extractDeepCopy(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;

        ListNode curr = head;

        while (curr != null) {
            prev.next = curr.next;
            curr.next = curr.next.next;

            prev = prev.next;
            curr = curr.next;
        }

        return dummy.next;
    }

    public static ListNode copyRandomList(ListNode head) {
        copyList(head);
        copyRandomPointers(head);
        return extractDeepCopy(head);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        ListNode r1 = revList(l1);
        ListNode r2 = revList(l2);
        int c = 0;

        ListNode dummy = new ListNode(-1);
        ListNode itr = dummy;

        while (r1 != null || r2 != null || c != 0) {

            int d1 = r1 != null ? r1.val : 0;
            int d2 = r2 != null ? r2.val : 0;

            int sum = d1 + d2 + c;

            int d = sum % 10;
            c = sum / 10;

            itr.next = new ListNode(d);
            itr = itr.next;

            if (r1 != null)
                r1 = r1.next;
            if (r2 != null)
                r2 = r2.next;

        }

        return revList(dummy.next);
    }

    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {

        if (l2 == null) {
            return l1;
        }
        if (l1 == null) {
            l2.val = -l2.val;
            return l2;
        }

        l1 = revList(l1);
        l2 = revList(l2);
        int b = 0; // borrow

        ListNode dummy = new ListNode(-1);
        ListNode itr = dummy;

        ListNode r1 = l1;
        ListNode r2 = l2;

        while (r1 != null) {

            int d1 = r1.val;
            int d2 = r2 != null ? r2.val : 0;

            int diff = d1 + b - d2;
            if (diff < 0) {
                diff += 10;
                b = -1;
            } else {
                b = 0;
            }

            itr.next = new ListNode(diff);
            itr = itr.next;

            r1 = r1.next;
            if (r2 != null)
                r2 = r2.next;

        }

        return revList(dummy.next);

    }

    public static ListNode multiplyWithSingleDigit(ListNode l1, int digit) {
        ListNode dummy = new ListNode(-1);
        ListNode itr = dummy;

        ListNode curr = l1;
        int c = 0;
        while (curr != null || c != 0) {
            int sum = c + (curr != null ? curr.val : 0) * digit;

            int d = sum % 10;
            c = sum / 10;

            itr.next = new ListNode(d);
            itr = itr.next;

            if (curr != null)
                curr = curr.next;
        }

        return dummy.next;
    }

    public static void addTwoLinkedList(ListNode head, ListNode ansItr) {

        ListNode c1 = head;
        ListNode c2 = ansItr;

        int c = 0;
        while (c1 != null || c != 0) {
            int sum = c + (c1 != null ? c1.val : 0) + (c2.next != null ? c2.next.val : 0);

            int d = sum % 10;
            c = sum / 10;

            if (c2.next != null) {
                c2.next.val = d;
            } else {
                c2.next = new ListNode(d);
            }
            if (c1 != null)
                c1 = c1.next;
            c2 = c2.next;

        }
    }

    public static ListNode multiplyTwoLL(ListNode l1, ListNode l2) {

        l1 = revList(l1);
        l2 = revList(l2);

        ListNode c2 = l2;
        ListNode dummy = new ListNode(-1);
        ListNode ansItr = dummy;

        while (c2 != null) {
            ListNode m = multiplyWithSingleDigit(l1, c2.val);
            c2 = c2.next;

            addTwoLinkedList(m, ansItr);
            ansItr = ansItr.next;
        }

        return revList(dummy.next);
    }

    public static ListNode removeDuplicates(ListNode head) {

        ListNode dummy = new ListNode(-(int) 1e8);
        ListNode itr = dummy;

        ListNode curr = head;

        while (curr != null) {
            if (itr.val != curr.val) {
                itr.next = curr;
                itr = itr.next;
            }
            curr = curr.next;
        }

        itr.next = null;
        return dummy.next;
    }

    private static ListNode th = null, tt = null;

    private static void addLast(ListNode node) {
        if (tt == null) {
            th = tt = node;
        } else {
            tt.next = node;
            tt = node;
        }
    }

    public static ListNode removeDuplicates(ListNode head) {

        ListNode curr = head;
        ListNode forward = null;
        while (curr != null) {
            forward = curr.next;

            curr.next = null;
            if (tt == null || curr.val != tt.val) {
                addLast(curr);
            }
            curr = forward;
        }
        return th;
    }

    public static ListNode removeAllDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode itr = dummy;
        itr.next = head; // potential unique ele
        ListNode curr = head.next;

        while (curr != null) {
            boolean flag = false;
            while (curr != null && curr.val == itr.next.val) {
                flag = true;
                curr = curr.next;
            }

            if (flag) {
                itr.next = curr;
            } else {
                itr = itr.next;
            }

            if (curr != null)
                curr = curr.next;
        }

        return dummy.next;
    }

    public void addFirst(int val) {
        Node node = new Node(val);

        if (this.size == 0) {
            this.head = this.tail = node;
        } else {
            node.next = this.head;
            this.head.prev = node;
            this.head = node;
        }
        this.size++;
    }

    public void addLast(int val) {
        Node node = new Node(val);

        if (this.size == 0) {
            this.head = this.tail = node;
        } else {
            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;
        }
        this.size++;
    }

    public int removeFirst() {
        Node node = this.head;
        if (this.size == 0) {
            System.out.print("ListIsEmpty: ");
            return -1;
        }

        else if (this.size == 1) {
            this.head = this.tail = null;
        } else {
            this.head = node.next;
            this.head.prev = null;
        }
        this.size--;
        return node.data;
    }

    public int removeLast() {
        Node node = this.tail;
        if (this.size == 0) {
            System.out.print("ListIsEmpty: ");
            return -1;
        }

        else if (this.size == 1) {
            this.head = this.tail = null;
        } else {
            this.tail = node.prev;
            this.tail.next = null;
        }
        this.size--;
        return node.data;
    }

    public int getAt(int index) {
        Node node = this.head;

        int i = 0;
        while (i < index) {
            node = node.next;
            i++;
        }
        return node.data;
    }

    public void addAt(int index, int data) {
        if (index < 0 || index > this.size) {
            System.out.println("IndexIsInValid: -1");
            return;
        }

        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == this.size) {
            addLast(data);
            return;
        }

        Node node = new Node(data);

        Node forward = getNodeAt(index);
        Node prev = forward.prev;

        prev.next = node;
        node.prev = prev;
        node.next = forward;
        forward.prev = node;

        this.size++;
    }

    public int removeAt(int index) {

        if (index < 0 || index >= this.size) {
            System.out.print("IndexIsInValid: ");
            return -1;
        }

        if (index == 0) {
            return removeFirst();
        }
        if (index == this.size - 1) {
            return removeLast();
        }

        Node node = getNodeAt(index);
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;

        this.size--;
        return node.data;
    }

    public void addBefore(Node refNode, int data) {
        Node node = new Node(data);
        this.size++;

        if (refNode.prev == null) {
            refNode.prev = node;
            node.next = refNode;
            this.head = node;
            return;
        } else {
            Node prev = refNode.prev;

            prev.next = node;
            node.prev = prev;
            node.next = refNode;
            refNode.prev = node;
            return;
        }
    }

    public void addBefore(int idx, int data) {
        Node node = getNodeAt(idx);
        addBefore(node, data);
    }

    public void addAfter(Node refNode, int data) {
        Node node = new Node(data);
        this.size++;

        if (refNode.next == null) {
            refNode.next = node;
            node.prev = refNode;
            this.tail = node;
            return;
        } else {
            Node next = refNode.next;

            refNode.next = node;
            node.prev = refNode;
            refNode.next = next;
            next.prev = refNode;
            return;
        }
    }

    public void addAfter(int idx, int data) {
        Node node = getNodeAt(idx);
        addAfter(node, data);
    }

    public int removeAfter(Node refNode) {
        if (refNode == this.tail) {
            System.out.print("LocationIsInvalid: ");
            return -1;
        }

        Node next = refNode.next;

        if (next == this.tail) {
            refNode.next = null;
            next.prev = null;
            this.tail = refNode;
        } else {
            Node nnext = refNode.next.next;

            next.prev = null;
            next.next = null;
            refNode.next = nnext;
            nnext.prev = refNode;
        }

        this.size--;
        return next.data;
    }

    public int removeAfter(int idx) {
        Node node = getNodeAt(idx);
        return removeAfter(node);
    }

    public int removeBefore(Node refNode) {
        if (refNode == this.head) {
            System.out.print("LocationIsInvalid: ");
            return -1;
        }

        Node prev = refNode.prev;
        if (prev == this.head) {
            prev.next = null;
            refNode.prev = null;
            this.head = refNode;
        } else {
            Node pprev = refNode.prev.prev;
            pprev.next = refNode;
            refNode.prev = pprev;
            prev.next = null;
            prev.next = null;
        }

        this.size--;
        return prev.data;
    }

    public int removeBefore(int idx) {
        Node node = getNodeAt(idx);
        return removeBefore(node);
    }

    public int removeNode(Node refNode) {
        Node next = refNode.next;
        Node prev = refNode.prev;

        if (this.size == 1) {
            this.head = this.tail = null;
        } else if (this.head == refNode) {
            next.prev = null;
            refNode.next = null;
            this.head = next;
        } else if (this.tail == refNode) {
            prev.next = null;
            refNode.prev = null;
            this.tail = prev;
        } else {
            prev.next = next;
            next.prev = prev;
            refNode.next = null;
            refNode.prev = null;
        }
        this.size--;
        return refNode.data;
    }

    public void displayForw() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        Node node = this.head;

        while (node != null) {
            s.append(node.data);
            if (node.next != null)
                s.append(", ");
            node = node.next;
        }
        s.append("]");
        System.out.println(s.toString());
    }

    public void displayBack() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        Node node = this.tail;

        while (node != null) {
            s.append(node.data);
            if (node.prev != null)
                s.append(", ");
            node = node.prev;
        }
        s.append("]");
        System.out.println(s.toString());
    }

    
     

}