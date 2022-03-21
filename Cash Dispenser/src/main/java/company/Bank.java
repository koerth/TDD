package company;

public class Bank {
    private BankDatabase bankDatabase;

    // Connection to local 'database'
    public Bank(BankDatabase bankDatabase) {
        this.bankDatabase = bankDatabase;
    }

    // Setting min- and max values
    public static double minWithdrawAmount = 10.00;
    public static double maxWithdrawAmount = 500.00;
    public int totalAttempts = 3; // Including a standard amount of attempts

    // Function for Withdrawl
    public void withdrawAmount(int accountID, double amount) {
        BankAccount bankAccount = bankDatabase.getAccountByID(accountID);
        double balance = bankAccount.getBalance();

        if (amount < minWithdrawAmount) {
            throw new IllegalArgumentException("Only 10 and over are permitted.");
        }
        if (amount > maxWithdrawAmount) {
            throw new IllegalArgumentException("Only 500 and under are permitted.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds.");
        }

        balance = balance - amount;
        bankAccount.setBalance(balance);
    }

    // Function for Checking Balance
    public double checkBalance(int accountID) {
        BankAccount bankAccount = bankDatabase.getAccountByID(accountID);
        return bankAccount.getBalance();
    }

    // Function for Adding to Account
    public void addAmount(int accountID, double amount) {
        BankAccount bankAccount = bankDatabase.getAccountByID(accountID);
        if (bankAccount.isLoggedIn()) {
            double balance = bankAccount.getBalance();

            if (amount < 0.0d) {
                System.out.println("Error: unable to add negative amount.");
            }
            balance = balance + amount;

            // Updates Account Values
            bankAccount.setBalance(balance);
        } else {
            System.out.println("You're not logged in.");
        }
    }

    // Function for Logging in
    public void executeLogin(int accountID, int pin) {
        BankAccount bankAccount = bankDatabase.getAccountByID(accountID);

        if (totalAttempts > 0) {
            if (pin == bankAccount.getPin()) { // Checks if pin matches pin, by fetching the value from BankAccount.java
                bankAccount.setLoggedIn(true); // Sets account to logged in
                System.out.println("Greetings sire!");
            } else {
                System.out.println("Login attempt failed, please try again.");
                totalAttempts--; // Subtracts one from current amount in attempts
            }
        } else {
            System.out.println("You have reaced the maximum allowed tried, your card has not been locked until further notice.");
            bankAccount.setLocked(true); // Sets account to locked if attempts succeed 3
        }

    }

    public void saveChanges(int accountID, int pin, double balance, String bank) {
        BankAccount bankAccount = bankDatabase.getAccountByID(accountID);
        
        // Update values to BankAccount
        bankAccount.setPin(pin);
        bankAccount.setBalance(balance);
        BankAccount.setBank(bank);
    }

}


