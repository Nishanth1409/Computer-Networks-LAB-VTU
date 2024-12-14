// Program for error detecting code using CRC-CCITT (16-bit)
import java.util.Scanner;

class CRC {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter binary data: ");
        String data = scanner.nextLine();
        String generator = "10001000000100001"; // CRC-CCITT standard
        String codeword = data + "0".repeat(generator.length() - 1);

        String remainder = calculateRemainder(codeword, generator);
        System.out.println("Remainder: " + remainder);
        System.out.println("Transmitted Codeword: " + data + remainder);
    }

    static String calculateRemainder(String data, String generator) {
        int n = generator.length();
        String dividend = data.substring(0, n);
        for (int i = n; i <= data.length(); i++) {
            dividend = dividend.charAt(0) == '1'
                ? xor(dividend, generator) + (i < data.length() ? data.charAt(i) : "")
                : dividend.substring(1) + (i < data.length() ? data.charAt(i) : "");
        }
        return dividend.substring(1);
    }

    static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }
}
