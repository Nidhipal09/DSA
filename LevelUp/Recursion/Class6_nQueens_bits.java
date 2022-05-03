public class Class6_nQueens_bits {
    
    public static void main(String[] args) {
        // System.out.println(nQueens2dAs1dCanKillCombinations(4, 4, 4, -1, ""));
        // System.out.println(nQueens2dAs1dCanKillCombinationsUsingBits(4, 4, 4, -1, ""));
        // System.out.println(nQueens2dAs1dCanKillPermutations(4, 4, 4, ""));
        // System.out.println(nQueens2dAs1dCanKillPermutationsUsingBits(4, 4, 4, ""));
        // System.out.println(nQueensCanKillCombinationsUsingBits(4, 0, ""));
        System.out.println(nQueensCanKillPermutationsUsingBits(4, 1, 4, ""));
    }

    static boolean[] rows = new boolean[4];  // if not static pass a 2d array in the arguments
    static boolean[] cols = new boolean[4];
    static boolean[] dia = new boolean[7];
    static boolean[] adia = new boolean[7];
      

    public static int nQueens2dAs1dCanKillCombinations(int m, int n, int tq, int bno, String asf) {
        if (tq==0) {
            System.out.println(asf);
            return 1;
        }

        int count=0;
        for(int bidx=bno+1; bidx<m*n; bidx++){
            int r = bidx/n;
            int c = bidx%n;
            if(!rows[r] && !cols[c] && !dia[r+c] && !adia[r-c+n-1]){
                rows[r] = cols[c] = dia[r+c] = adia[r-c+n-1] = true; 
                count += nQueens2dAs1dCanKillCombinations(m, n, tq-1, bidx, asf+"["+r+","+c+"] ");
                rows[r] = cols[c] = dia[r+c] = adia[r-c+n-1] = false; 
            }
        }

        return count;
    }

    static int rowsb = 0;  // if not static pass a 1d array in the arguments
    static int colsb = 0;
    static int diab = 0;
    static int adiab = 0;

    public static int nQueens2dAs1dCanKillCombinationsUsingBits(int m, int n, int tq, int bno, String asf) { 
        if (tq==0) {
            System.out.println(asf);
            return 1;
        }

        int count=0;
        for(int bidx=bno+1; bidx<m*n; bidx++){
            int r = bidx/n;
            int c = bidx%n;
            if((rowsb & (1<<r))==0 && (colsb & (1<<c))==0 && (diab & (1<<(r+c)))==0 && (adiab & (1<<(r-c+n-1)))==0){
                rowsb |= (1<<r);
                colsb |= (1<<c);
                diab  |= (1<<(r+c));
                adiab |= (1<<(r-c+n-1));
                count += nQueens2dAs1dCanKillCombinationsUsingBits(m, n, tq-1, bidx, asf+"["+r+","+c+"] ");
                rowsb &= ~(1<<r);
                colsb &= ~(1<<c);
                diab  &= ~(1<<(r+c));
                adiab &= ~(1<<(r-c+n-1));
            }
        }

        return count;
    }

    public static int nQueens2dAs1dCanKillPermutations(int m, int n, int tq, String asf) {
        if (tq==0) {
            System.out.println(asf);
            return 1;
        }

        int count=0;
        for(int bidx=0; bidx<m*n; bidx++){
            int r = bidx/n;
            int c = bidx%n;
            if(!rows[r] && !cols[c] && !dia[r+c] && !adia[r-c+n-1]){
                rows[r] = cols[c] = dia[r+c] = adia[r-c+n-1] = true; 
                count += nQueens2dAs1dCanKillPermutations(m, n, tq-1, asf+"["+r+","+c+"] ");
                rows[r] = cols[c] = dia[r+c] = adia[r-c+n-1] = false; 
            }
        }

        return count;
    }

    public static int nQueens2dAs1dCanKillPermutationsUsingBits(int m, int n, int tq, String asf) { // O((nm)^tq) 
        if (tq==0) {
            System.out.println(asf);
            return 1;
        }

        int count=0;
        for(int bidx=0; bidx<m*n; bidx++){
            int r = bidx/n;
            int c = bidx%n;
            if((rowsb & (1<<r))==0 && (colsb & (1<<c))==0 && (diab & (1<<(r+c)))==0 && (adiab & (1<<(r-c+n-1)))==0){
                rowsb |= (1<<r);
                colsb |= (1<<c);
                diab  |= (1<<(r+c));
                adiab |= (1<<(r-c+n-1));
                count += nQueens2dAs1dCanKillPermutationsUsingBits(m, n, tq-1, asf+"["+r+","+c+"] ");
                rowsb &= ~(1<<r);
                colsb &= ~(1<<c);
                diab  &= ~(1<<(r+c));
                adiab &= ~(1<<(r-c+n-1));
            }
        }

        return count;
    }

    public static int nQueensCanKillCombinationsUsingBits(int n, int r, String asf) {
        if (r==n) {
            System.out.println(asf);
            return 1;
        }

        int count=0;
        for(int c=0; c<n; c++){
            if((rowsb & (1<<r))==0 && (colsb & (1<<c))==0 && (diab & (1<<(r+c)))==0 && (adiab & (1<<(r-c+n-1)))==0){
                rowsb |= (1<<r);
                colsb |= (1<<c);
                diab  |= (1<<(r+c));
                adiab |= (1<<(r-c+n-1));
                count += nQueensCanKillCombinationsUsingBits(n, r+1, asf+"["+r+","+c+"] ");
                rowsb &= ~(1<<r);
                colsb &= ~(1<<c);
                diab  &= ~(1<<(r+c));
                adiab &= ~(1<<(r-c+n-1));
            }
        }

        return count;
    }

    public static int nQueensCanKillPermutationsUsingBits(int n, int qn, int tq, String asf) {
        if (qn==tq+1) {
            System.out.println(asf);
            return 1;
        }

        int count=0;
        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                if((rowsb & (1<<r))==0 && (colsb & (1<<c))==0 && (diab & (1<<(r+c)))==0 && (adiab & (1<<(r-c+n-1)))==0){
                    rowsb |= (1<<r);
                    colsb |= (1<<c);
                    diab  |= (1<<(r+c));
                    adiab |= (1<<(r-c+n-1));
                    count += nQueensCanKillPermutationsUsingBits(n, qn+1, tq, asf+qn+"["+r+","+c+"] ");
                    rowsb &= ~(1<<r);
                    colsb &= ~(1<<c);
                    diab  &= ~(1<<(r+c));
                    adiab &= ~(1<<(r-c+n-1));
                }
            }
    
        }
        
        return count;
    }
}
