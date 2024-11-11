import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }

//        int fee = scn.nextInt();
        System.out.println(getBuySellTwoTransactionAllowed(arr));
    }


    private static int getBuySellTwoTransactionAllowed(int[] arr) {
        int lsf = arr[0];
        int[] dpl = new int[arr.length];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < lsf) {
                lsf = arr[i];
            }
            int pist = arr[i] - lsf;
            if (pist > dpl[i - 1]) {
                dpl[i] = pist;
            } else {
                dpl[i] = dpl[i - 1];
            }

        }
        int msf = arr[arr.length - 1];
        int[] dpr = new int[arr.length];

        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > msf) {
                msf = arr[i];
            }
            int mpibt = msf - arr[i];
            if (mpibt > dpr[i + 1]) {
                dpr[i] = mpibt;
            } else {
                dpr[i] = dpr[i + 1];            }
        }
        int rv = 0;
        for (int i = 0; i < arr.length; i++) {
            int add = dpr[i] + dpl[i];
            rv = Math.max(add, rv);
        }
        return rv;
    }


    private static int getBuySellWithCoolingPeriod(int[] arr) {
        int obsp = -arr[0];
        int ossp = 0;
        int ocsp = 0;
        for (int i = 1; i < arr.length; i++) {
            int nbsp = 0;
            int nssp = 0;
            int ncsp = 0;
            if (ocsp - arr[i] > obsp) {
                nbsp = ocsp - arr[i];
            } else {
                nbsp = obsp;
            }

            if (obsp + arr[i] > ossp) {
                nssp = obsp + arr[i];
            } else {
                nssp = ossp;
            }

            if (ossp > ocsp) {
                ncsp = ossp;
            } else {
                ncsp = ocsp;
            }
            obsp = nbsp;
            ossp = nssp;
            ocsp = ncsp;
        }
        return ossp;
    }

    private static int getBuySellInfiniteTransactionsWithFees(int[] prices, int fee) {
        int obsp = -prices[0];
        int ossp = 0;
        for (int i = 0; i < prices.length; i++) {
            int nbsp = 0;
            int nssp = 0;
            if (ossp - prices[i] > obsp) {
                nbsp = ossp - prices[i];
            } else {
                nbsp = obsp;
            }
            if (obsp + prices[i] - fee > ossp) {
                nssp = obsp + prices[i] - fee;
            } else {
                nssp = obsp;
            }
            obsp = nbsp;
            ossp = nssp;
        }
        return ossp;
    }

    private static int getBuySellInfiniteTransactions(int[] prices) {
        int bd = 0;
        int sd = 0;
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] >= prices[i - 1]) {
                sd++;
            }
            profit += prices[sd] - prices[bd];
            bd = sd = i;
        }
        profit += prices[sd] - prices[bd];
        return profit;
    }

    private static int getBuySellOneTransaction(int[] prices) {
        int op = 0;
        int lsf = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < lsf) {
                lsf = prices[i];
            }
            int pist = prices[i] - lsf;
            if (pist > op) {
                op = pist;
            }
        }
        return op;
    }


    public static int getWaysSubset(int n, int k) {
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    dp[i][j] = 1;
                } else if (j < i) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + (dp[i][j - 1] * i);
                }
            }
        }
        return dp[k][n];
    }

    public static int friendsPairingProblem(int n) {
        int im2 = 1;
        int im1 = 2;
        for (int i = 3; i <= n; i++) {
            int temp = im1 + (im2 * (i - 1));
            im2 = im1;
            im1 = temp;
        }
        return im1;
    }

    public static int getTilingWays(int n, int m) {
        int dp[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i < m) {
                dp[i] = 1;
            } else if (i == m) {
                dp[i] = 2;
            } else {
                dp[i] = dp[i - 1] + dp[i - m];
            }
        }
        return dp[n];
    }

    public static int getTilingWays(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        int a = 1;
        int b = 2;
        for (int i = 3; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        System.out.println(b);
        return dp[n];
    }

    public static int minPathSum(int[][] grid) {
        int dp[][] = new int[grid.length][grid[0].length];
        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = dp[0].length - 1; j >= 0; j--) {
                if (i == dp.length - 1 && j == dp[0].length - 1) {
                    dp[i][j] = grid[i][j];
                } else if (i == dp.length - 1) {
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                } else if (j == dp[0].length - 1) {
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                } else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        for (int[] x : dp) {
            for (int y : x) {
                System.out.print(y + " ");
            }
            System.out.println();

        }
        return dp[0][0];
    }

    public static List<List<Integer>> generate(int numRows) {

        if (numRows == 1) {
            ArrayList<Integer> list = new ArrayList<>();
            ArrayList<List<Integer>> lists = new ArrayList<>();
            list.add(1);
            lists.add(list);
            return lists;
        }
        List<List<Integer>> rv = generate(numRows - 1);
        List<Integer> integers = rv.get(rv.size() - 1);
        ArrayList<Integer> res = new ArrayList<>();

        int j = 1;
        for (int i = 1; i <= numRows; i++) {
            if (i == 1 || i == numRows) {
                res.add(1);
            } else {
                Integer a = integers.get(j);
                Integer b = integers.get(j - 1);
                res.add(a + b);
                j++;
            }

        }
        rv.add(res);
        return rv;
    }

    public static int getPaintFence(int n, int k) {
        int same = k;
        int diff = k * (k - 1);
        int total = same + diff;
        for (int i = 3; i <= n; i++) {
            same = diff;
            diff = total * (k - 1);
            total = same + diff;
        }
        return total;
    }

    public static int getTotalStrings(String s) {
        int a, b, c;
        a = b = c = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                a = a + a + 1;
            } else if (s.charAt(i) == 'b') {
                b = b + b + a;
            } else if (s.charAt(i) == 'c') {
                c = c + c + b;
            }
        }
        return c;
    }

    public static int getTotalDecodes(String s) {
        int dp[] = new int[s.length()];
        if (s.charAt(0) != '0') {
            dp[0] = 1;
        }
        for (int i = 1; i < dp.length; i++) {
            char ch = s.charAt(i);
            char chim1 = s.charAt(i - 1);
            if (ch == '0' && chim1 == '0') {
                dp[i] = 0;
            } else if (ch == '0' && chim1 != '0') {
                if (chim1 == '1' || chim1 == '2') {
                    dp[i] = i >= 2 ? dp[i - 2] : 1;
                } else {
                    dp[i] = 0;
                }
            } else if (ch != '0' && chim1 == '0') {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = dp[i - 1];
                if (Integer.parseInt(s.substring(i - 1, i + 1)) <= 26) {
                    dp[i] += i >= 2 ? dp[i - 2] : 1;
                }
            }
        }
        for (int x : dp) {
            System.out.println(x);
        }
        return dp[dp.length - 1];
    }

    public static int findIntegers(int n) {
        int count = 2;
        for (int i = 2; i <= n; i++) {
            if (!isConsecutiveones(Integer.toBinaryString(i))) {
                count++;
            }
        }
        return count;
    }


    private static boolean isConsecutiveones(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == 1 && s.charAt(i - 1) == 1) {
                return true;
            }
        }
        return false;
    }

    public static int totalStr(int n) {
        int c0 = 1;
        int c1 = 1;
        for (int i = 2; i <= n; i++) {
            int temp0 = c1;
            int temp1 = c1 + c0;
            c0 = temp0;
            c1 = temp1;
        }
        return c0 + c1;
    }

    public static int unboundedKnapsack(int[] wt, int[] val, int capacity) {
        int[] dp = new int[capacity + 1];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < wt.length; j++) {
                if (i >= wt[j]) {
                    int rc = i - wt[j];
                    int rcv = dp[rc];
                    int tv = rcv + val[j];
                    if (tv > max) {
                        max = tv;
                    }
                }
            }
            dp[i] = max;
        }
        return dp[capacity];
    }

    public static int knapsack(int[] weights, int[] value, int target) {
        int[][] dp = new int[value.length + 1][target + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                int w = weights[i - 1];
                int v = value[i - 1];
                int viim1 = dp[i - 1][j];
                dp[i][j] = viim1;
                if (j >= w) {
                    int viim1jmw = dp[i - 1][j - w];
                    if (viim1jmw + v > viim1) {
                        dp[i][j] = viim1jmw + v;
                    }
                }
            }
        }
        return dp[dp.length - 1][target];
    }

    public static int coinChange(int[] coins, int amount) {
        Integer[] dp = new Integer[amount + 1];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (coin <= i) {
                    min = Math.min(dp[i - coin], min);
                }

            }
            if (min != Integer.MAX_VALUE) {
                dp[i] = min + 1;
            }
        }
        if (dp[amount] == null) {
            return -1;
        } else return dp[amount];
    }

    public static int coinChangePermutation(int[] coins, int amount) {
        int dp[] = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                dp[i] += i - coin >= 0 ? dp[i - coin] : 0;
            }
        }
        return dp[dp.length - 1];
    }

    public static int coinChangeCombination(int[] arr, int tar) {
        int[] dp = new int[tar + 1];
        dp[0] = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr[i]; j < dp.length; j++) {
                dp[j] += dp[j - arr[i]];
            }
        }
        return dp[dp.length - 1];
    }

    public static boolean subsetPresent(int[] arr, int target) {
        boolean dp[][] = new boolean[arr.length + 1][target + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = false;
                } else if (j == 0) {
                    dp[i][j] = true;
                } else {
                   /* if (dp[i - 1][j] == true) {
                        dp[i][j] = true;

                    } else if (j - arr[i - 1] >= 0) {
                        if (dp[i - 1][j - arr[i - 1]] == true) {
                            dp[i][j] = true;
                        }
                    }*/
                    if (j - arr[i - 1] >= 0) {
                        dp[i][j] = dp[i - 1][j] | dp[i - 1][j - arr[i - 1]];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }

            }
        }
        for (boolean[] x : dp) {
            for (boolean y : x) {
                System.out.print((y ? 1 : 0) + " ");
            }
            System.out.println();
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static int getMaxGold(int[][] arr) {
        int[][] dp = new int[arr.length][arr[0].length];
        for (int j = dp[0].length - 1; j >= 0; j--) {
            for (int i = dp.length - 1; i >= 0; i--) {
                if (j == dp[0].length - 1) {
                    dp[i][j] = arr[i][j];
                } else if (i == dp.length - 1) {
                    dp[i][j] = Math.max(dp[i - 1][j + 1], dp[i][j + 1]) + arr[i][j];
                } else if (i == 0) {
                    dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j + 1]) + arr[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i][j + 1], Math.max(dp[i - 1][j + 1], dp[i + 1][j + 1])) + arr[i][j];
                }

            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i][0]);
        }
        return max;
    }

    public static int getMinCost(int[][] arr) {
        int[][] dp = new int[arr.length][arr[0].length];
        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = dp[0].length; j >= 0; j--) {
                if (i == dp.length - 1 && j == dp[0].length - 1) {
                    dp[i][j] = arr[i][j];
                } else if (i == dp.length - 1) {
                    dp[i][j] = dp[i][j + 1] + arr[i][j];
                } else if (j == dp[0].length - 1) {
                    dp[i][j] = dp[i + 1][j] + arr[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j]) + arr[i][j];
                }
            }
        }
        return dp[0][0];
    }

    //12 5 9 3 2 1 0 2 3 3 1 0 0

    public static Integer minJump(int n, int[] arr) {
        Integer[] dp = new Integer[n + 1];
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] > 0) {
                int min = Integer.MAX_VALUE;
                for (int j = 1; j <= arr[i] && i + j <= n; j++) {
                    if (dp[i + j] != null) {
                        min = Math.min(min, dp[i + j]);
                    }
                }
                if (min != Integer.MAX_VALUE) {
                    dp[i] = min + 1;
                }
            }
        }
        for (Integer x : dp) {
            System.out.print(x + ", ");
        }
        return dp[0];
    }

    public static int getCountPathJump(int[] arr, int n) {

        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= arr[i]; j++) {
                if (i + j <= n) {
                    dp[i] += dp[i + j];
                }
            }
        }
        return dp[0];
    }

    public static int countPathTabulation(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            if (i == 1) {
                dp[i] = dp[i - 1];
            } else if (i == 2) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] += dp[i - 1] + dp[i - 2] + dp[i - 3];
            }
        }
        return dp[n];
    }

    public static int countPathMemoized(int n, int[] mem) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }
        if (mem[n] > 0) {
            return mem[n];
        }

        int nm1 = countPathMemoized(n - 1, mem);
        int nm2 = countPathMemoized(n - 2, mem);
        int nm3 = countPathMemoized(n - 3, mem);
        int rv = nm1 + nm2 + nm3;
        mem[n] = rv;
        return rv;
    }

    public static int fibMemoized(int n, int[] qb) {
        if (n == 0 || n == 1) {
            return n;
        }
        if (qb[n] != 0) {
            return qb[n];
        }
        int result = fibMemoized(n - 1, qb) + fibMemoized(n - 2, qb);
        qb[n] = result;
        return result;
    }

    public static int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static void floodfill(int r, int c, int maze[][], String ans, boolean[][] visited) {
        if (r == maze.length && c == maze[0].length) {
            System.out.println(ans);
            return;
        }
        if (r < 0 || c < 0 || c > maze[0].length || r > maze.length || maze[r][c] == 1) {
            return;
        }
        visited[r][c] = true;
        // top
        floodfill(r - 1, c, maze, ans + 't', visited);
        // left, visited
        floodfill(r, c - 1, maze, ans + 'l', visited);
        // down, visited
        floodfill(r + 1, c, maze, ans + 'd', visited);
        // right, visited
        floodfill(r, c + 1, maze, ans + 'r', visited);
        visited[r][c] = false;

    }

    public static void printEncoding(String ques, String ans) {
        if (ques.length() == 0) {
            System.out.println(ans);
            return;
        }
        if (ques.charAt(0) == '0') {
            return;
        }
        String fch = ques.substring(0, 1);

        int fchv = Integer.parseInt(fch);
        char a = (char) ('a' + fchv - 1);
        printEncoding(ques.substring(1), ans + a);
        if (ques.length() > 1) {
            String f2ch = ques.substring(0, 2);
            int f2chv = Integer.parseInt(f2ch);
            char a2 = (char) ('a' + f2chv - 1);
            if (f2chv <= 26) {
                printEncoding(ques.substring(2), ans + a2);
            }
        }
    }

    public static void printMazePathWithJump(int sr, int sc, int dr, int dc, String ans) {
        if (sc == dc && sr == dr) {
            System.out.println(ans);
            return;
        }
        for (int jump = 1; jump <= dc - sc; jump++) {
            printMazePathWithJump(sr, sc + jump, dr, dc, ans + "h" + jump);
        }
        for (int jump = 1; jump <= dr - sr; jump++) {
            printMazePathWithJump(sr + jump, sc, dr, dc, ans + "v" + jump);
        }
        for (int jump = 1; jump <= dc - sc && jump <= dr - sr; jump++) {
            printMazePathWithJump(sr + jump, sc + jump, dr, dc, ans + "d" + jump);
        }
    }

    public static void printMazePath(int sc, int sr, int dr, int dc, String ans) {
        if (sr == dr && sc == dc) {
            System.out.println(ans);
            return;
        }
        if (sc < dc) {
            printMazePath(sc + 1, sr, dr, dc, ans + "h");
        }
        if (sr < dr) {
            printMazePath(sc, sr + 1, dr, dc, ans + "v");
        }

    }

    public static void printStairPath(int n, String path) {
        if (n == 0) {
            System.out.println(path);
            return;
        }
        if (n < 0) {
            return;
        }
        printStairPath(n - 1, path + 1);
        printStairPath(n - 2, path + 2);
        printStairPath(n - 3, path + 3);
    }

    public static void printKeypadCombination(String ques, String ans) {
        if (ques.length() == 0) {
            System.out.print(ans + " ");
            return;
        }
        char ch = ques.charAt(0);
        String letters = CODES[ch - '0'];
        for (int i = 0; i < letters.length(); i++) {
            printKeypadCombination(ques.substring(1), ans + letters.charAt(i));
        }
    }

    public static void printSubSequence(String ques, String ans) {
        if (ques.length() == 0) {
            System.out.println(ans);
            return;
        }
        printSubSequence(ques.substring(1), ans + ques.charAt(0));
        printSubSequence(ques.substring(1), ans);

    }

    public static ArrayList<String> mazePath(int sr, int sc, int dr, int dc) {
        if (sr == sc && dr == dc) {
            ArrayList<String> brv = new ArrayList<>();
            brv.add("");
            return brv;
        }
        ArrayList<String> hp = new ArrayList<>();
        ArrayList<String> vp = new ArrayList<>();
        ArrayList<String> dp = new ArrayList<>();

        ArrayList<String> rv = new ArrayList<>();

        for (int jump = 1; jump <= dc - sc; jump++) {
            hp = mazePath(sr, sc + jump, dr, dc);
            for (String p : hp) {
                rv.add("h" + jump + hp);
            }
        }


        for (int jump = 1; jump <= dr - sr; jump++) {
            vp = mazePath(sr + jump, sc, dr, dc);
            for (String p : vp) {
                rv.add("v" + jump + hp);
            }
        }


        for (int jump = 1; jump <= dc - sc && jump <= dr - sr; jump++) {
            dp = mazePath(sr + jump, sc + jump, dr, dc);
            for (String p : dp) {
                rv.add("d" + jump + dp);
            }
        }

        return rv;
    }


    public static ArrayList<String> getMazePath(int sr, int sc, int dr, int dc) {
        if (sr == dr && sc == dc) {
            ArrayList<String> brv = new ArrayList<>();
            brv.add("");
            return brv;
        }
        ArrayList<String> hmp = new ArrayList<>();
        ArrayList<String> vmp = new ArrayList<>();
        if (sc < dc) {
            hmp = getMazePath(sr, sc + 1, dr, dc);
        }
        if (sr < dr) {
            vmp = getMazePath(sr + 1, sc, dr, dc);
        }
        ArrayList<String> rv = new ArrayList<>();
        for (String mp : hmp) {
            rv.add('h' + mp);
        }
        for (String mp : vmp) {
            rv.add('v' + mp);
        }
        return rv;

    }

    public static ArrayList<String> getStairPath(int n) {
        if (n == 0) {
            ArrayList<String> brv = new ArrayList<>();
            brv.add("");
            return brv;
        }
        if (n < 0) {
            return new ArrayList<>();
        }
        ArrayList<String> spnm1 = getStairPath(n - 1);
        ArrayList<String> spnm2 = getStairPath(n - 2);
        ArrayList<String> spnm3 = getStairPath(n - 3);
        ArrayList<String> rv = new ArrayList<>();
        for (String path : spnm1) {
            rv.add("1" + path);
        }
        for (String path : spnm2) {
            rv.add("2" + path);
        }
        for (String path : spnm3) {
            rv.add("3" + path);
        }
        return rv;
    }

    private static final String[] CODES = {".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz"};

    public static ArrayList<String> getKpcIterative(String str) {
        ArrayList<String> rv = new ArrayList<>();
        rv.add("");
        for (int i = str.length() - 1; i >= 0; i--) {
            ArrayList<String> temp = new ArrayList<>();
            String code = CODES[str.charAt(i) - '0'];
            for (int j = 0; j < code.length(); j++) {
                for (String word : rv) {
                    temp.add(code.charAt(j) + word);
                }
            }
            rv = temp;
        }
        return rv;
    }

    public static ArrayList<String> getKPC(String str) {
        if (str.length() == 0) {
            ArrayList<String> brv = new ArrayList<>();
            brv.add("");
            return brv;
        }
        char numString = str.charAt(0);
        ArrayList<String> kpcrl = getKPC(str.substring(1));
        ArrayList<String> rv = new ArrayList<>();

        String letters = CODES[numString - '0'];
        for (int i = 0; i < letters.length(); i++) {
            char l = letters.charAt(i);
            for (String word : kpcrl) {
                rv.add(l + word);
            }
        }
        return rv;
    }

    public static ArrayList<String> getSubString(String str) {
        if (str.length() == 0) {
            ArrayList<String> rv = new ArrayList<>();
            rv.add("");
            return rv;
        }
        ArrayList<String> ssiros = getSubString(str.substring(1));
        ArrayList<String> rv = new ArrayList<>();
        rv.addAll(ssiros);
        for (String s : ssiros) {
            rv.add(str.charAt(0) + s);
        }
        return rv;
    }

    public static int fo(int[] a, int key, int idx) {
        if (idx == a.length) {
            return -1;
        }

        int foisa = fo(a, key, idx + 1);
        if (a[idx] == idx) {
            return idx;
        } else return foisa;
    }

    public static int getMax(int[] a, int idx) {
        if (idx == a.length - 1) {
            return a[idx];
        }
        int maxisa = getMax(a, idx + 1);
        int max = Math.max(maxisa, a[idx]);
        return max;
    }

    public static void pdi(int n) {
        if (n <= 0) {
            return;
        }
        System.out.println(n);
        pdi(n - 1);
        System.out.println(n);
    }

    public static int factorial(int n) {
        int rv = 1;
        for (int i = n; i >= 1; i--) {
            rv *= i;
        }
        return rv;
    }

    public static char getUpperCharacter(char lc) {
        return (char) ('A' + lc - 'a');
    }

    public static char getLowerCharacter(char uc) {
        return (char) ('a' + uc - 'A');
    }
}

