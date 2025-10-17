import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
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
                    System.out.println("Your balance is: " + getBalance(accountId));
                    break;
                }
                case 2: {
                    Doposit(accountId);
                    return;
                }
                case 3: {
                    withdraw(accountId);
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
                
                ==== Doposit System ====
                """);
        System.out.println("Your Total balance = " + getBalance(accountId));
        System.out.println("How to much money your want to doposit = ");
        double dp = scanner.nextDouble();
        // เชคความถูกต้อง
        if (dp > 0) {
            balances[accountId] += dp;
            System.out.println("");
            System.out.println("Total Amount Received = " + getBalance(accountId));
            System.out.println("");
            Back(accountId);

        } else if (dp == 0) {
            System.out.println("");
            System.out.println("==== Enter again please ====");
            Doposit(accountId);
        } else {
            System.out.println("");
            System.out.println("==== Enter again please ====");
            Doposit(accountId);
        }
    static void withdraw(int acountId) {
        Scanner scanner = new Scanner(System.in);
        double balance = getBalance(acountId);
        double amount;
        System.out.println("\t--------------------------------------------");
        System.out.println("\t\t\t\tBalance is " + balance + "THB");
        do {
            System.out.println("");
            System.out.print("\t\t\tEnter amount to withdraw: ");
            amount = scanner.nextDouble();

            if (amount <= 0) {
                System.out.println("\t----------------------------------------------");
                System.out.println("\t\t=== !!! Enter again please !!! ===");
            } else if (amount > balance) {
                System.out.println("\t----------------------------------------------");
                System.out.println("\t\t=== !!! Not enough balance !!! ===");
            }
        } while (amount <= 0 || amount > balance);
        if (confirmWithdraw(amount)) {
            System.out.println("\t\t\tWithdrawal successful!");
            System.out.println("\t----------------------------------------------");
        } else {
            System.out.println("\tTransaction canceled.");
            System.out.println("\t----------------------------------------------");

            return;
        }

        balances[acountId] -= amount;
        double remaining = balance - amount;
        System.out.println("\t----------------------------------------------");
        System.out.println("\t\t\t\tYou withdraw " + amount + "THB");
        System.out.println("");
        System.out.println("\t\t\tThe Remaining Amount is " + remaining + " THB");
        System.out.println("\t----------------------------------------------");
        getBalance(acountId);
    }
//method ย้อนกลับ
    public static void Back(int accountId) {
        Scanner scanner = new Scanner(System.in);
        String inputsting = "";
        //รับค่า
        System.out.println("=== Do you want to deposit again (yes/no) ===");
        inputsting = scanner.next();
        if (inputsting.equals("yes")) {
            Doposit(accountId);
        } else {
            System.out.println("ฺ==== Back to LoginID ====");
            System.out.println("");

            login();
            return;


    static boolean confirmWithdraw(double amount) {
        Scanner scanner = new Scanner(System.in);
        char confirm;

        while (true) {
            System.out.print("Confirm withdraw " + amount + " THB? (Y/N): ");
            confirm = scanner.next().charAt(0);

            if (confirm == 'Y' || confirm == 'y') {
                return true;
            } else if (confirm == 'N' || confirm == 'n') {
                return false;
            } else {
                System.out.println("Invalid input! Please enter Y or N.");
            }
        }
    }
}