import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Input: s = "babad"
        //Output: "bab"
        //Note: "aba" is also a valid answer.

        //Input: s = "cbbd"
        //Output: "bb"

        //Input: s = "a"
        //Output: "a"

        //Input: s = "ac"
        //Output: "a"
        List<String> tests = Arrays.asList("ccc",
                "babad",
                "cbbd",
                "a",
                "ac",
                "sdhffkjabbadskssl",
                "ibvjkmpyzsifuxcabqqpahjdeuzaybqsrsmbfplxycsafogotliyvhxjtkrbzqxlyfwujzhkdafhebvsdhkkdbhlhmaoxmbkqiwiusngkbdhlvxdyvnjrzvxmukvdfobzlmvnbnilnsyrgoygfdzjlymhprcpxsnxpcafctikxxybcusgjwmfklkffehbvlhvxfiddznwumxosomfbgxoruoqrhezgsgidgcfzbtdftjxeahriirqgxbhicoxavquhbkaomrroghdnfkknyigsluqebaqrtcwgmlnvmxoagisdmsokeznjsnwpxygjjptvyjjkbmkxvlivinmpnpxgmmorkasebngirckqcawgevljplkkgextudqaodwqmfljljhrujoerycoojwwgtklypicgkyaboqjfivbeqdlonxeidgxsyzugkntoevwfuxovazcyayvwbcqswzhytlmtmrtwpikgacnpkbwgfmpavzyjoxughwhvlsxsgttbcyrlkaarngeoaldsdtjncivhcfsaohmdhgbwkuemcembmlwbwquxfaiukoqvzmgoeppieztdacvwngbkcxknbytvztodbfnjhbtwpjlzuajnlzfmmujhcggpdcwdquutdiubgcvnxvgspmfumeqrofewynizvynavjzkbpkuxxvkjujectdyfwygnfsukvzflcuxxzvxzravzznpxttduajhbsyiywpqunnarabcroljwcbdydagachbobkcvudkoddldaucwruobfylfhyvjuynjrosxczgjwudpxaqwnboxgxybnngxxhibesiaxkicinikzzmonftqkcudlzfzutplbycejmkpxcygsafzkgudy");


/*        long startBrute = System.currentTimeMillis();
        for (String test : tests) {
            System.out.println(longestPalindromeBrute(test));
        }
        long endBrute = System.currentTimeMillis();
        System.out.println("work time for brute: " + (double) (endBrute - startBrute));*/

        long startDynamic = System.currentTimeMillis();
        for (String test : tests) {
            System.out.println(longestPalindromeDynamic(test));
        }
        long endDynamic = System.currentTimeMillis();
        System.out.println("work time for dynamic: " + (double) (endDynamic - startDynamic));
    }

    public static String longestPalindromeDynamic(String s) {
        int length = s.length();
        int[][] memo = new int[length][length];

        for (int i = 0; i < length; i++) {
            memo[i][i] = 1;
        }

        if (length > 1) {
            for (int row = 0; row < length - 1; row++) {
                int col = row + 1;
                if (equalSymbols(s, row, col)) {
                    memo[row][col] = 1;
                }
            }

            if (length > 2) {
                for (int pair = 2; pair < length; pair++) {
                    for (int row = 0; row < length - pair; row++) {
                        int col = row + pair;

                        if (equalSymbols(s, row, col) && memo[row + 1][col - 1] == 1) {
                            memo[row][col] = 1;
                        }
                    }
                }
            }
        }

        return findMaxLengthPalindrome(s, length, memo);
    }

    public static String longestPalindromeBrute(String s) {
        int length = s.length();

        List<String> palindromes = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                String substring = s.substring(i, j + 1);
                if (isPalindrome(substring)) {
                    palindromes.add(substring);
                }
            }
        }

        String maxLengthPalindrome = "";
        for (String palindrome : palindromes) {
            if (palindrome.length() > maxLengthPalindrome.length()) {
                maxLengthPalindrome = palindrome;
            }
        }

        return maxLengthPalindrome;
    }

    private static String findMaxLengthPalindrome(String s, int length, int[][] memo) {
        String maxLengthPalindrome = "";
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (memo[i][j] == 1 && s.substring(i, j + 1).length() > maxLengthPalindrome.length()) {
                    maxLengthPalindrome = s.substring(i, j + 1);
                }
            }
        }
        return maxLengthPalindrome;
    }

    public static boolean equalSymbols(String s, int start, int end) {
        return s.charAt(start) == s.charAt(end);
    }

    public static boolean isPalindrome(String subString) {
        if (subString.length() == 1) {
            return true;
        }

        StringBuilder checkString = new StringBuilder();
        for (int i = subString.length() - 1; i >= 0; i--) {
            checkString.append(subString.charAt(i));
        }
        return checkString.toString().equals(subString);
    }
}
