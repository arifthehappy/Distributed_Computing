public class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountHolderName, String address, String phoneNumber, double balance, double interestRate) {
        super(accountHolderName, address, phoneNumber, balance);
        this.interestRate = interestRate;
    }

    public void calculateInterest() {
        double interest = getBalance() * interestRate;
        super.credit(interest);
        System.out.println("Interest calculated and added. New balance: $" + getBalance());
    }
}
