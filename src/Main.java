import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    // Pattern: Array
    // accounts[0] = balances[0]

    private static String[] accounts = {
            "12345",
            "23456",
            "34567"
    };

    private static double[] balances = { // Default accounts balances array
            1000,
            2000,
            3000,
            4000
    };

    public static void main(String[] args) {
        Doposit();

    }

    private static int getAccountId(String account) { // get account index from accounts array
        if (isAccountSyntaxErr(account)) { // validate account: String is "", Account number length > 5
            System.out.println("Invalid account"); // print error message
            return -1; // return -1 for account not found
        }
        for (int i = 0; i < accounts.length; i++) { // loop through accounts array
            if (accounts[i].equals(account)) { // check if account exists
                return i; // return account index
            }
        }
        System.out.println("Account not found"); // print error message
        return -1; // return -1 for account not found
    }

    private static double getBalance(int accountId) {
        if (accountId == -1) { // validate accountId
            System.out.println("Invalid account ID"); // print error message
        }
        return balances[accountId]; // return balance of account from account index
    }

    private static boolean isAccountSyntaxErr(String account) {
        boolean isSyntaxErr = account.length() != 5;
        if (isSyntaxErr) {
            System.out.println("""
                Account number must be 5 digits long
                Please re-enter account number""");
        }
        return isSyntaxErr;
    }
    // ระบบฝากเงิน
    public static void Doposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                
                Doposit System
                """);
        System.out.println("Total balance = " + getBalance(0) );



    }

}