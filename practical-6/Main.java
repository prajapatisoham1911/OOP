
import java.util.*;

interface Transactable {
    void deposit(long amount);
    boolean withdraw(long amount);
}

interface InterestBearing {
    double interestRate();

    long getBalance();

    default double yearlyInterest() {
        return getBalance() * interestRate() / 100.0;
    }

    default double monthlyInterest() {
        return yearlyInterest() / 12;
    }
}

interface Premium {
}

@FunctionalInterface
interface WithdrawRule {
    boolean allow(Account account, long amount);
}

class Validator {
    public static boolean isValidAmount(long amount) {
        return amount > 0;
    }

    public static boolean canWithdraw(Account account, long amount) {
        return account != null
                && isValidAmount(amount)
                && account.canWithdraw(amount);
    }
}

abstract class Account implements Transactable, InterestBearing {
    private static int counter = 0;

    private final String accountNumber;
    private String ownerName;
    protected long balance;
    private boolean active = true;

    public Account(String ownerName, long balance) {
        this.accountNumber = String.format("AC%04d", ++counter);
        this.ownerName = ownerName;
        this.balance = Math.max(0, balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public long getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void deposit(long amount) {
        if (amount > 0 && active) {
            balance += amount;
        }
    }

    @Override
    public boolean withdraw(long amount) {
        if (amount <= 0 || !active || !canWithdraw(amount)) {
            return false;
        }

        balance -= amount;
        return true;
    }

    public abstract boolean canWithdraw(long amount);

    @Override
    public String toString() {
        return accountNumber + " | " + ownerName + " | " + balance;
    }
}

class SavingsAccount extends Account {
    private long minBalance;

    public SavingsAccount(String name, long balance, long minBalance) {
        super(name, balance);
        this.minBalance = minBalance;
    }

    @Override
    public double interestRate() {
        return 4.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return amount > 0 && balance - amount >= minBalance;
    }
}

class CurrentAccount extends Account {
    private long overdraftLimit;

    public CurrentAccount(String name, long balance, long overdraftLimit) {
        super(name, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public double interestRate() {
        return 0.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return amount > 0 && balance - amount >= -overdraftLimit;
    }
}

class FixedDepositAccount extends Account {
    public FixedDepositAccount(String name, long balance) {
        super(name, balance);
    }

    @Override
    public double interestRate() {
        return 7.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        Account[] accounts = {
            new SavingsAccount("Om", 10000, 2000),
            new CurrentAccount("Rahul", 5000, 3000),
            new FixedDepositAccount("Amit", 20000)
        };

        for (Account account : accounts) {
            System.out.println(account);
            System.out.println("Interest Rate: " + account.interestRate() + "%");
            System.out.println("Yearly Interest: Rs. " + account.yearlyInterest());
            System.out.println("Monthly Interest: Rs. " + account.monthlyInterest());
            System.out.println();
        }

        WithdrawRule rule1 = new WithdrawRule() {
            @Override
            public boolean allow(Account account, long amount) {
                return account.canWithdraw(amount);
            }
        };

        WithdrawRule rule2 = (account, amount) ->
                account.canWithdraw(amount);

        System.out.println("Anonymous Class: "
                + rule1.allow(accounts[0], 1000));

        System.out.println("Lambda Expression: "
                + rule2.allow(accounts[0], 1000));

        System.out.println("Valid Amount: "
                + Validator.isValidAmount(500));

        System.out.println("Can Withdraw: "
                + Validator.canWithdraw(accounts[0], 1000));

        Object obj = accounts[0];

        if (obj instanceof Premium) {
            System.out.println("Premium Account");
        } else {
            System.out.println("Regular Account");
        }

        accounts[0].deposit(500);
        System.out.println("Updated Account: " + accounts[0]);
    }
}
