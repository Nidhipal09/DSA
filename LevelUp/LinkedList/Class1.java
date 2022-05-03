import java.util.*;

public class Class1 {
    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode random;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // first mid - in even length
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // second mid - in even length
    public ListNode middleNode1(ListNode head) { // ts- On
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public int len(ListNode head) {
        ListNode curr = head;
        int len = 0;

        while (curr != null) {
            len++;
            curr = curr.next;
        }

        return len;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode forward = curr.next;
            curr.next = prev;

            prev = curr;
            curr = forward;
        }

        return prev;
    }

    public boolean isPalindrome(ListNode head) {
        ListNode midNode = middleNode1(head); // second mid
        ListNode lastNode = reverseList(midNode);

        ListNode left = head;
        ListNode right = lastNode;

        boolean flag = true;
        while (right != null) {
            if (left.val != right.val) {
                flag = false;
                break;
            }

            left = left.next;
            right = right.next;
        }

        reverseList(lastNode); // make the list as before

        return flag;
    }

    public void reorderList(ListNode head) {
        ListNode midNode = middleNode1(head);
        ListNode nHead = midNode.next;
        midNode.next = null;
        nHead = reverseList(nHead);

        ListNode left = head;
        ListNode right = nHead;

        while (right != null) {
            ListNode lnext = left.next;
            ListNode rnext = right.next;

            left.next = right;
            right.next = lnext;

            left = lnext;
            right = rnext;
        }

        // or
        // ListNode midNode = middleNode1(head);
        // ListNode nHead = reverseList(midNode);
        // midNode.next = null;

        // ListNode left = head;
        // ListNode right = nHead;

        // while(right.next!=null){
        // ListNode lnext = left.next;
        // ListNode rnext = right.next;

        // left.next = right;
        // right.next = lnext;

        // left = lnext;
        // right = rnext;
        // }
    }

    public ListNode mergeTwoSortedLists(ListNode n1, ListNode n2) {
        ListNode dummy = new ListNode(-1, null), curr = dummy, c1 = n1, c2 = n2;

        while (c1 != null && c2 != null) {
            if (c1.val <= c2.val) {
                curr.next = c1;
                c1 = c1.next;
            } else {
                curr.next = c2;
                c2 = c2.next;
            }

            curr = curr.next;
        }

        curr.next = (n1 == null ? n2 : n1);

        return dummy.next;
    }

    public void unfold(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;

        ListNode curr = head;
        ListNode currPrev = null;

        while (curr != null) {
            ListNode forward = (curr.next == null ? null : curr.next.next);
            if (forward == null) {
                currPrev = curr;
            }

            prev.next = curr.next;
            curr.next = forward;

            prev = prev.next;
            curr = curr.next;
        }

        ListNode tail = dummy.next;
        dummy.next = null;

        ListNode midNode = reverseList(tail);
        currPrev.next = midNode;
    }

    public void unfold1(ListNode head) {
        ListNode h1 = head, h2 = head.next, c1 = h1, c2 = h2;

        while (c2 != null && c2.next != null) {
            ListNode forward = c2.next;

            c1.next = forward;
            c2.next = forward.next;

            c1 = c1.next;
            c2 = c2.next;
        }

        c1.next = reverseList(h2);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n == 0)
            return head;
        if (head == null)
            return null;

        int k = n;
        ListNode a = head, b = head;

        while (k-- > 0) {
            b = b.next;
        }

        if (b == null) { // if size = 3 n = 3 ec-edge case
            ListNode headn = head.next;
            head.next = null;
            return headn;
        }

        while (b.next != null) {
            a = a.next;
            b = b.next;
        }

        ListNode forward = a.next.next;
        a.next.next = null;
        a.next = forward;

