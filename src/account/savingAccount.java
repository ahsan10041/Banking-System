package account;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
public class savingAccount extends account.accountSystem {

    private double interest_rate ;
    private double zakat_amount;
    private HashMap<String,String> deduction;
    public savingAccount()
    {
        this.interest_rate = 15;
        this.zakat_amount = 0;
        this.deduction = new HashMap<String,String>();
    }
    public void setZakatAmount(double zakat_amount)
    {
        this.zakat_amount = zakat_amount;
    }
    public double getZakatAmount()
    {
        return this.zakat_amount;
    }
    public void checkBalance()
    {
        System.out.println("Current Balance : "+getBalance());
    }
    public void makeDeposit(double deposit_amount)
    {
        this.setBalance(this.getBalance()+deposit_amount);
        System.out.println("Depositing...");
        this.deduction.put(this.printDateTime()+ " Recieved Amount   = ", "PKR " + deposit_amount);
        System.out.println("\n\t*--NOTIFICATION----------------------*");
        String message = "\tPKR "+deposit_amount+" deposit"
                +" from MARKAZ "+this.getAddress()+"\n\tin your "
                +"A/C xxxxxx"+this.getAcc_number()
                +"\n\ton "+this.printDateTime()+" Bal: PKR "+this.getBalance();
        System.out.println(message);
        System.out.println("\t*------------------------------------*\n");
    }
    public void makeWithdrawl(double withdrawl_amount)
    {
        this.deduction.put(this.printDateTime()+ " Withdrawn Amount = ", "PKR " + withdrawl_amount);
        this.setTransactionAmount(this.getTransactionAmount()+withdrawl_amount);
        if(withdrawl_amount<getBalance())
        {
            this.setBalance(getBalance()-withdrawl_amount);
            System.out.println("Processing...");
            System.out.println("\n\t*--NOTIFICATION----------------------*");
            String message = "\tPKR "+withdrawl_amount+" cash withdrawn "
                    +"\n\tfrom MARKAZ "+this.getAddress()+" from "
                    + "\n\tA/C xxxxxx"+this.getAcc_number()
                    +"\n\ton "+this.printDateTime()+" Bal: PKR "+this.getBalance();
            System.out.println(message);
            System.out.println("\t*------------------------------------*\n");
        }
        else
            System.out.println("\tNot enough Balance!!!");
    }
    public void transferAmount(double transfer_amount,accountSystem acc,String acc_number)
    {
        // TODO transferring amount to another account
        int id_check = Integer.parseInt(acc_number.substring(acc_number.length() - 4));
        acc.customer_accounts(id_check);
        if(acc.getRecords().containsKey(id_check))
        {
            if(this.getBalance()<transfer_amount)
            {
                System.out.println("\tNot enough Balance!!!");
            }
            else
            {
                this.setTransactionAmount(this.getTransactionAmount()+transfer_amount);
                this.setBalance(getBalance()-transfer_amount);
                this.deduction.put(this.printDateTime()+" Transfer Amount   = ",
                        "PKR "+transfer_amount+" sent to "+acc.getName());
                acc.setBalance(acc.getBalance()+transfer_amount);
                System.out.println("Sending...");
                System.out.println("\n\t*----NOTIFICATION----------------------------*");
                String message = "\tPKR "+transfer_amount+" sent to "+acc.getName()+"-"
                        +acc.getPhone_number()+"\n\tfrom your A/C xxxxxx"
                        +this.getAcc_number()+" of BR "+this.getAddress()
                        +"\n\ton "+this.printDateTime()+" Bal: PKR "+this.getBalance();
                System.out.println(message);
                System.out.println("\t*--------------------------------------------*\n");
            }
        }
        else
            System.out.println("\tAccount Not Found");
    }
    public void calculateZakat()
    {

        if(this.getBalance()>=20000)
        {
            setZakatAmount((this.getBalance()*2.5)/100);
            setBalance(this.getBalance()-this.getZakatAmount());
            this.setTransactionAmount(this.getTransactionAmount()+this.getZakatAmount());
            this.deduction.put(this.printDateTime()+" Zakat Amount     = ", "PKR "+this.getZakatAmount());
            System.out.println("Calculating Zakat...");
            System.out.println("\n\t*--NOTIFICATION----------------------*");
            String message = "\tPKR "+this.getZakatAmount()+" is debited as "
                    +"ZAKAT AMOUNT\n\tfrom your A/C xxxxxx"+this.getAcc_number()
                    +"\n\tof "+this.getAddress()
                    +" on "+this.printDateTime()+"\n\tBal: PKR "+this.getBalance();
            System.out.println(message);
            System.out.println("\t*------------------------------------*\n");
        }
        else
            System.out.println("*--NOTE-----Not applicable to Calculate Zakat");
    }
    public void displayAlldeductions()
    {
        Set<Map.Entry<String, String>> deductions = this.deduction.entrySet();
        System.out.println("\n\t*----NOTIFICATION-----------------------------------------*");
        for (Map.Entry<String, String> d : deductions)
        {
            System.out.println("\t"+d.getKey()+d.getValue());
        }
        System.out.println("\t*---------------------------------------------------------*");
    }
    @SuppressWarnings("resource")
    public void calculateInterest()
    {
        Scanner in_6 = new Scanner(System.in);
        System.out.println("Do you want to change Interest Rate? (y/n)");
        char yes_no = in_6.next().charAt(0);
        if(yes_no == 'y')
        {
            System.out.println("Interest Rate - ");
            this.interest_rate = in_6.nextDouble();
        }
        double total_interest = this.getBalance()*(this.interest_rate/100);
        System.out.println("\n\t*--NOTIFICATION---------------------------------*");
        System.out.println("\tYou have to pay : PKR "+total_interest+" as Interest");
        System.out.println("\t*-----------------------------------------------*\n");
    }
    public void printStatment()
    {
        /* Account number
         * Date/Time
         * Transaction Amount
         * Remaining Balance
         */
        this.customer_details(this.getAcc_number());
        System.out.println("Date/Time          : " + this.printDateTime());
        System.out.println("Transaction Amount : " + this.getTransactionAmount());
        System.out.println("Zakat Amount       : " + this.getZakatAmount());
        System.out.println("Remaining Balance  : " + this.getBalance());
    }


}