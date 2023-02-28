/**
 *
 */
package account;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Ali Ahsan(Q4RUJI)
 *
 */
abstract class accountSystem extends account.customer{

    private double balance;
    private String account_number;
    private String createdDate;
    private double transaction_amount;
    public accountSystem() {
        balance = 0;
        account_number = "" ;
        createdDate = "";
        transaction_amount = 0;
    }

    //getters and setters
    public void setBalance(double balance)
    {
        if(balance>0)
            this.balance = balance;
    }
    public double getBalance()
    {
        return this.balance;
    }
    public void setTransactionAmount(double transaction_amount)
    {
        if(transaction_amount>0)
            this.transaction_amount = transaction_amount;
    }
    public double getTransactionAmount()
    {
        return this.transaction_amount;
    }
    public String getaccount_number()
    {
        return account_number;
    }
    public void setaccount_number(String account_number)
    {
        if(account_number.length()==10)
            this.account_number = account_number;
    }
    public String getCreatedDate()
    {
        return createdDate;
    }
    public void setCreatedDate(String createdDate)
    {
        this.createdDate = createdDate;
    }

    public String printDateTime() {
        //printing time and date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public abstract void checkBalance();
    public abstract void makeDeposit(double deposit_amount);
    public abstract void makeWithdrawl(double withdrawl_amount);
    public abstract void printStatment();
    public abstract void transferAmount(double transfer_amount,accountSystem acc,String acc_number);
    public abstract void displayAlldeductions();
}