package Basics;

public class Patterns {

    // This process can be correlated with a construction of a house. A person hires three men; an electrician, a painter, and a partitioner.
    // The person has no idea about how the work of the three men is executed or how they will do their work, he just knows that upon payment of certain amount of currency, he will get his desired specifications in his house. This is his faith (working on abstraction). If he was concerned with the working of every layer (each of the three men's work), he would not be able to get his job done properly (finished house).
    
    public static void pattern1(int n) {  // Abstraction means giving your thinking a layered overview. When working on a layer based or placed over another layer, we have faith that the layer related or below is going to work perfectly.
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) { // magic-> step by step
                System.out.print("*\t");
            }
            System.out.println();
        }
    }

    public static void pattern2(int n) {
        // for(int i=1; i<=n; i++){ // 1 way

        // for(int j=1; j<= n-i+1; j++){
        // System.out.print("*\t");
        // }
        // System.out.println();
        // }

        for (int i = n; i >= 1; i--) { // 2 way
            for (int j = 1; j <= i; j++) {
                System.out.print("*\t");
            }
            System.out.println();
        }
    }

    public static void pattern3(int n) {
        int sp = n - 1, st = 1;
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= sp; j++) { // spaces
                System.out.print("\t");
            }

            for (int j = 1; j <= st; j++) { // stars
                System.out.print("*\t");
            }

            sp--;
            st++;

            System.out.println();
        }
    }

    public static void pattern4(int n) {
        int sp = 0, st = n;
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= sp; j++) { // spaces
                System.out.print("\t");
            }

            for (int j = 1; j <= st; j++) { // stars
                System.out.print("*\t");
            }

            sp++;
            st--;

            System.out.println();
        }
    }

    public static void pattern5(int n) {
        int sp = n / 2, st = 1;
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= sp; j++) { // spaces
                System.out.print("\t");
            }

            for (int j = 1; j <= st; j++) { // stars
                System.out.print("*\t");
            }

            System.out.println();

            if (i <= n / 2) {
                sp--;
                st += 2;
            } else {
                sp++;
                st -= 2;
            }

        }
    }

    public static void pattern6(int n) {
        int sp = 1, st = n / 2 + 1;

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= st; j++) { // stars
                System.out.print("*\t");
            }

            for (int j = 1; j <= sp; j++) { // spaces
                System.out.print("\t");
            }

            for (int j = 1; j <= st; j++) { // stars
                System.out.print("*\t");
            }

            System.out.println();

            if (i <= n / 2) {
                sp += 2;
                st--;
            } else {
                sp -= 2;
                st++;
            }

        }
    }

    public static void pattern7(int n) {
        // int sp=0;
        // for (int i = 1; i <= n; i++) { // 1 way

        // for (int j = 1; j <= sp; j++) { // spaces
        // System.out.print("\t");
        // }
        // System.out.print("*\t");

        // System.out.println();

        // sp++;
        // }

        for (int i = 1; i <= n; i++) { // 2 way

            for (int j = 1; j <= i; j++) {
                if (i == j)
                    System.out.print("*\t"); // d1-> i=j
                else
                    System.out.print("\t");
            }

            System.out.println();

        }
    }

    public static void pattern8(int n) {

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= n - i + 1; j++) { // d2-> i+j=n+1
                if (j == n - i + 1)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }

            System.out.println();

        }
    }

    public static void pattern9(int n) {

//         for (int i = 1; i <= n; i++) {

//             for (int j = 1; j <= n; j++) {
//                 if (i == j || i + j == n + 1)
//                     System.out.print("*\t");
//                 else
//                     System.out.print("\t");
//             }

//             System.out.println();

//         }
        
        int os=0, is=n-2;
        
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= os; j++) {
                System.out.print("\t");
            }
            System.out.print("*\t");
            
            for (int j = 1; j <= is; j++) {
                System.out.print("\t");
            }
            if(i!=(n/2+1)) System.out.print("*\t");
            
            
            System.out.println();
            
            if(i<=n/2){
                os++;
                is-=2;
            }else{
                os--;
                is+=2;
            }
            

        }
        
        
    }

    public static void pattern10(int n) {
        int os = n / 2, is = -1;

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= os; j++) {
                System.out.print("\t");
            }
            System.out.print("*\t");

            for (int j = 1; j <= is; j++) {
                System.out.print("\t");
            }
            if (i > 1 && i < n)
                System.out.print("*\t");
            System.out.println();

            if (i <= n / 2) {
                os--;
                is += 2;
            } else {
                os++;
                is -= 2;
            }
        }
    }

    public static void pattern11(int n) {
        int x = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(x + "\t");
                x++;
            }
            System.out.println();
        }
    }

    public static void pattern12(int n) {
        int a = 0, b = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(a + "\t");
                int c = a + b;
                a = b;
                b = c;
            }
            System.out.println();
        }
    }

    public static void pattern13(int n) {
        for (int i = 0; i <= n; i++) {
            int icj = 1;

            for (int j = 0; j <= i; j++) {

                System.out.print(icj + "\t");
                int nicj = (icj * (i - j)) / (j + 1);
                icj = nicj;

            }
            System.out.println();
        }
    }

    public static void pattern15(int n) {
        int sp = n / 2, st = 1, num = 1;

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= sp; j++) { // spaces
                System.out.print("\t");
            }

            int val = num;
            for (int j = 1; j <= st; j++) { // stars
                System.out.print(val + "\t");

                if (j <= st / 2)
                    val++;
                else
                    val--;

            }

            System.out.println();

            if (i <= n / 2) {
                sp--;
                st += 2;
                num++;
            } else {
                sp++;
                st -= 2;
                num--;
            }

        }
    }

    public static void pattern16(int n) {
        int sp = n * 2 - 3, st = 1;

        for (int i = 1; i <= n; i++) {

            int val = 1;
            for (int j = 1; j <= st; j++) {
                System.out.print(val + "\t");
                val++;
            }

            for (int j = 1; j <= sp; j++) {
                System.out.print("\t");
            }

            if (i == n) {
                val--;
                st--;
            }

            for (int j = 1; j <= st; j++) {
                val--;
                System.out.print(val + "\t");

            }

            System.out.println();

            sp -= 2;
            st++;
        }
    }


    public static void pattern17(int n) {
        int st = 1, sp = n / 2;

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= sp; j++) {
                if (i == n / 2 + 1)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }

            for (int j = 1; j <= st; j++) {
                System.out.print("*\t");
            }

            System.out.println();

            if (i <= n / 2)
                st++;
            else
                st--;
        }

    }

    public static void pattern19(int n) {

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                if (i == 1) {
                    if (j <= n / 2 + 1 || j == n)
                        System.out.print("*\t");
                    else
                        System.out.print("\t");
                } else if (i <= n / 2) {
                    if (j == n / 2 + 1 || j == n)
                        System.out.print("*\t");
                    else
                        System.out.print("\t");
                } else if (i == n / 2 + 1) {
                    System.out.print("*\t");
                } else if (i >= n / 2) {
                    if (j == n / 2 + 1 || j == 1)
                        System.out.print("*\t");
                    else
                        System.out.print("\t");
                }else if (i == n) {
                    if (j == 1 || j >= n / 2 + 1)
                        System.out.print("*\t");
                    else
                        System.out.print("\t");
                } 

            }
            System.out.println();
        }
    }

    public static void pattern20(int n) {

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                if (i <= n / 2) {
                    if (j == 1 || j == n)
                        System.out.print("*\t");
                    else
                        System.out.print("\t");
                } else {
                    if (i == j || i + j == n + 1 || j == 1 || j == n)
                        System.out.print("*\t");
                    else
                        System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    public static void pattern18(int n) {
        int st = n, os = 0, is = n / 2;

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= os; j++) {
                System.out.print("\t");
            }

            for (int j = 1; j <= st; j++) {
                if (i > 1 && i <= n / 2 && j > 1 && j < st)
                    System.out.print("\t");
                else
                    System.out.print("*\t");
            }

            if (i <= n / 2) {
                st -= 2;
                os++;
                is -= 2;
            } else {
                st += 2;
                os--;
            }
            System.out.println();
        }
    }

}
