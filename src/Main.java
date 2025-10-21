import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    // Pattern: Array
    // accounts[0] = balances[0]

    // Default accounts array
    private static String[] accounts = {
            "12345",
            "23456",
            "34567"
    };

    // Default balances array
    private static double[] balances = { // Default accounts balances array
            1000,
            2000,
            3000,
            4000
    };

    // ระบบหน้าแรก: นนท์นิพัทธ์ ตั้งโรจนขจร 682110178
    public static void main(String[] args) {
        System.out.println("Welcome to the Banking System");
        System.out.print("""
                Please select an option:
                1. Login
                2. Exit
                Your choice:\s"""); // \s is adding 1 whitespace " "
        Scanner scanner = new Scanner(System.in);
        boolean landing = true;
        while (landing) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    landing = false;
                    login();
                    break;
                }
                case 2: {
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

    // ระบบล็อกอิน: นนท์นิพัทธ์ ตั้งโรจนขจร 682110178
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
        String prompt = """
                    Please select an option:
                    1. View Balance
                    2. Deposit
                    3. Withdraw
                    4. Transfer
                    5. Show Banking Tip
                    6. Logout
                    Your choice:\s""";
        System.out.print(prompt);
        int choice = scanner.nextInt();
        while (loggedIn) {
            switch (choice) {
                case 1: {
                    printBalance(accountId);
                    break;
                }
                case 2: {
                    Doposit(accountId);
                    break;
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
                        continue;
                    }
                    if (toAccountId == -1) {
                        System.out.println("Invalid recipient account");
                        continue;
                    }
                    System.out.print("Enter amount to transfer: ");
                    int amount = scanner.nextInt();
                    if (balances[accountId] < amount) {
                        System.out.println("Insufficient balance");
                        continue;
                    }
                    scanner.nextLine();
                    transfer(accountId, toAccountId, amount);
                    break;
                }
                case 5: {
                    showTip();
                    break;
                }
                case 6: {
                    loggedIn = false;
                    continue;
                }
                default: {
                    System.out.println("""
                    ===================================
                     Invalid choice, Please try again.
                    ===================================""");
                    break;
                }
            }
            System.out.print(prompt);
            choice = scanner.nextInt();
            scanner.nextLine();
        }
    }

    // รับเลขที่บัญชีจากผู้ใช้: นนท์นิพัทธ์ ตั้งโรจนขจร 682110178
    private static String getAccount(Scanner scanner) {
        System.out.print("Enter account number (Must be 5 digit long): ");
        return scanner.nextLine();
    }


    // ดึงเลขที่บัญชี: นนท์นิพัทธ์ ตั้งโรจนขจร 682110178
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

    // ดึงยอดเงินคงเหลือของบัญชี: นนท์นิพัทธ์ ตั้งโรจนขจร 682110178
    private static double getBalance(int accountId) {
        if (accountId == -1) { // validate accountId
            System.out.println("Invalid account ID"); // print error message
        }
        return balances[accountId]; // return balance of account from account index
    }

    // เช็คความถูกต้องของเลขที่บัญชี: นนท์นิพัทธ์ ตั้งโรจนขจร 682110178
    private static boolean isAccountSyntaxErr(String account) {
        boolean isSyntaxErr = account.length() != 5;
        if (isSyntaxErr) {
            System.out.println("""
                Account number must be 5 digits long
                Please re-enter account number""");
        }
        return isSyntaxErr;
    }

    // ระบบฝากเงิน: ศุภวิชญ์ อ้ายเสาร์ 682110196
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
            System.out.println();
            System.out.println("Total Amount Received = " + getBalance(accountId));
            System.out.println();
            Back(accountId);


        } else if (dp == 0) {
            System.out.println();
            System.out.println("==== Enter again please ====");
            Doposit(accountId);
        } else {
            System.out.println();
            System.out.println("==== Enter again please ====");
            Doposit(accountId);
        }
    }

    // ศุภวิชญ์ อ้ายเสาร์ 682110196
    public  static void Back (int accountId) {
        Scanner scanner = new Scanner(System.in);
        String backs = "";
        System.out.println("==== Would you like to deposit money again ====");
        System.out.println(" Yes / No ");
        backs = scanner.nextLine();

        if (backs.equalsIgnoreCase("Yes")) {
            Doposit(accountId);
        } else if (backs.equalsIgnoreCase("No")) {
            System.out.println("Back to your choice");
            System.out.println("===============================");
            login();
        }
    }

    // ระบบโอนเงิน: วริธทภัทร์ ธรรมธิ 682110191
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
        System.out.println();
    }

    // วริธทภัทร์ ธรรมธิ 682110191
    public static void showTip() {
        String[] tips = {
                "💡 Tip: Always check your balance before making transfers.",
                "💡 Tip: Saving 10% of your income every month builds stability.",
                "💡 Tip: Keep your account number private for security.",
                "💡 Tip: Review your transactions regularly to prevent fraud.",
                "💡 Tip: Withdraw only what you need to avoid overspending."
        };

        int randomIndex = (int)(Math.random() * tips.length);
        System.out.println("\n" + tips[randomIndex] + "\n");
    }

    // ระบบถอนเงิน: สายกลาง จะวะนะ 682110198
    static void withdraw(int acountId) {
        Scanner scanner = new Scanner(System.in);
        double balance = getBalance(acountId);
        double amount;
        System.out.println("\t--------------------------------------------");
        System.out.println("\t\t\t\tBalance is " + balance + "THB");
        do {
            System.out.println();
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
        System.out.println();
        System.out.println("\t\t\tThe Remaining Amount is " + remaining + " THB");
        System.out.println("\t----------------------------------------------");
        getBalance(acountId);
    }

    // ยืนยันการถอนเงิน: สายกลาง จะวะนะ 682110198
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

    // แสดงผลยอดเงิน พร้อมบันทึกเวลา: กันต์ธีภพ ปันพรม 682110160
    public static void printBalance(int accountId) {
        System.out.println("ยอดเงินคงเหลือ: " + getBalance(accountId) + " บาท ณ เวลา " + getCurrentTime());
    }

    // ดึงเวลาปัจจุบัน: กันต์ธีภพ ปันพรม 682110160
    private static String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}