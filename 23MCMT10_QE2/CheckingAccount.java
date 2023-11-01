public class CheckingAccount extends BankAccount {
    private static final int FREE_TRANSACTIONS = 1;
    private static final double TRANSACTION_FEE = 2.0;
    private int transactionCount;

    public CheckingAccount(String accountHolderName, String address, String phoneNumber, double balance) {
        super(accountHolderName, address, phoneNumber, balance);
        this.transactionCount = 0;
    }

    @Override
    public void credit(double amount) {
        super.credit(amount);
        transactionCount++;
        if (transactionCount > FREE_TRANSACTIONS) {
            super.withdraw(TRANSACTION_FEE);
            System.out.println("Transaction fee applied. New balance: $" + getBalance());
        }
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
        transactionCount++;
        if (transactionCount > FREE_TRANSACTIONS) {
            super.withdraw(TRANSACTION_FEE);
            System.out.println("Transaction fee applied. New balance: $" + getBalance());
        }
    }
}
