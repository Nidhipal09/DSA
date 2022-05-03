public class Stacks {
    public static int[] ngr(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        ans[n - 1] = -1;

        Stack<Integer> st = new Stack<>();
        st.push(arr[n - 1]);

        for (int i = n - 2; i >= 0; i--) {
            int e = arr[i];

            while (st.size() > 0 && e > st.peek()) {
                st.pop();
            }

            if (st.size() == 0) {
                ans[i] = -1;
            } else {
                ans[i] = st.peek();
            }

            st.push(e);
        }

        return ans;
    }

    public static int[] ngl(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        ans[0] = -1;

        Stack<Integer> st = new Stack<>();
        st.push(arr[0]);

        for (int i = 1; i < n; i++) {
            int e = arr[i];

            while (st.size() > 0 && e > st.peek()) {
                st.pop();
            }

            if (st.size() == 0) {
                ans[i] = -1;
            } else {
                ans[i] = st.peek();
            }

            st.push(e);
        }

        return ans;
    }

    public static int[] nsr(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        ans[n - 1] = -1;

        Stack<Integer> st = new Stack<>();
        st.push(arr[n - 1]);

        for (int i = n - 2; i >= 0; i--) {
            int e = arr[i];

            while (st.size() > 0 && e < st.peek()) {
                st.pop();
            }

            if (st.size() == 0) {
                ans[i] = -1;
            } else {
                ans[i] = st.peek();
            }

            st.push(e);
        }

        return ans;
    }

    public static int[] nsl(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        ans[0] = -1;

        Stack<Integer> st = new Stack<>();
        st.push(arr[0]);

        for (int i = 1; i < n; i++) {
            int e = arr[i];

            while (st.size() > 0 && e < st.peek()) {
                st.pop();
            }

            if (st.size() == 0) {
                ans[i] = -1;
            } else {
                ans[i] = st.peek();
            }

            st.push(e);
        }

        return ans;
    }

    public static int[] nextGreaterElement(int[] arr, int[] query) {

        int[] ngr = ngr(arr);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], ngr[i]);
        }

        int nq = query.length;
        int[] ans = new int[nq];

        for (int i = 0; i < nq; i++) {
            ans[i] = map.get(query[i]);
        }

        return ans;
    }

    public static int[] nextGreaterElementII(int[] arr) {
        int n = arr.length;

        Stack<Integer> st = new Stack<>();

        for (int i = n - 2; i >= 0; i--) {
            int e = arr[i];

            while (st.size() > 0 && e >= st.peek()) {
                st.pop();
            }
            st.push(e);
        }

        int[] ans = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            int e = arr[i];

            while (st.size() > 0 && e >= st.peek()) {
                st.pop();
            }

            if (st.size() == 0) {
                ans[i] = -1;
            } else {
                ans[i] = st.peek();
            }

            st.push(e);
        }
        return ans;
    }

    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;

        int[] nsr = nsr(heights);
        int[] nsl = nsl(heights);

        int[] widths = new int[n];
        for (int i = 0; i < n; i++) {
            widths[i] = nsr[i] - nsl[i] - 1;
        }

        int max = 0, area = 0;
        for (int i = 0; i < n; i++) {
            area = widths[i] * heights[i];
            max = area > max ? area : max;
        }

        return max;

    }

    public static int[] nsr1(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        ans[n - 1] = n;

        Stack<Integer> st = new Stack<>();
        st.push(n - 1);

        for (int i = n - 2; i >= 0; i--) {
            int e = arr[i];

            while (st.size() > 0 && e <= arr[st.peek()]) {
                st.pop();
            }

            if (st.size() == 0) {
                ans[i] = n;
            } else {
                ans[i] = st.peek();
            }

            st.push(i);
        }

        return ans;
    }

    public static int[] nsl1(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        ans[0] = -1;

        Stack<Integer> st = new Stack<>();
        st.push(0);

        for (int i = 1; i < n; i++) {
            int e = arr[i];

            while (st.size() > 0 && e <= arr[st.peek()]) {
                st.pop();
            }

            if (st.size() == 0) {
                ans[i] = -1;
            } else {
                ans[i] = st.peek();
            }

            st.push(i);
        }

        return ans;
    }

    public static int largestRectangleArea1(int[] heights) {
        int n = heights.length;

        Stack<Integer> st = new Stack<>();
        st.push(-1);

        int max = 0;
        for (int i = 0; i <= n; i++) {
            int e = i == n ? 0 : heights[i];

            while (st.peek() != -1 && heights[st.peek()] >= e) {
                int rm = i;
                int h = heights[st.pop()];
                int lm = st.peek();

                int area = (rm - lm - 1) * h;
                max = max > area ? max : area;
            }

            st.push(i);
        }

        return max;

    }

    public static int maximalRectangle(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        int[] hist = new int[n];

        for (int i = 0; i < n; i++) {
            hist[i] = arr[0][i];
        }

        int max = largestRectangleArea1(hist);
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 0)
                    hist[j] = 0;
                else
                    hist[j] += 1;
            }
            int area = largestRectangleArea1(hist);
            max = max > area ? max : area;

        }
        return max;
    }

    public static boolean validateStackSequences(int[] pushed, int[] popped) {

        int n = pushed.length;
        Stack<Integer> st = new Stack<>();

        int j = 0;
        for (int i = 0; i < n; i++) {
            int e = pushed[i];
            st.push(e);

            while (j < n && st.size() > 0 && popped[j] == st.peek()) {
                st.pop();
                j++;
            }
        }

        return st.size() == 0; // or j==n
    }

    public static int minAddToMakeValid(String s) {

        int n = s.length();
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch == '(')
                st.push('(');

            else if (st.size() > 0 && st.peek() == '(')
                st.pop();

            else
                st.push(')');

        }
        return st.size();
    }

    public static String removeOuterParentheses(String s) {

        int n = s.length();
        Stack<Character> st = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                if (st.size() > 0)
                    sb.append(ch);
                st.push('(');
            } else {
                if (st.size() > 1)
                    sb.append(ch);
                st.pop();
            }
        }

        return sb.toString();
    }

    public static int scoreOfParentheses(String s) {
        int n = s.length();
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                st.push(-1);
            } else {
                if (st.peek() == -1) {
                    st.pop();
                    st.push(1);
                } else {
                    int score = 0;
                    while (st.peek() != -1) {
                        score += st.pop();
                    }
                    st.pop();
                    st.push(score * 2);
                }

            }
        }

        int score = 0;
        while (st.size() > 0)
            score += st.pop();
        return score;
    }

    public static String reverseParentheses(String s) {
        int n = s.length();
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch == ')') {
                Queue<Character> q = new LinkedList<>();
                while (st.peek() != '(') {
                    q.add(st.pop());
                }
                st.pop();
                while (q.size() > 0) {
                    st.push(q.remove());
                }
            } else {
                st.push(ch);
            }
        }

        char[] ans = new char[st.size()];
        int i = st.size() - 1;
        while (i >= 0) {
            ans[i] = st.pop();
            i--;
        }
        return new String(ans);
    }

    public static String minRemoveToMakeValidParentheses(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (chars[i] == '(') {
                st.push(i);
            } else if (chars[i] == ')') {
                if (st.size() == 0) {
                    chars[i] = '.';
                } else
                    st.pop();
            }
        }

        while (st.size() > 0) {
            chars[st.pop()] = '.';
        }

        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (c != '.')
                sb.append(c);
        }

        return sb.toString();
    }

    static class StockSpanner {

        static class Pair {
            int price;
            int index;
        }

        Stack<Pair> st;
        int time = 0;

        public StockSpanner() {
            st = new Stack<>();
            Pair base = new Pair();
            base.price = 1000000;
            base.index = -1;
            st.push(base);
        }

        public int next(int price) {
            Pair p = new Pair();
            p.price = price;
            p.index = time;
            time++;

            while (st.peek().price <= p.price) {
                st.pop();
            }

            int ans = p.index - st.peek().index;

            st.push(p);
            return ans;
        }
    }

    static class Pair {
        int id, st, ct;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        // use read for taking input
        int n = Integer.parseInt(read.readLine());
        int[] time = new int[n];
        int intervals = Integer.parseInt(read.readLine());
        Stack<Pair> st = new Stack<>();

        for (int i = 0; i < intervals; i++) {
            String[] l = read.readLine().split(":"); // 0-id, 1-s or e, 2- time

            if (l[1].equals("start")) {
                Pair p = new Pair();
                p.id = Integer.parseInt(l[0]);
                p.st = Integer.parseInt(l[2]);
                p.ct = 0;
                st.push(p);
            } else {
                int et = Integer.parseInt(l[2]);
                Pair p = st.pop();
                int t = et - p.st + 1;
                if (st.size() > 0)
                    st.peek().ct += t;
                time[p.id] += t - p.ct;

            }
        }

        for (int e : time)
            System.out.println(e);
    }

    public static boolean pattern132(int[] arr) {
        int n = arr.length;
        int[] min = new int[n];
        int minval = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min[i] = arr[i] < minval ? arr[i] : minval;
            minval = min[i];
        }

        Stack<Integer> st = new Stack<>(); // k
        st.push(arr[n - 1]);

        int j = n - 2;
        while (j > 0) {
            if (min[j] < st.peek()) {
                if (st.peek() < arr[j])
                    return true;
            } else {
                st.pop();
            }
            st.push(arr[j]);
            j--;
        }

        return false;
    }

    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for (int e : asteroids) {
            if (e > 0) {
                st.push(e);
            } else {
                while (st.size() > 0 && st.peek() > 0 && st.peek() < -e) {
                    st.pop();
                }
                if (st.size() > 0 && st.peek() == -e) {
                    st.pop();
                } else if (st.size() > 0 && st.peek() > -e) {

                } else {
                    st.push(e);
                }
            }

        }

        int ans[] = new int[st.size()];
        int i = st.size() - 1;
        while (i >= 0) {
            ans[i] = st.pop();
            i--;
        }

        return ans;
    }

    public static String removeKDigits(String s, int k) {
        Stack<Character> st = new Stack<>();
        st.push(s.charAt(0));

        int n = s.length();
        int i = 1;
        while (i < n) {
            char ch = s.charAt(i);
            while (st.size() > 0 && k > 0 && ch < st.peek()) {
                st.pop();
                k--;
            }
            st.push(ch);

            i++;
        }

        while (k > 0) {
            st.pop();
            k--;
        }

        char[] ans = new char[st.size()];
        int j = ans.length - 1;
        while (j >= 0) {
            ans[j--] = st.pop();
        }

        int z = 0;
        while (z < ans.length && ans[z] == '0')
            z++;

        StringBuilder sb = new StringBuilder();
        while (z < ans.length)
            sb.append(ans[z++]);
        if (sb.length() == 0)
            sb.append("0");
        return sb.toString();
    }

    public static String removeDuplicateLetters(String s) {

        int n = s.length();

        Stack<Character> st = new Stack<>();
        int[] freq = new int[26];
        boolean[] exists = new boolean[26];

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            freq[ch - 'a']++;
        }

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            freq[ch - 'a']--;
            if (exists[ch - 'a'])
                continue;

            while (st.size() > 0 && st.peek() > ch && freq[st.peek() - 'a'] > 0) {
                char rem = st.pop();
                exists[rem - 'a'] = false;
            }
            st.push(ch);
            exists[ch - 'a'] = true;
        }

        char ans[] = new char[st.size()];
        int c = ans.length - 1;
        while (st.size() > 0) {
            ans[c--] = st.pop();
        }

        return new String(ans);
    }

    public static int[] smallest(int[] nums, int k) {
        int n = nums.length;
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            int e = nums[i];

            while (st.size() > 0 && st.peek() > e && n - i - 1 >= k - st.size()) {
                st.pop();
            }
            if (st.size() < k)
                st.push(e);
        }

        int ans[] = new int[k];
        int c = k - 1;
        while (st.size() > 0) {
            ans[c--] = st.pop();
        }
        return ans;
    }

    public static class CustomStack {

        int value[];
        int increment[];
        int index;

        public CustomStack(int maxSize) {
            value = new int[maxSize];
            increment = new int[maxSize];
            index = -1;
        }

        public void push(int x) {
            if (index + 1 == value.length)
                return;

            index++;
            value[index] = x;
        }

        public int pop() {
            if (index == -1)
                return -1;

            int x = value[index];
            int inc = increment[index];
            value[index] = 0;
            increment[index] = 0;
            index--;
            if (index >= 0) {
                increment[index] += inc;
            }

            return x + inc;
        }

        public void increment(int k, int val) {
            int idx = Math.min(k - 1, index);

            if (index >= 0) {
                increment[idx] += val;
            }
        }
    }

    public static boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        
        int n = s.length();
        for(int i=0; i<n; i++){
            char ch = s.charAt(i);
            
            if(ch == 'c'){
               if(st.size()>=2 && st.pop() == 'b' && st.pop()=='a'){
                 // paired  
               }
               else return false;
            } 
            else st.push(ch);  // a,b
        }
        
        return true;
        
     }

     public static int trap(int[] height) {
        Stack<Integer> st = new Stack<>();
        int n = height.length;
        st.push(0);
        int rain = 0;
        
        for(int i=1; i<n; i++){
            int e = height[i];
            
            while(st.size()>0 && e > height[st.peek()]) {
                int rm = i;
                int curr = height[st.pop()];
                if(st.size()==0) break;
                int lm = st.peek();
                int wt = rm - lm - 1;
                int ht = Math.min(height[lm], height[rm])-curr;
                rain += wt * ht;
            }
            st.push(i);
        }
        
        return rain;
    }

    public static int validSubarrays(int[] nums) {
        // next smaller e on right
        
           int n = nums.length, ans = 1;
   
           Stack<Integer> st = new Stack<>();
           st.push(n - 1);
   
           for (int i = n - 2; i >= 0; i--) {
   
               while (st.size() > 0 && nums[i] <= nums[st.peek()]) {
                   st.pop();
               }
   
               if (st.size() == 0) {
                   ans += n-i;
               } else {
                   ans += st.peek()-i;
               }
               
               st.push(i);
   
           }
   
           return ans;
     }
   
     public static int calculate(String s){
        int n = s.length(), sum = 0, sign = 1;
   
        Stack<Integer> st = new Stack<>();
        for(int i=0; i<n; i++){
            char ch = s.charAt(i);
            
            if(Character.isDigit(ch)){
               
               int val = 0;
               while(i < n && Character.isDigit(s.charAt(i))){
                val = val*10 + (s.charAt(i)-'0');                   i++;
               }
               i--;
               val = val*sign;
               sign = +1;
               sum += val;
            }
            else if(ch == '('){
                st.push(sum);
                st.push(sign);
                
                sum=0;
                sign= +1;
            }
            else if(ch == ')'){
                sum *= st.pop();  // sign
                sum += st.pop();  // sum
            }
            else if(ch == '-'){
                sign *= -1;
            }
        }
        return sum;
    }


    public static int calculate(String s) {

        int n = s.length();
        char sign = '+';
   
        Stack<Integer> st = new Stack<>();
        for(int i=0; i<n; i++){
            char ch = s.charAt(i);
            
            if(Character.isDigit(ch)){
               
               int val = 0;
               while(i < n && Character.isDigit(s.charAt(i))){
                val = val*10 + (s.charAt(i)-'0');                   i++;
               }
               i--;
               
               if(sign == '+'){
                   st.push(val);
               }
               else if(sign == '-'){
                   st.push(-val);
               }
               else if(sign == '*'){
                   int a = st.pop();
                   st.push(a*val);
               }
               else if(sign == '/'){
                   int a = st.pop();
                   st.push(a/val);
               }
            }
            else if(ch != ' ') sign = ch;
            
        }
        
        int sum=0;
        while(st.size()>0) sum += st.pop();
        return sum;
  }

  


}
