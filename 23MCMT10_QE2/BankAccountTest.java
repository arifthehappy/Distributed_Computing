
public class BankAccountTest {
    public static void main(String[] args) {
        //Demonstrate at least ten transactions on a bank account from a user Consider all the extreme cases to get full points
        BankAccount myAccount = new BankAccount("Arif Khan", "123 Men H", "123456789", 1000.0);
        myAccount.credit(500.0);
        myAccount.withdraw(200.0);
        myAccount.credit(1000.0);
        myAccount.withdraw(15000.0); // Invalid withdrawal, insufficient funds
        myAccount.credit(-200.0); // Invalid credit, negative amount

        // demonstrate transactions
        myAccount.display();
       
    }
}