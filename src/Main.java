import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AuthService auth = new AuthService();
        BankService bank = new BankService();

        String name, email, phone;

        System.out.println("===== BANK SYSTEM =====");

        // INPUT + VALIDATION
        while (true) {
            System.out.print("Enter Name: ");
            name = sc.nextLine();
            if (auth.isValidName(name)) break;
            System.out.println("Invalid Name!");
        }

        while (true) {
            System.out.print("Enter Email: ");
            email = sc.nextLine();
            if (auth.isValidEmail(email)) break;
            System.out.println("Invalid Email!");
        }

        while (true) {
            System.out.print("Enter Phone: ");
            phone = sc.nextLine();
            if (auth.isValidPhone(phone)) break;
            System.out.println("Invalid Phone!");
        }

        String user = auth.loginOrRegister(name, email, phone);

        System.out.println("\nWelcome " + user);

        int lastAcc = -1;

        while (true) {

            System.out.println("\n1.Create 2.Deposit 3.Withdraw 4.Transfer 5.Check 6.Exit");
            int c = sc.nextInt();

            if (c == 1) {
                System.out.print("Acc No: ");
                int a = sc.nextInt();
                System.out.print("Balance: ");
                double b = sc.nextDouble();
                bank.createAccount(a, email, b);
                lastAcc = a;
            }

            else if (c == 2) {
                System.out.print("Acc No: ");
                int a = sc.nextInt();
                System.out.print("Amount: ");
                double b = sc.nextDouble();
                bank.deposit(a, b);
                lastAcc = a;
            }

            else if (c == 3) {
                System.out.print("Acc No: ");
                int a = sc.nextInt();
                System.out.print("Amount: ");
                double b = sc.nextDouble();
                bank.withdraw(a, b);
                lastAcc = a;
            }

            else if (c == 4) {
                System.out.print("From: ");
                int f = sc.nextInt();
                System.out.print("To: ");
                int t = sc.nextInt();
                System.out.print("Amount: ");
                double b = sc.nextDouble();
                bank.transfer(f, t, b);
                lastAcc = f;
            }

            else if (c == 5) {
                System.out.print("Acc No: ");
                int a = sc.nextInt();
                bank.show(a);
                lastAcc = a;
            }

            else {
                System.out.println("\n===== FINAL SUMMARY =====");
                System.out.println("User: " + user);
                System.out.println("Email: " + email);
                System.out.println("Phone: " + phone);

                if (lastAcc != -1) {
                    bank.show(lastAcc);
                }

                System.out.println("Thank you!");
                break;
            }
        }
    }
}