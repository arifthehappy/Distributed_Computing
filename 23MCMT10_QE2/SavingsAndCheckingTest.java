public class SavingsAndCheckingTest  {
    public static void main(String[] args) {
        BankAccount myCheckingAccount = new CheckingAccount("Arif Khan", "123 Men H", "1234567890", 1000.0);
        BankAccount mySavingsAccount = new SavingsAccount("Sal ", "110 Avenue", "1234560", 2000.0, 0.05);
        
        // transactions checking account
        System.out.println("Checking account:");
        myCheckingAccount.display();
        myCheckingAccount.credit(500.0);
        myCheckingAccount.withdraw(200.0);
        myCheckingAccount.credit(1000.0);
        myCheckingAccount.withdraw(15000.0); // Invalid withdrawal, insufficient funds
        myCheckingAccount.credit(-200.0); // Invalid credit, negative amount

        myCheckingAccount.display();

        // transactions savings account
        mySavingsAccount.display();
        System.out.println("Savings account:");
        mySavingsAccount.credit(1000.0);
        mySavingsAccount.withdraw(500.0);
        ((SavingsAccount) mySavingsAccount).calculateInterest();
        
       
        mySavingsAccount.display();        
    }    
}
