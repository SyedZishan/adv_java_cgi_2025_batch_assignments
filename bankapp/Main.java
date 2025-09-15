package bankapp;

import bankapp.model.Account;
import bankapp.service.AccountManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager();
        Scanner sc = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("=== Bank Account Management ===");
            System.out.println("1. Add Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Display Account Details");
            System.out.println("5. Calculate Interest");
            System.out.println("6. Get Account Count");
            System.out.println("7. Get Balance");
            System.out.println("8. List All Accounts");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception ignored) {}

            switch (choice) {
                case 1 -> {
                    try {
                        System.out.print("Account Number: ");
                        long accNo = Long.parseLong(sc.nextLine().trim());
                        System.out.print("Holder Name: ");
                        String name = sc.nextLine();
                        System.out.print("Opening Balance: ");
                        double bal = Double.parseDouble(sc.nextLine().trim());
                        Account acc = accountManager.addAccount(accNo, name, bal);
                        System.out.println("Created: " + acc);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 2 -> {
                    try {
                        System.out.print("Account Number: ");
                        long accNo = Long.parseLong(sc.nextLine().trim());
                        System.out.print("Amount to deposit: ");
                        double amt = Double.parseDouble(sc.nextLine().trim());
                        boolean ok = accountManager.deposit(accNo, amt);
                        System.out.println(ok ? "Deposit successful." : "Account not found.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 3 -> {
                    try {
                        System.out.print("Account Number: ");
                        long accNo = Long.parseLong(sc.nextLine().trim());
                        System.out.print("Amount to withdraw: ");
                        double amt = Double.parseDouble(sc.nextLine().trim());
                        boolean ok = accountManager.withdraw(accNo, amt);
                        System.out.println(ok ? "Withdrawal successful." : "Failed (insufficient funds or account not found).");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        System.out.print("Account Number: ");
                        long accNo = Long.parseLong(sc.nextLine().trim());
                        System.out.println(accountManager.displayAccountDetails(accNo));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 5 -> {
                    try {
                        System.out.print("Account Number: ");
                        long accNo = Long.parseLong(sc.nextLine().trim());
                        Double interest = accountManager.calculateInterest(accNo);
                        if (interest == null) {
                            System.out.println("Account not found.");
                        } else {
                            System.out.println("Yearly interest (not credited): " + interest);
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 6 -> System.out.println("Total accounts: " + Account.getAccountCount());
                case 7 -> {
                    try {
                        System.out.print("Account Number: ");
                        long accNo = Long.parseLong(sc.nextLine().trim());
                        var acc = accountManager.find(accNo);
                        if (acc.isPresent()) {
                            System.out.println("Balance: " + acc.get().getBalance());
                        } else {
                            System.out.println("Account not found.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 8 -> {
                    for (var a : accountManager.getAllAccounts()) {
                        System.out.println(a);
                    }
                }
                case 0 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}
