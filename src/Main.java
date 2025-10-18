import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        System.out.println("Welcome to the Banking System");
        System.out.print("""
                Please select an option:
                1. Create Account
                2. Login
                3. Exit
                Your choice:\s"""); // \s is adding 1 whitespace " "
        Scanner scanner = new Scanner(System.in);
        boolean landing = true;
        while (landing) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    landing = false;
                    // createAccount();
                    break;
                }
                case 2: {
                    landing = false;
                    login();
                    break;
                }
                case 3: {
                    landing = false;
                    System.out.println("Exiting...\n" +
                            "Thank you for using the Banking System");
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.print("""
                            ===================================
                             Invalid choice, Please try again.
                            ===================================
                            Please select an option:
                            1. Create Account
                            2. Login
                            3. Exit
                            Your choice:\s""");
                    break;
                }
            }
        }
    }

    private static void login() {
        Scanner scanner = new Scanner(System.in);
        String account = null;
        int accountId = -1;
        boolean loggingIn = true;
        while (loggingIn) {
            account = getAccount(scanner); // get account number from user

            if (isAccountSyntaxErr(account)) { // validate account: String is "", Account number length > 5
                continue; // continue the loop
            }

            accountId = getAccountId(account); // get account index from accounts array
            if (accountId == -1) { // validate accountId
                System.out.println("Please try again..."); // print error message
                continue; // continue the loop
            }

            loggingIn = false; // exit the loop
        }

        System.out.println("Login successful\n" +
                "Welcome, " + account);

        boolean loggedIn = true;
        while (loggedIn) {
            System.out.print("""
                    Please select an option:
                    1. View Balance
                    2. Deposit
                    3. Withdraw
                    4. Transfer
                    5. Logout
                    Your choice:\s""");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    printBalance(accountId);
                    break;
                }
                case 2: {
                    Doposit(accountId);
                    return;
                }
                case 3: {
                    System.out.print("Enter amount to withdraw: ");
                    int amount = scanner.nextInt();
                    scanner.nextLine();
//                    withdraw(accountId, amount);
                    break;
                }
                case 4: {
                    System.out.print("Enter recipient account number: ");
                    String toAccount = scanner.nextLine();
                    int toAccountId = getAccountId(toAccount);
                    if (accountId == toAccountId) {
                        System.out.println("Cannot transfer to the same account");
                        break;
                    }
                    if (toAccountId == -1) {
                        System.out.println("Invalid recipient account");
                        break;
                    }
                    System.out.print("Enter amount to transfer: ");
                    int amount = scanner.nextInt();
                    if (balances[accountId] < amount) {
                        System.out.println("Insufficient balance");
                        break;
                    }
                    scanner.nextLine();
                    transfer(accountId, toAccountId, amount);
                    break;
                }
                case 5: {
                    loggedIn = false;
//                    logout();
                    break;
                }
                default: {
                    System.out.println("""
                    ===================================
                     Invalid choice, Please try again.
                    ===================================""");
                    break;
                }
            }
        }
    }

    private static String getAccount(Scanner scanner) {
        System.out.print("Enter account number (Must be 5 digit long): ");
        return scanner.nextLine();
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
    public static void Doposit(int accountId) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                
                Doposit System
                """);
        System.out.println("Your Total balance = " + getBalance(accountId) );
        System.out.println("How to much money your want to doposit = ");
        double dp = scanner.nextDouble();
        balances[accountId] += dp;
        System.out.println("");
        System.out.println("Total Amount Received = " + getBalance(accountId));
        System.out.println("");
    }

    // ระบบโอนเงิน
    public static void transfer(int fromAccountId, int toAccountId, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid transfer amount.");
            return;
        }

        if (balances[fromAccountId] < amount) {
            System.out.println("Insufficient balance.");
            return;
        }

        balances[fromAccountId] -= amount;
        balances[toAccountId] += amount;

        System.out.println("""
                
                Transfer Successful!
                """);
        System.out.println("Transferred " + amount + " from Account "
                + accounts[fromAccountId] + " to Account " + accounts[toAccountId]);
        System.out.println("Your new balance: " + balances[fromAccountId]);
        System.out.println("");
    }


    // แสดงผลยอดเงิน พร้อมบันทึกเวลา
    public static void printBalance(int accountId) {
        System.out.println("ยอดเงินคงเหลือ: " + getBalance(accountId) + " บาท ณ เวลา " + getCurrentTime());
    }

    private static String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}