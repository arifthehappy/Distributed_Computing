public class BankAccount {
    private String accountHolderName;
    private String address;
    private String phoneNumber;
    private double balance;

    public BankAccount(String accountHolderName, String address, String phoneNumber, double balance) {
        this.accountHolderName = accountHolderName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    // Getters and Setters
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Methods for Credit and Withdraw
    public void credit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Credited: Rs" + amount);
        } else {
            System.out.println("Invalid amount for credit");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: Rs" + amount);
        } else {
            System.out.println("Invalid amount for withdrawal");
        }
    }

    public void display() {
        System.out.println();
        System.out.println("Account Holder Name: " + accountHolderName);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Balance: Rs" + balance);
        System.out.println();

    }

    //
}