        return head;
    }

    // sir can we solve faang2.0 list in parallel with this course?

    public void removeNthFromEnd_noReturn(ListNode head, int n) {
        if (n == 0)
            return;
        if (head == null)
            return;

        int k = n;
        ListNode a = head, b = head;

        while (k-- > 0) {
            b = b.next;
        }

        if (b == null) { // if size = 3 n = 3 ec-edge case
            a.val = a.next.val;
            ListNode forward = a.next.next;
            a.next.next = null;
            a.next = forward;
            return;
        }

        while (b.next != null) {
            a = a.next;
            b = b.next;
        }

        ListNode forward = a.next.next;
        a.next.next = null;
        a.next = forward;
    }

    // 2
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int c = 0;
        ListNode dummy = new ListNode(-1), prev = dummy, c1 = l1, c2 = l2;
        while (c1 != null || c2 != null || c != 0) {
            int sum = (c1 == null ? 0 : c1.val) + (c2 == null ? 0 : c2.val) + c;
            c = sum / 10;
            prev.next = new ListNode(sum % 10);

            prev = prev.next;
            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }

        ListNode anshead = dummy.next;
        dummy.next = null;

        return anshead;
    }

    // 445
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);

        int c = 0;
        ListNode dummy = new ListNode(-1), curr = dummy, c1 = l1, c2 = l2;
        while (c1 != null || c2 != null || c != 0) {
            int sum = (c1 == null ? 0 : c1.val) + (c2 == null ? 0 : c2.val) + c;
            c = sum / 10;
            curr.next = new ListNode(sum % 10);

            curr = curr.next;
            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }

        l1 = reverseList(l1);
        l2 = reverseList(l2);

        ListNode anshead = dummy.next;
        dummy.next = null;

        anshead = reverseList(anshead);

        return anshead;
    }

    public int isBiggerList(ListNode l1, ListNode l2) {
        int len1 = len(l1), len2 = len(l2);

        if (len1 == len2) {
            ListNode c1 = l1, c2 = l2;
            while (c1 != null) {
                if (c1.val != c2.val) {
                    return c1.val - c2.val;
                }
                c1 = c1.next;
                c2 = c2.next;

            }
        }

        return len1 - len2;

    }

    public ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        if (isBiggerList(l1, l2) < 0) {
            ListNode temp = l1;
            l1 = l2;
            l2 = temp;
        } else if (isBiggerList(l1, l2) == 0) {
            return new ListNode(0);
        }

        l1 = reverseList(l1);
        l2 = reverseList(l2);

        int b = 0; // borrow
        ListNode dummy = new ListNode(-1), curr = dummy, c1 = l1, c2 = l2;
        while (c1 != null || c2 != null) {
            int val1 = (c1 == null ? 0 : c1.val) + b;
            int val2 = (c2 == null ? 0 : c2.val);

            int val = 0;
            if (val1 < val2) {
                val = val1 + 10 - val2;
                b = -1;
            } else {
                val = val1 - val2;
                b = 0;
            }
            curr.next = new ListNode(val);

            curr = curr.next;
            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }

        l1 = reverseList(l1);
        l2 = reverseList(l2);

        ListNode anshead = dummy.next;
        dummy.next = null;

        anshead = reverseList(anshead);

        while (anshead.next != null && anshead.val == 0) { // remove leading zeros
            anshead = anshead.next;
        }

        return anshead;
    }

    public ListNode multiplyWithSingleDigit(ListNode l1, int digit) {
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

    public void addTwoLinkedListForMultiply(ListNode ansItr, ListNode m) {

        ListNode c1 = ansItr;
        ListNode c2 = m;

        int c = 0;
        while (c2 != null || c != 0) {
            int sum = c + (c2 != null ? c2.val : 0) + (c1.next != null ? c1.next.val : 0);

            int d = sum % 10;
            c = sum / 10;

            if (c1.next != null) {
                c1.next.val = d;
            } else {
                c1.next = new ListNode(d);
            }
            if (c2 != null)
                c2 = c2.next;
            c1 = c1.next;

        }
    }

    public ListNode multiplyTwoLL(ListNode l1, ListNode l2) {

        l1 = reverseList(l1);
        l2 = reverseList(l2);

        ListNode c2 = l2;
        ListNode dummy = new ListNode(-1);
        ListNode ansItr = dummy;

        while (c2 != null) {
            ListNode m = multiplyWithSingleDigit(l1, c2.val);
            c2 = c2.next;

            addTwoLinkedListForMultiply(ansItr, m);
            ansItr = ansItr.next;
        }

        return reverseList(dummy.next);
    }

    public ListNode removeDuplicates(ListNode head) {
        // ListNode curr = head, prev = head;

        // while(curr!=null){
        // prev = curr;
        // while(curr.next!=null && curr.val==curr.next.val){
        // curr = curr.next;
        // }

        // ListNode forward = curr.next;
        // curr.next = null;
        // prev.next = forward;

        // curr = forward;
        // }

        // return head;

        // or
        // ListNode curr = head, prev = head;

        // while(prev!=null){
        // curr = prev.next;
        // while(curr!=null && prev.val == curr.val){
        // curr = curr.next;
        // }

        // prev.next = curr;
        // prev = curr;
        // }

        // return head;

        // or
        ListNode prev = head, curr = head.next;

        while (curr != null) {
            if (prev.val != curr.val) {
                prev.next = curr;
                prev = curr;
            }
            curr = curr.next;
        }

        prev.next = null;

        return head;
    }

    public ListNode removeAllDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1, head), prev = dummy, curr = head.next;

        while (curr != null) {
            boolean isEqual = false;

            while (curr != null && prev.next.val == curr.val) {
                isEqual = true;
                curr = curr.next;
            }

            if (!isEqual) { // directly connected 1->2 one after the other
                prev = prev.next;
            } else {
                prev.next = curr;
            }

            if (curr != null)
                curr = curr.next;
        }

        return dummy.next;
    }

    public ListNode segregateEvenOdd(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode dummyeven = new ListNode(-1);
        ListNode dummyodd = new ListNode(-1);

        ListNode curr = head, even = dummyeven, odd = dummyodd;

        while (curr != null) {
            if (curr.val % 2 == 0) {
                even.next = curr;
                even = even.next;
            } else {
                odd.next = curr;
                odd = odd.next;
            }

            curr = curr.next;
        }
        even.next = odd.next = null;
        if (dummyeven.next == null) {
            return dummyodd.next;
        }

        even.next = dummyodd.next;

        return dummyeven.next;
    }

    // 328
    public ListNode oddEvenList(ListNode head) {
        ListNode dummyeven = new ListNode(-1);
        ListNode dummyodd = new ListNode(-1);

        ListNode curr = head, even = dummyeven, odd = dummyodd;

        int idx = 0;
        while (curr != null) {
            if (idx == 0) {
                even.next = curr;
                even = even.next;
                idx = 1;
            } else {
                odd.next = curr;
                odd = odd.next;
                idx = 0;
            }

            curr = curr.next;
        }

        even.next = odd.next = null;
        if (dummyeven.next == null) {
            return dummyodd.next;
        }

        even.next = dummyodd.next;

        return dummyeven.next;
    }

    public ListNode mergeSortList(ListNode head) {
        if (head.next == null)
            return head;

        ListNode midNode = middleNode(head);
        ListNode nhead = midNode.next;
        midNode.next = null;

        return mergeTwoSortedLists(mergeSortList(head), mergeSortList(nhead));

    }

    public ListNode mergeKSortedLists(ListNode[] lists) { // brute force
        ListNode ans = null;
        for (ListNode node : lists) {
            ans = mergeTwoSortedLists(ans, node);
        }

        return ans;
    }

    public ListNode mergeKSortedLists1(ListNode[] lists) { // optimized
        int n = lists.length;
        return mergeKSortedLists1Helper(lists, 0, n - 1);
    }

    public ListNode mergeKSortedLists1Helper(ListNode[] lists, int si, int ei) { // optimized
        if (si == ei) {
            return lists[si];
        }

        int mid = (si + ei) / 2;
        return mergeTwoSortedLists(mergeKSortedLists1Helper(lists, si, mid),
                mergeKSortedLists1Helper(lists, mid + 1, ei));
    }

    public ListNode mergeKSortedLists2(ListNode[] lists) {
        if (lists.length == 0)
            return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> { // lambda func
            return a.val - b.val;
        });

        int k = lists.length;
        while (k-- > 0) {
            if (lists[k] != null)
                pq.add(lists[k]);
        }

        ListNode dummy = new ListNode(-1), ans = dummy;
        while (pq.size() > 0) {
            ListNode rem = pq.remove();
            ans.next = rem;
            ans = ans.next;

            if (rem.next != null)
                pq.add(rem.next);

        }

        return dummy.next;
    }

    // 25
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head, ah = null, at = null;
        while (curr != null) {
            int k1 = k;
            boolean kreverse = false;
            while (len(curr) >= k1 && k1-- > 0) {
                kreverse = true;
                ListNode forward = curr.next;
                curr.next = null;
                addFirst(curr);
                curr = forward;
            }
            if (kreverse) {
                if (ah == null && at == null) {
                    ah = th;
                    at = tt;
                } else {
                    at.next = th;
                    at = tt;
                }
                th = null;
                tt = null;
            } else {
                break;
            }

        }

        at.next = curr;

        return ah;
    }

    ListNode th = null, tt = null;

    private void addFirst(ListNode node) {
        if (th == null && tt == null)
            th = tt = node;
        else {
            node.next = th;
            th = node;
        }
    }

    public ListNode reverseKGroup1(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head), prev = dummy, curr = head;

        while (len(curr) >= k && curr != null) {
            int k1 = k;
            while (k1-- > 1) {
                curr = curr.next;
            }

            ListNode prevNext = prev.next;
            ListNode forward = curr.next;
            curr.next = null;

            ListNode ret = reverseGroup(prev.next, curr);
            prev.next = ret;
            prevNext.next = forward;

            prev = prevNext;
            curr = forward;
        }

        return dummy.next;
    }

    public ListNode reverseGroup(ListNode l1, ListNode l2) {
        ListNode prev = null;
        ListNode curr = l1;

        while (curr != null) {
            ListNode forward = curr.next;

            curr.next = prev;

            prev = curr;
            curr = forward;
        }
        return prev;
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null && head.next == null)
            return head;
        if (k == 1)
            return head;

        ListNode dummy = new ListNode(-1, head), prev = head, curr = head;

        while (len(curr) >= k && curr != null) {
            int k1 = k;
            while (k1-- > 1) {
                curr = curr.next;
            }

            ListNode forward = curr.next;
            curr.next = null;

            reverseKGroupHelper(prev, curr);
            curr.next = forward;

            prev = forward;
            curr = forward;
        }

        return dummy.next;
    }

    public void reverseKGroupHelper(ListNode n1, ListNode n2) {
        ListNode midNode = middleNode1(n1);
        n2 = reverseList(midNode);

        ListNode left = n1, right = n2;

        while (right != null) {
            swap(left, right);
            left = left.next;
            right = right.next;
        }

        reverseList(n2);
    }

    public void swap(ListNode n1, ListNode n2) {
        int temp = n1.val;
        n1.val = n2.val;
        n2.val = temp;
    }

    // 92
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right)
            return head;

        ListNode dummy = new ListNode(-1, head), prev = dummy;
        int left1 = left;
        while (left1-- > 1) {
            prev = prev.next;
        }

        ListNode prevNext = prev.next;
        prev.next = null;

        int k = (right - left + 1);
        prev.next = reverseStartingKNodes(prevNext, k);

        return dummy.next;
    }

    private ListNode reverseStartingKNodes(ListNode node, int k) {

        while (k-- > 0) {
            ListNode forward = node.next;
            addFirst(node);
            node = forward;
        }

        tt.next = node;
        return th;
    }

    public ListNode reverseBetween1(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head), prev = dummy, curr = head;

        int left1 = left;
        while (left1-- > 1) {
            prev = prev.next;
        }
        curr = prev;
        int k = right - left + 1;
        while (k-- > 0) {
            curr = curr.next;
        }

        ListNode prevNext = prev.next;
        ListNode forward = curr.next;
        curr.next = null;

        prev.next = reverseList(prev.next);
        prevNext.next = forward;

        return dummy.next;
    }

    // 203
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1, head), prev = dummy, curr = head;

        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }

            curr = curr.next;
        }

        return dummy.next;
    }

    // 817
    public int numComponents(ListNode head, int[] nums) {
        ListNode curr = head;
        HashSet set = new HashSet<>();
        for (int e : nums)
            set.add(e);

        int count = 0;
        while (curr != null) {
            // boolean isPresent = false;
            // while(curr!=null && set.contains(curr.val)){
            // isPresent = true;
            // curr = curr.next;
            // }

            // if(isPresent) count++;
            // else curr = curr.next;

            if (set.contains(curr.val) && (curr.next == null || !set.contains(curr.next.val))) {
                count++;
            }

            curr = curr.next;
        }

        return count;
    }

    // 138
    public ListNode copyRandomList(ListNode head) {
        copyNodes(head); // ts - O(n), sc - O(1) no extra space, new spaces are created for ans which is
                         // to be returned
        copyRandomNodes(head); // ts - O(n), sc - O(1)
        return extractList(head); // ts - O(n), sc - O(1)
    }

    public void copyNodes(ListNode head) {
        ListNode curr = head;

        while (curr != null) {
            ListNode forward = curr.next;

            ListNode nnode = new ListNode(curr.val);
            curr.next = nnode;
            nnode.next = forward;

            curr = forward;
        }
    }

    public void copyRandomNodes(ListNode head) {
        ListNode curr = head;

        while (curr != null) {
            ListNode randomNode = curr.random;
            if (randomNode != null)
                curr.next.random = randomNode.next;

            curr = curr.next.next;
        }
    }

    public ListNode extractList(ListNode head) {
        ListNode curr = head, dummy = new ListNode(-1), ans = dummy;

        while (curr != null) {
            // ListNode forward = curr.next.next;
            // ListNode cnext = curr.next;

            // curr.next = forward;
            // cnext.next = (forward==null ? null : forward.next);
            // ans.next = cnext;
            // ans=ans.next;

            // curr = forward;

            // or
            ans.next = curr.next;
            curr.next = curr.next.next;

            ans = ans.next;
            curr = curr.next;
        }

        return dummy.next;
    }

    public ListNode copyRandomList1(ListNode head) {
        HashMap<ListNode, ListNode> map = new HashMap<>();
        copyNodesAndPutIntoHashMap(head, map);
        return linkRandomNodes(head, map);
    }

    public void copyNodesAndPutIntoHashMap(ListNode head, HashMap<ListNode, ListNode> map) {

        ListNode curr = head;

        ListNode dummy = new ListNode(-1), ans = dummy;
        while (curr != null) {
            ans.next = new ListNode(curr.val);
            ans = ans.next;
            map.put(curr, ans);
            curr = curr.next;
        }
    }

    public ListNode linkRandomNodes(ListNode head, HashMap<ListNode, ListNode> map) {

        ListNode curr = head;

        while (curr != null) {
            ListNode randomNode = curr.random;
            if (randomNode == null)
                map.get(curr).random = null;
            else
                map.get(curr).random = map.get(randomNode);

            curr = curr.next;
        }

        return map.get(head);
    }

    public boolean isCyclePresentInLL(ListNode head) {

        if (head == null || head.next == null)
            return false;

        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow)
                return true;
        }
        return false;
    }

    public ListNode CycleNode(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head, fast = head;
        boolean cycleFound = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) {
                cycleFound = true;
                break;
            }
        }
        if (!cycleFound)
            return null;

        slow = head;
        fast = fast;

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow; // or fast
    }

    public ListNode IntersectionNodeInTwoLL(ListNode headA, ListNode headB) {
        int k = Math.abs(len(headA) - len(headB));

        if (len(headA) > len(headB)) {
            while (k-- > 0)
                headA = headA.next;
        } else {
            while (k-- > 0)
                headB = headB.next;
        }

        while (headA != null && headA != headB) { // not headA.val != headB.val becoz two nodes in two lists may have
                                                  // same values doesn't mean that node is intersecting node
            headA = headA.next;
            headB = headB.next;
        }

        return headA == null ? null : headA;
    }

    public ListNode IntersectionNodeInTwoLL1(ListNode headA, ListNode headB) {
        ListNode c1 = headA, c2 = headB;

        while (c1.next != null) { // reach the tail
            c1 = c1.next;
        }

        c1.next = c2; // make the cycle
        ListNode intersectingNode = CycleNode(headA); // find cycle node
        c1.next = null; // remove the cycle

        return intersectingNode;
    }

    public ListNode segregate01(ListNode head) {
        ListNode dummyZero = new ListNode(-1);
        ListNode dummyOne = new ListNode(-1);

        ListNode zero = dummyZero;
        ListNode one = dummyOne;

        ListNode curr = head;

        while (curr != null) {
            if (curr.val == 0) {
                zero.next = curr;
                zero = curr;
            } else if (curr.val == 1) {
                one.next = curr;
                one = curr;
            }
            curr = curr.next;
        }

        zero.next = dummyOne.next;
        one.next = null;

        return dummyZero.next;
    }

    public ListNode segregate012(ListNode head) {
        ListNode dummyZero = new ListNode(-1);
        ListNode dummyOne = new ListNode(-1);
        ListNode dummyTwo = new ListNode(-1);

        ListNode zero = dummyZero;
        ListNode one = dummyOne;
        ListNode two = dummyTwo;

        ListNode curr = head;

        while (curr != null) {
            if (curr.val == 0) {
                zero.next = curr;
                zero = curr;
            } else if (curr.val == 1) {
                one.next = curr;
                one = curr;
            } else {
                two.next = curr;
                two = curr;
            }
            curr = curr.next;
        }

        zero.next = dummyOne.next;
        one.next = dummyTwo.next;
        two.next = null;

        if (zero == dummyZero) {
            if (one == dummyOne) {
                return dummyTwo.next;
            } else {
                return dummyOne.next;
            }
        }
        return dummyZero.next;
    }

    public ListNode segregateOnLastIndex(ListNode head) {
        ListNode dummyS = new ListNode(-1);
        ListNode dummyL = new ListNode(-1);

        ListNode s = dummyS;
        ListNode l = dummyL;

        ListNode curr = head;
        int pivot = 0;

        while (curr.next != null) {
            curr = curr.next;
        }
        pivot = curr.val;
        curr = head;

        while (curr.next != null) {
            if (curr.val < pivot) {
                s.next = curr;
                s = curr;
            } else if (curr.val > pivot) {
                l.next = curr;
                l = curr;
            }
            curr = curr.next;
        }

        curr.next = dummyL.next;
        l.next = null;

        return curr;
    }

    public ListNode segregate(ListNode head, int pivotIdx) {
        ListNode dummyS = new ListNode(-1);
        ListNode dummyL = new ListNode(-1);

        ListNode s = dummyS;
        ListNode l = dummyL;

        ListNode curr = head;

        int pivotval = getNodeAt(head, pivotIdx).val;

        ListNode pivot = null;
        int i = 0;

        while (curr != null) {
            if (i == pivotIdx) {
                pivot = curr;
            } else if (curr.val <= pivotval) {
                s.next = curr;
                s = curr;
            } else if (curr.val > pivotval) {
                l.next = curr;
                l = curr;
            }
            i++;
            curr = curr.next;
        }

        s.next = pivot;
        pivot.next = dummyL.next;
        l.next = null;

        return dummyS.next;
    }

    public int getTailData(ListNode head) {
        ListNode curr = head;
        while (curr.next != null)
            curr = curr.next;
        return curr.val;
    }

    public ListNode segregateOnLastIndex(ListNode head) {
        int pivot = getTailData(head);
        ListNode curr = head, dummySmaller = new ListNode(-1), dummyLarger = new ListNode(-1), smaller = dummySmaller,
                larger = dummyLarger;

        while (curr != null) {
            if (curr.val <= pivot) {
                smaller.next = curr;
                smaller = smaller.next;
            } else {
                larger.next = curr;
                larger = larger.next;
            }
            curr = curr.next;
        }

        smaller.next = null;
        larger.next = null;

        smaller.next = dummyLarger.next;

        return smaller;
    }

    public int getTailData1(ListNode head) {
        while (head.next != null)
            head = head.next;
        return head.val;
    }

    public ListNode segregateOnLastIndex1(ListNode head) {
        int pivot = getTailData(head);
        ListNode dummySmaller = new ListNode(-1), dummyLarger = new ListNode(-1), smaller = dummySmaller,
                larger = dummyLarger;

        while (head != null) {
            if (head.val <= pivot) {
                smaller.next = head;
                smaller = smaller.next;
            } else {
                larger.next = head;
                larger = larger.next;
            }
            head = head.next;
        }

        smaller.next = null;
        larger.next = null;

        smaller.next = dummyLarger.next;

        return smaller;
    }

    public ListNode getNodeAt(ListNode head, int idx) {
        ListNode curr = head;
        while (idx-- > 0) {
            curr = curr.next;
        }
        return curr;
    }

    public ListNode segregateOnPivotIndex(ListNode head, int pivotIdx) {
        // when this list gets sorted the pivot must be at its right place
        // that's why we are using getNodeAt() not getAt() becoz we want that exact node
        // not its val becoz duplicates of pivot r also available.

        // failed tc-
        // input-
        // 12
        // 9 8 8 0 8 0 13 9 0 8 6 13
        // 4

        // expected o/p - 8 8 0 0 0 8 6 8 9 13 9 13
        // actual o/p - 8 8 0 8 0 0 8 6 9 13 9 13

        ListNode pivot = getNodeAt(head, pivotIdx);

        ListNode curr = head, dummySmaller = new ListNode(-1), dummyLarger = new ListNode(-1), smaller = dummySmaller,
                larger = dummyLarger;

        while (curr != null) {
            if (curr != pivot && curr.val <= pivot.val) { // try not
                smaller.next = curr;
                smaller = smaller.next;
            } else if (curr != pivot && curr.val > pivot.val) {
                larger.next = curr;
                larger = larger.next;
            }
            curr = curr.next;
        }

        smaller.next = null;
        larger.next = null;

        smaller.next = pivot;
        pivot.next = dummyLarger.next;

        return dummySmaller.next;
    }

    // sir can we solve faang2.0 list ques parallel with this course?

    // 1171
    public ListNode removeZeroSumSublists(ListNode head) {
        HashMap<Integer, ListNode> map = new HashMap<>(); // sum, index
        ListNode dummy = new ListNode(-1, head);
        map.put(0, dummy);

        ListNode curr = head;
        int sum = 0;

        while (curr != null) {
            sum += curr.val;

            if (map.containsKey(sum)) {
                ListNode lNode = map.get(sum); // last node with same sum

                removeFromHashMap(map, lNode.next, curr, sum); // remove sums from hashmap of nodes to be removed
                lNode.next = curr.next;
            } else {
                map.put(sum, curr);
            }

            curr = curr.next;

        }

        return dummy.next;
    }

    static void removeFromHashMap(HashMap<Integer, ListNode> map, ListNode snode, ListNode enode, int sum) {
        ListNode curr = snode;
        int csum = sum;
        while (curr != enode) {
            csum += curr.val;
            map.remove(csum);
            curr = curr.next;
        }
    }

    class LRUCache {
    
        private HashMap<Integer, Node> map;
        private Node head = null, tail = null;
        private int size = 0;
        
        private class Node{
            int appName = 0;
            int state = 0;
            Node prev = null;
            Node next = null;
            
            Node(int appName){
                this.appName = appName;
            }
            
            Node(int appName, int state){
                this.appName = appName;
                this.state = state;
            }
        }
    
        public LRUCache(int capacity) {
            map = new HashMap<>();
            size = capacity;
        }
        
        // get recent appName
        public int get(int key) {
            if(!map.containsKey(key)) return -1; // not present in recent app
            
            return fetchNode(key).state;
        }
        
        private void makeItRecentApp(Node node){
            if(node == head) return; // it is already the recent app
             
            removeNode(node);
            addFirst(node);
        }
        
        private void removeNode(Node node){
            if(map.size()==1){
                head = tail = null;
            }
            else if(head == node){
                Node nodePrev = head.prev;
                nodePrev.next = null;
                head.prev = null;
                head = nodePrev;
            }
            else if(tail == node){
                Node tailNext = tail.next;
                tailNext.prev = null;
                tail.next = null;
                tail = tailNext;
            }
            else{
                Node prevNode = node.prev;
                Node nextNode = node.next;
                
                prevNode.next = nextNode;
                nextNode.prev = prevNode;
                
                node.prev = null;
                node.next = null;
            }
        }
        
        private void addFirst(Node node){
            if(head == null){
                head = tail = node;
            }
            else{
                head.next = node;
                node.prev = head;
    
                head = node;
            }
        }
        
        // appName, appState
        public void put(int key, int value) {
            if(map.containsKey(key)){
                Node node = fetchNode(key);
                node.state = value;
            }
            else{
                Node node = new Node(key, value);
                addFirst(node);
                map.put(key, node);
                if(map.size()>size){
                    Node tail = this.tail;
                    removeNode(tail);
                    map.remove(tail.appName);
                }
            }
        }
        
        private Node fetchNode(int key){
            Node node = map.get(key);
            makeItRecentApp(node);
            return node;
        }
    }

    class MyLinkedList {
        private class Node{
            int val=0;
            Node next=null;
            
            Node(int val){
                this.val = val;
            }
        }
        
        private Node head = null, tail = null;
        private int size = 0;
    
        public MyLinkedList() {
            
        }
        
        public int get(int index) {
            if(index < 0 || index >= size) return -1;
            Node curr = head;
            int i=0;
            while(i!=index){
                curr = curr.next;
                i++;
            }
                
            return curr.val;    
        }
        
        public void addAtHead(int val) {
            Node node = new Node(val);
            if(head == null && tail == null){
                head = tail = node;
            }
            else{
                node.next = head;
                head = node;
            }
            size++;
        }
        
        public void addAtTail(int val) {
            Node node = new Node(val);
            if(head == null && tail == null){
                head = tail = node;
            }
            else{
                tail.next = node;
                tail = node;
            }
            size++;
        }
        
        public void addAtIndex(int index, int val) {
            if(index<0 || index > size) return;
            else if(index == 0){
              addAtHead(val); 
            } 
            else if(index == size){
              addAtTail(val);  
            }
            else{
                Node prev = getNodeAt(index-1);
                Node forward = prev.next;
                
                Node node = new Node(val);
                prev.next = node;
                node.next = forward;
                size++; 
            }
            
        }
        
        private Node getNodeAt(int idx){
            Node curr = head;
            while(idx-->0){
                curr = curr.next;
            }
            return curr;
        }
        
        public void deleteAtIndex(int index) {
            if(size==0) return;
            else if(index < 0 || index >= size) return;
            else if(size==1 && index == 0){
                head = tail = null; 
                size--;
            }
            else if(index==0){
                deleteHead();
            }
            else if(index==size-1){
                deleteTail();
            }
            else{
                Node prev = getNodeAt(index-1);
                Node forward = prev.next.next;
                
                prev.next.next = null;
                prev.next = forward;
                size--;
            }
        }
        
        private void deleteHead(){
            Node headNext = head.next;
            head.next = null;
            head = headNext;
            size--;
        }
        
        private void deleteTail(){
            Node curr = head;
            while(curr.next!=tail){
                curr = curr.next;
            }
            
            curr.next = null;
            tail = curr;
            size--;
        }
    }

    class AllOne {
    
        private class Node{
            int count = 0;
            ArrayList<String> al;
            Node next = null;
            Node prev = null;
            
            Node(int count, String s){
                this.count = count;
                al = new ArrayList<>();
                al.add(s);
            }
        }
        
        private Node head = null, tail = null;
        private HashMap<String, Node> map;
    
        public AllOne() {
            map = new HashMap<>();
        }
        
        public void inc(String key) {
            if(map.size()==0){
                Node node = new Node(1, key);
                map.put(key, node);
                head = tail = node;
            }
            else if(!map.containsKey(key)){
                head.al.add(key);
                map.put(key, head);
            }
            else if(map.containsKey(key)){
                Node node = map.get(key);
                Node nodeNext = node.next;
                
                if(nodeNext==null){
                    Node newNode = new Node(node.count+1, key);
                    
                    node.next = newNode;
                    newNode.prev = node;
                    
                    tail = newNode;
                    
                    map.put(key, newNode);
                }
                else if(nodeNext.count == node.count+1){
                    nodeNext.al.add(key);
                    
                    map.put(key, nodeNext);
                }
                else{
                    Node newNode = new Node(node.count+1, key);
                    
                    node.next = newNode;
                    newNode.prev = node;
                    
                    newNode.next = nodeNext;
                    nodeNext.prev = newNode;
                    
                    map.put(key, newNode);
                }
                
                node.al.remove(key);
                
            }
        }
            
        
        public void dec(String key) {
            Node node = map.get(key); 
            Node nodePrev = node.prev;
            
            if(nodePrev==null){
                map.remove(key);
            }
            else if(nodePrev.count == node.count-1){
                nodePrev.al.add(key);
                
                map.put(key, nodePrev);
            }
            else{
                Node newNode = new Node(node.count-1, key);
                
                nodePrev.next = newNode;
                newNode.prev = nodePrev;
                
                newNode.next = node;
                node.prev = newNode;
                
                map.put(key, newNode);
            }
            
            node.al.remove(key);
            
        }
        
        public String getMaxKey() {
            if(tail==null) return "";
            
            Node curr = tail;
            while(curr.al.size()==0){
                curr = curr.prev; 
                if(curr==null){
                    return "";
                }
            }
            return curr.al.get(0);
        }
        
        public String getMinKey() {
            if(head==null) return "";
            
            Node curr = head;
            while(curr.al.size()==0){
                curr = curr.next; 
                if(curr==null){
                    return "";
                }
            }
            return curr.al.get(0);
        }
    }



}
